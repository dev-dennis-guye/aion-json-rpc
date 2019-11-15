package org.aion.rpc.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class TypeUtilsTest {

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeLongToHex(long l) {
        String s0 = Long.toHexString(l);
        String s1 = TypeUtils.encodeLongToHex(l);
        assertEquals(s0,s1.substring(2).replaceAll("^(0)*",""));
        assertEquals(Long.parseLong(s0, 16), TypeUtils.decodeLongFromHex(s1));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeIntegerToHex(int i) {
        String s0 = Integer.toHexString(i);
        String s1 = TypeUtils.encodeIntegerToHex(i);
        assertEquals(s0,s1.substring(2).replaceAll("^(0)*",""));
        assertEquals(Integer.parseInt(s0, 16), TypeUtils.decodeIntFromHex(s1));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeLongToHexFixedLength(long l) {
        String s0 = Long.toHexString(l);
        String s1 = TypeUtils.encodeLongToHexFixedLength(l);
        System.out.println(s1);
        assertEquals(s0,s1.substring(2).replaceAll("^(0)*",""));
        assertEquals(Long.parseLong(s0, 16), TypeUtils.decodeLongFromHex(s1));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeIntegerToHexFixedLength(int i) {
        String s0 = Integer.toHexString(i);
        String s1 = TypeUtils.encodeIntegerToHexFixedLength(i);
        System.out.println(s1);
        assertEquals(s0,s1.substring(2).replaceAll("^(0)*",""));
        assertEquals(Integer.parseInt(s0, 16), TypeUtils.decodeIntFromHex(s1));
    }

    @ParameterizedTest
    @MethodSource("integerStream")
    void encodeBigIntegerToHex(long l) {
        BigInteger bigInteger = BigInteger.valueOf(l);
        String s0 = bigInteger.toString(16);
        String s1 = TypeUtils.encodeBigIntegerToHex(bigInteger, 8);
        System.out.println(s1);
        assertEquals(s0,s1.substring(2).replaceAll("^(0)*",""));
        assertEquals(new BigInteger(s0, 16), TypeUtils.decodeBigIntFromHex(s1));
    }


    static Stream<Arguments> integerStream() {
        return IntStream.rangeClosed(0, 20).mapToDouble(i -> Math.pow(2, i))
            .mapToLong(i -> (long) Math.floor(i)).mapToInt(Math::toIntExact)
            .mapToObj(Arguments::of);
    }
}
