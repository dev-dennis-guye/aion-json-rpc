package org.aion.rpc.types;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.aion.rpc.errors.RPCExceptions.ParseErrorRPCException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class TypeUtilsTest {

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeLongToHex(long l) {
        String s0 = Long.toHexString(l);
        String s1 = TypeUtils.encodeLongToHex(l);
        compareStringEncoding(s0, s1);
        assertEquals(Long.parseLong(s0, 16), TypeUtils.decodeLongFromHex(s1));// we use the same method of decoding in type utils so there should be no issue
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeIntegerToHex(int i) {
        String s0 = Integer.toHexString(i);
        String s1 = TypeUtils.encodeIntegerToHex(i);
        compareStringEncoding(s0, s1);
        assertEquals(Integer.parseInt(s0, 16), TypeUtils.decodeIntFromHex(s1));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeLongToHexFixedLength(long l) {
        String s0 = Long.toHexString(l);
        String s1 = TypeUtils.encodeLongToHexFixedLength(l);
        System.out.println(s1);
        compareStringEncoding(s0, s1);
        checkHexStringLength(Long.BYTES * 2 + 2, s1);
        assertEquals(Long.parseLong(s0, 16), TypeUtils.decodeLongFromHex(s1));// we use the same method of decoding in type utils so there should be no issue
    }

    //compares our method of encoding with the standard library
    //we should have only added 0s
    private void compareStringEncoding(String s0, String s1) {
        assertEquals(s0, s1.substring(2).replaceAll("^(0)*", ""));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeIntegerToHexFixedLength(int i) {
        String s0 = Integer.toHexString(i);
        String s1 = TypeUtils.encodeIntegerToHexFixedLength(i);
        System.out.println(s1);
        compareStringEncoding(s0, s1);
        checkHexStringLength(Integer.BYTES * 2 + 2, s1);
        assertEquals(Integer.parseInt(s0, 16), TypeUtils.decodeIntFromHex(s1));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeBigIntegerToHex(long l) {
        BigInteger bigInteger = BigInteger.valueOf(l);
        String s0 = bigInteger.toString(16);
        final int arrLength=8;
        String s1 = TypeUtils.encodeBigIntegerToHex(bigInteger, arrLength);
        System.out.println(s1);
        compareStringEncoding(s0, s1);
        checkHexStringLength(arrLength * 2 + 2, s1);
        assertEquals(new BigInteger(s0, 16), TypeUtils.decodeBigIntFromHex(s1));// we use the same method of decoding in type utils so there should be no issue
    }

    private void checkHexStringLength(int i, String str) {
        assertEquals(i, str.length());
    }


    static Stream<Arguments> integerStream() {
        return IntStream.rangeClosed(0, 20).mapToDouble(i -> Math.pow(2, i))
            .mapToLong(i -> (long) Math.floor(i)).mapToInt(Math::toIntExact)
            .mapToObj(Arguments::of);
    }

    //Check the unsigned validators
    @Test
    void checkUnsignedLong() {
        assertThrows(ParseErrorRPCException.class, ()-> TypeUtils.checkUnsigned(-1L));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned(0L));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned(1L));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned((Long) null));
    }

    @Test
    void checkUnsignedInt() {
        assertThrows(ParseErrorRPCException.class, ()-> TypeUtils.checkUnsigned(-1));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned(0));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned(1));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned((Integer) null));
    }

    @Test
    void checkUnsignedBigInt() {
        assertThrows(ParseErrorRPCException.class, ()-> TypeUtils.checkUnsigned(BigInteger.ONE.negate()));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned(BigInteger.ZERO));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned(BigInteger.ONE));
        assertDoesNotThrow(()-> TypeUtils.checkUnsigned((BigInteger) null));
    }
}
