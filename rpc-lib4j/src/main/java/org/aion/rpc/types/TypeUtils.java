package org.aion.rpc.types;

import java.math.BigInteger;
import java.util.Arrays;
import org.aion.util.bytes.ByteUtil;

class TypeUtils {

    public static String encodeLongToHex(Long l){
        String outputString = Long.toString(l,16);
        if (outputString.length() %2 ==0){
            return "0x"+outputString;
        }
        else {
            return "0x0"+outputString;
        }
    }

    public static String encodeIntegerToHex(int i){
        String outputString = Integer.toString(i,16);
        if (outputString.length() % 2 ==0){
            return "0x"+outputString;
        }
        else {
            return "0x0"+outputString;
        }
    }

    public static String encodeLongToHexFixedLength(Long l){
        byte[] output = padArray(8, ByteUtil.longToBytes(l));
        String outputString = ByteUtil.toHexString(output);
        if (outputString.length() %2 ==0){
            return "0x"+outputString;
        }
        else {
            return "0x0"+outputString;
        }
    }

    public static String encodeIntegerToHexFixedLength(int i){
        byte[] output = padArray(4, ByteUtil.intToBytes(i));
        String outputString = ByteUtil.toHexString(output);
        if (outputString.length() % 2 ==0){
            return "0x"+outputString;
        }
        else {
            return "0x0"+outputString;
        }
    }

    public static String encodeBigIntegerToHex(BigInteger b, final int byteLength){
        final byte[] outputArray = padArray(byteLength, b.toByteArray());
        final String outputString = ByteUtil.toHexString(outputArray);
        if (outputString.length() % 2 ==0){
            return "0x"+outputString;
        }
        else {
            return "0x0"+outputString;
        }
    }

    private static byte[] padArray(int byteLength, byte[] inputArray) {
        final byte[] outputArray = new byte[byteLength];
        System.arraycopy(inputArray, 0, outputArray, byteLength - inputArray.length, inputArray.length);
        return outputArray;
    }

    public static long decodeLongFromHex(final String s){
        final String stringToParse;
        if (s.startsWith("0x")) {
            stringToParse = s.substring(2);
        }
        else {
            stringToParse = s;
        }
        return Long.parseLong(stringToParse, 16);
    }

    public static int decodeIntFromHex(final String  s){
        final String stringToParse;
        if (s.startsWith("0x")) {
            stringToParse = s.substring(2);
        }
        else {
            stringToParse = s;
        }
        return Integer.parseInt(stringToParse, 16);
    }

    public static BigInteger decodeBigIntFromHex(final String s){
        final String stringToParse;
        if (s.startsWith("0x")) {
            stringToParse = s.substring(2);
        }
        else {
            stringToParse = s;
        }
        return new BigInteger(stringToParse, 16);
    }

}
