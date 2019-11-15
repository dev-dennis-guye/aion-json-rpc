package org.aion.rpc.types;

import java.math.BigInteger;
import org.aion.rpc.errors.RPCExceptions.ParseErrorRPCException;
import org.aion.util.bytes.ByteUtil;

/**
 * The use of these utilities are internal to the rpc library
 * Therefore all access is package private
 * To access the utilities provided by the library please use the RPCTypesConverter
 */
class TypeUtils {

    /**
     * Check the number and throws if the sign is negative
     * @param l
     */
    static void checkUnsigned(Long l){//we use boxed values here since the caller may pass in nulls
        if (l !=null && l<0) throw ParseErrorRPCException.INSTANCE;
    }

    /**
     * Checks the number and throws if the sign is negative
     * @param bigInteger
     */
    static void checkUnsigned(BigInteger bigInteger){
        final int negativeSignum = -1;
        if (bigInteger!=null && bigInteger.signum()==negativeSignum) throw ParseErrorRPCException.INSTANCE;
    }

    /**
     * Checks the number and throws if the sign is negative
     * @param i
     */
    static void checkUnsigned(Integer i){// same as with unsigned long
        if (i!=null && i<0) throw ParseErrorRPCException.INSTANCE;
    }

    static String encodeLongToHex(long l) {
        String outputString = Long.toString(l, 16);
        return fixEncoding(outputString);
    }

    private static boolean encodesBytes(
        String hexString) {// to encode a byte array we need an even number of chars
        return hexString.length() % 2 == 0;
    }

    static String encodeIntegerToHex(int i) {
        String outputString = Integer.toString(i, 16);
        return fixEncoding(outputString);
    }

    //Validates that the hex string encodes a valid byte array
    //This may be done by adding a leading 0
    //We assume that all hex string are bigendian
    private static String fixEncoding(String outputString) {
        if (encodesBytes(outputString)) {
            return "0x" + outputString;
        } else {
            return "0x0" + outputString;// add a leading 0 so that we have a valid byte
        }
    }

    static String encodeLongToHexFixedLength(long l) {
        byte[] output = padArray(Long.BYTES, ByteUtil.longToBytes(l));
        String outputString = ByteUtil.toHexString(output);
        return "0x" + outputString;// we started with a byte array so we do not need to check that the string is valid
    }

    static String encodeIntegerToHexFixedLength(int i) {
        byte[] output = padArray(Integer.BYTES, ByteUtil.intToBytes(i));
        String outputString = ByteUtil.toHexString(output);
        return "0x" + outputString;
    }

    static String encodeBigIntegerToHex(BigInteger b, final int byteLength) {
        final byte[] outputArray = padArray(byteLength, b.toByteArray());
        final String outputString = ByteUtil.toHexString(outputArray);
        return "0x"
            + outputString;// we started with a byte array so we do not need to check that the string is valid
    }

    //Makes a copy of the array and pads with leading 0s
    private static byte[] padArray(int byteLength, byte[] inputArray) {
        final byte[] outputArray = new byte[byteLength];
        System.arraycopy(inputArray, 0, outputArray, byteLength - inputArray.length,
            inputArray.length);
        return outputArray;
    }

    static long decodeLongFromHex(final String s) {
        final String stringToParse = strip0x(s);
        return Long.parseLong(stringToParse, 16);
    }

    static int decodeIntFromHex(final String s) {
        final String stringToParse = strip0x(s);
        return Integer.parseInt(stringToParse, 16);
    }

    private static String strip0x(String s) {
        String stringToParse;
        if (s.startsWith("0x")) {
            stringToParse = s.substring(2);
        } else {
            stringToParse = s;
        }
        return stringToParse;
    }

    static BigInteger decodeBigIntFromHex(final String s) {
        final String stringToParse = strip0x(s);
        return new BigInteger(stringToParse, 16);
    }

}
