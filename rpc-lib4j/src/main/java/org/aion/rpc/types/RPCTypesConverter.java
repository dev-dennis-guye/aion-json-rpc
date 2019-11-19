package org.aion.rpc.types;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.aion.rpc.errors.RPCExceptions.InvalidParamsRPCException;
import org.aion.rpc.errors.RPCExceptions.ParseErrorRPCException;
import org.aion.rpc.types.RPCTypes.*;
import org.aion.types.AionAddress;
import org.aion.util.bytes.ByteUtil;
import org.aion.util.types.ByteArrayWrapper;
import org.json.JSONArray;
import org.json.JSONObject;

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-19
*
*****************************************************************************/
public class RPCTypesConverter{
    /**
    * Validates that a hex string.
    */
    public static final Pattern hexPattern = Pattern.compile("^0x[0-9a-fA-F]+$");
    /**
    * Validates an unsigned decimal string.
    */
    public static final Pattern unsignedDecPattern = Pattern.compile("^[0-9]+$");
    /**
    * Validates that a number is encoded as a hex string. This is different from hex pattern since this validates that the string correctly encodes a byte array.
    */
    public static final Pattern unsignedHexPattern = Pattern.compile("^0x([0-9a-fA-F]{2})+$");
    /**
    * Validates a signed decimal string.
    */
    public static final Pattern decPattern = Pattern.compile("^[-+]?[0-9]+$");
    /**
    * Validates a boolean string.
    */
    public static final Pattern booleanPattern = Pattern.compile("^([Tt]rue|[Ff]alse)$");
    /**
    * Validates a byte hex string. This is different from unsignedHexPattern since empty byte arrays are allowed here.
    */
    public static final Pattern byteArrayPattern = Pattern.compile("^0x([0-9a-fA-F]{2})*$");
    /**
    * Validates that an aion address is valid
    */
    public static final Pattern aionAddressPattern = Pattern.compile("^0x([Aa]0[a-fA-F0-9]{62}|0{64}|0{61}200)$");

    public static class ObjectConverter{

        public static String decode(Object s){
            if(s==null || s.equals(JSONObject.NULL)) return null;
            return s.toString();
        }

        public static Object encode(Object obj){
            return obj;
        }
    }

    public static class StringConverter{

        public static String decode(Object s){
            if(s==null || s.equals(JSONObject.NULL)) return null;
            return s.toString();
        }

        public static String encode(String s){
            return s;
        }
    }

    public static class BooleanConverter{
        public static Boolean decode(Object s){
            if(s==null || s == JSONObject.NULL) return null;
            if ( booleanPattern.matcher(s.toString()).find()) return Boolean.parseBoolean(s.toString());
            else throw ParseErrorRPCException.INSTANCE;
        }

        public static Boolean encode(Boolean b){
            return b;
        }
    }

    public static class ByteConverter{
        public static Byte decode(Object s){
            if(s==null||s.equals(JSONObject.NULL)) return null;
            if(hexPattern.matcher(s.toString()).find()){
                return Byte.parseByte(s.toString().substring(2), 16);
            }
            else if(decPattern.matcher(s.toString()).find()){
                return Byte.parseByte(s.toString());
            }
            else{
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Byte encode(Byte s) {
            try {
                return s;
            } catch (Exception e) {
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeHex(Byte s) {
            try {
                if (s==null||s.equals(JSONObject.NULL)) return null;
                else return "0x"+ByteUtil.oneByteToHexString(s);
            } catch (Exception e) {
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class LongConverter{

        public static Long decode(Object s){
            if(s==null || s.equals(JSONObject.NULL)) return null;
            if(hexPattern.matcher(s.toString()).find()){
                return Long.parseLong(s.toString().substring(2), 16);
            }
            else if(decPattern.matcher(s.toString()).find()){
                return Long.parseLong(s.toString());
            }
            else{
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Long encode(Long s){
            try{
                return s;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeHex(Long s){
            try{
            if (s==null || s.equals(JSONObject.NULL)) return null;
            else return "0x"+Long.toHexString(s);
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

    }

    public static class UnsignedInteger16Converter{
        public static String encodeHex(Integer integer){
            TypeUtils.checkUnsigned(integer);
            if (integer == null) {
                return null;
            } else return TypeUtils.encodeIntegerToHex(integer);
        }

        public static Integer encode(Integer integer){
            TypeUtils.checkUnsigned(integer);
            return integer;
        }

        public static Integer decode(Object object){
            if (object == null) {
                return null;
            } else {
                String string = object.toString();
                Integer  integer;
                if (unsignedDecPattern.matcher(string).find()){
                    integer = Integer.parseInt(string);
                } else if (unsignedHexPattern.matcher(string).find()){
                    integer = TypeUtils.decodeIntFromHex(string);
                } else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                TypeUtils.checkUnsigned(integer);
                return integer;
            }
        }
    }

    public static class UnsignedInteger32Converter{
        public static String encodeHex(Long integer){
            TypeUtils.checkUnsigned(integer);
            if (integer == null) {
                return null;
            } else return TypeUtils.encodeLongToHex(integer);
        }

        public static Long encode(Long integer){
            TypeUtils.checkUnsigned(integer);
            return integer;
        }

        public static Long decode(Object object){
            if (object == null) {
                return null;
            } else {
                String string = object.toString();
                Long integer;
                if (unsignedDecPattern.matcher(string).find()){
                    integer= Long.parseLong(string);
                }else if (unsignedHexPattern.matcher(string).find()){
                    integer= TypeUtils.decodeLongFromHex(string);
                } else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                TypeUtils.checkUnsigned(integer);
                return integer;
            }
        }
    }

    public static class UnsignedInteger64Converter{
        public static String encodeHex(BigInteger integer){
            TypeUtils.checkUnsigned(integer);
            if (integer == null) {
                return null;
            } else return TypeUtils.encodeBigIntegerToHex(integer,8);
        }

        public static String encode(BigInteger integer){
            TypeUtils.checkUnsigned(integer);
            if(integer==null) return null;
            return integer.toString();
        }

        public static BigInteger decode(Object object){
            if (object == null) {
                return null;
            } else {
                String string = object.toString();
                BigInteger integer;
                if (unsignedDecPattern.matcher(string).find()){
                    integer = new BigInteger(string);
                } else if (unsignedHexPattern.matcher(string).find()){
                    integer= TypeUtils.decodeBigIntFromHex(string);
                } else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                TypeUtils.checkUnsigned(integer);
                return integer;
            }
        }
    }

    public static class UnsignedInteger128Converter{
        public static String encodeHex(BigInteger integer){
            TypeUtils.checkUnsigned(integer);
            if (integer == null) {
                return null;
            } else return TypeUtils.encodeBigIntegerToHex(integer,16);
        }

        public static String encode(BigInteger integer){
            TypeUtils.checkUnsigned(integer);
            if(integer==null) return null;
            return integer.toString();
        }

        public static BigInteger decode(Object object){
            if (object == null) {
                return null;
            } else {
                String string = object.toString();
                BigInteger integer;
                if (unsignedDecPattern.matcher(string).find()){
                    integer= new BigInteger(string);
                } else if (unsignedHexPattern.matcher(string).find()){
                    integer= TypeUtils.decodeBigIntFromHex(string);
                } else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                TypeUtils.checkUnsigned(integer);
                return integer;
            }
        }
    }

    public static class UnsignedInteger256Converter{
        public static String encodeHex(BigInteger integer){
            TypeUtils.checkUnsigned(integer);
            if (integer == null) {
                return null;
            } else return TypeUtils.encodeBigIntegerToHex(integer, 32);
        }

        public static String encode(BigInteger integer){
            TypeUtils.checkUnsigned(integer);
            if(integer==null) return null;
            return integer.toString();
        }

        public static BigInteger decode(Object object){
            if (object == null) {
                return null;
            } else {
                String string = object.toString();
                BigInteger returnValue;
                if (unsignedDecPattern.matcher(string).find()){
                    returnValue= new BigInteger(string);
                } else if (unsignedHexPattern.matcher(string).find()){
                    returnValue= TypeUtils.decodeBigIntFromHex(string);
                } else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                TypeUtils.checkUnsigned(returnValue);
                return returnValue;
            }
        }
    }

    public static class IntegerConverter{

        public static Integer decode(Object s){
            if(s==null || s.equals(JSONObject.NULL)) return null;
            if(hexPattern.matcher(s.toString()).find()){
                return Integer.parseInt(s.toString().substring(2), 16);
            }
            else if(decPattern.matcher(s.toString()).find()){
                return Integer.parseInt(s.toString());
            }
            else{
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Integer encode(Integer s){
            try{
                return s;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeHex(Integer s){
            try{
                if (s==null) return null;
                else return "0x"+Integer.toHexString(s);
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class BigIntegerConverter{

        public static String encodeHex(BigInteger bigInteger){
            try{
                if(bigInteger==null) return null;
                return "0x"+bigInteger.toString(16);
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(BigInteger bigInteger){
            try{
                if(bigInteger==null) return null;
                return bigInteger.toString(10);
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static BigInteger decode(Object s){
            if(s==null || s.equals(JSONObject.NULL)) return null;

            if(hexPattern.matcher(s.toString()).find()){
                return new BigInteger(s.toString().substring(2), 16);
            }
            else if(decPattern.matcher(s.toString()).find()){
                return new BigInteger(s.toString());
            }
            else{
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class ByteArrayConverter{

        public static ByteArray decode(Object obj){
            if (obj == null || obj.equals(JSONObject.NULL)){
                return null;
            }
            else if(obj instanceof byte[]){
                return new ByteArray((byte[]) obj);
            }
            else if (obj instanceof String && byteArrayPattern.matcher(((String)obj)).find()){
                return new ByteArray(ByteUtil.hexStringToBytes((String) obj));
            }
            else {
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(ByteArray bytes){
            if (bytes == null) return null;
            else return bytes.toString();
        }
    }

    public static class AionAddressConverter{
        public static AionAddress decode(Object obj){
            try{
                if (obj == null){
                    return null;
                }
                else if (obj instanceof String && aionAddressPattern.matcher(((String)obj)).find()){
                    return new AionAddress(ByteUtil.hexStringToBytes(((String) obj)));
                }
                else if (obj instanceof byte[]){
                    return new AionAddress(((byte[])obj));
                }
                else {
                    throw ParseErrorRPCException.INSTANCE;
                }
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(AionAddress address){
            if (address==null) return null;
            else return "0x"+address.toString();
        }
    }
    public static class BlockSpecifierUnionConverter{
        public static BlockSpecifierUnion decode(Object str){
             if(str==null|| str==JSONObject.NULL) return null;
            return BlockSpecifierUnion.decode(str);
        }

        public static Object encode(BlockSpecifierUnion obj){
            if(obj==null) return null;
            else return obj.encode();
        }

        public static String encodeStr(BlockSpecifierUnion obj){
            if(obj==null) return null;
            else return obj.encode().toString();
        }
    }

    public static class ResultUnionConverter{
        public static ResultUnion decode(Object str){
             if(str==null|| str==JSONObject.NULL) return null;
            return ResultUnion.decode(str);
        }

        public static Object encode(ResultUnion obj){
            if(obj==null) return null;
            else return obj.encode();
        }

        public static String encodeStr(ResultUnion obj){
            if(obj==null) return null;
            else return obj.encode().toString();
        }
    }

    public static class ParamUnionConverter{
        public static ParamUnion decode(Object str){
            
            return ParamUnion.decode(str);
        }

        public static Object encode(ParamUnion obj){
            if(obj==null) return null;
            else return obj.encode();
        }

        public static String encodeStr(ParamUnion obj){
            if(obj==null) return null;
            else return obj.encode().toString();
        }
    }

    public static class RequestConverter{
        public static Request decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new Request( IntegerConverter.decode(jsonObject.opt("id")) , StringConverter.decode(jsonObject.opt("method")) , ObjectConverter.decode(jsonObject.opt("params")) , VersionTypeConverter.decode(jsonObject.opt("jsonrpc")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( Request obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", IntegerConverter.encode(obj.id));
                jsonObject.put("method", StringConverter.encode(obj.method));
                jsonObject.put("params", ObjectConverter.encode(obj.params));
                jsonObject.put("jsonrpc", VersionTypeConverter.encode(obj.jsonrpc));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( Request obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", IntegerConverter.encode(obj.id));
                jsonObject.put("method", StringConverter.encode(obj.method));
                jsonObject.put("params", ObjectConverter.encode(obj.params));
                jsonObject.put("jsonrpc", VersionTypeConverter.encode(obj.jsonrpc));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class ResponseConverter{
        public static Response decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new Response( IntegerConverter.decode(jsonObject.opt("id")) , ObjectConverter.decode(jsonObject.opt("result")) , RPCErrorConverter.decode(jsonObject.opt("error")) , VersionTypeConverter.decode(jsonObject.opt("jsonrpc")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( Response obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", IntegerConverter.encode(obj.id));
                jsonObject.put("result", ObjectConverter.encode(obj.result));
                jsonObject.put("error", RPCErrorConverter.encode(obj.error));
                jsonObject.put("jsonrpc", VersionTypeConverter.encode(obj.jsonrpc));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( Response obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", IntegerConverter.encode(obj.id));
                jsonObject.put("result", ObjectConverter.encode(obj.result));
                jsonObject.put("error", RPCErrorConverter.encode(obj.error));
                jsonObject.put("jsonrpc", VersionTypeConverter.encode(obj.jsonrpc));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class RPCErrorConverter{
        public static RPCError decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new RPCError( IntegerConverter.decode(jsonObject.opt("code")) , StringConverter.decode(jsonObject.opt("message")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( RPCError obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", IntegerConverter.encode(obj.code));
                jsonObject.put("message", StringConverter.encode(obj.message));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( RPCError obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", IntegerConverter.encode(obj.code));
                jsonObject.put("message", StringConverter.encode(obj.message));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class TxLogDetailsConverter{
        public static TxLogDetails decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new TxLogDetails( AionAddressConverter.decode(jsonObject.opt("address")) , IntegerConverter.decode(jsonObject.opt("transactionIndex")) , DataHexStringConverter.decode(jsonObject.opt("data")) , DataHexStringListConverter.decode(jsonObject.opt("topics")) , LongConverter.decode(jsonObject.opt("blockNumber")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( TxLogDetails obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("address", AionAddressConverter.encode(obj.address));
                jsonObject.put("transactionIndex", IntegerConverter.encode(obj.transactionIndex));
                jsonObject.put("data", DataHexStringConverter.encode(obj.data));
                jsonObject.put("topics", DataHexStringListConverter.encode(obj.topics));
                jsonObject.put("blockNumber", LongConverter.encode(obj.blockNumber));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( TxLogDetails obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("address", AionAddressConverter.encode(obj.address));
                jsonObject.put("transactionIndex", IntegerConverter.encode(obj.transactionIndex));
                jsonObject.put("data", DataHexStringConverter.encode(obj.data));
                jsonObject.put("topics", DataHexStringListConverter.encode(obj.topics));
                jsonObject.put("blockNumber", LongConverter.encode(obj.blockNumber));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class TransactionDetailsConverter{
        public static TransactionDetails decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new TransactionDetails( AionAddressConverter.decode(jsonObject.opt("contractAddress")) , Byte32StringConverter.decode(jsonObject.opt("hash")) , IntegerConverter.decode(jsonObject.opt("transactionIndex")) , BigIntegerHexStringConverter.decode(jsonObject.opt("value")) , LongHexStringConverter.decode(jsonObject.opt("nrg")) , LongHexStringConverter.decode(jsonObject.opt("nrgPrice")) , LongHexStringConverter.decode(jsonObject.opt("gas")) , LongHexStringConverter.decode(jsonObject.opt("gasPrice")) , DataHexStringConverter.decode(jsonObject.opt("nonce")) , AionAddressConverter.decode(jsonObject.opt("from")) , AionAddressConverter.decode(jsonObject.opt("to")) , LongConverter.decode(jsonObject.opt("timestamp")) , DataHexStringConverter.decode(jsonObject.opt("input")) , LongHexStringConverter.decode(jsonObject.opt("blockNumber")) , Byte32StringConverter.decode(jsonObject.opt("blockHash")) , StringConverter.decode(jsonObject.opt("error")) , ByteHexStringConverter.decode(jsonObject.opt("type")) , LongHexStringConverter.decode(jsonObject.opt("nrgUsed")) , LongHexStringConverter.decode(jsonObject.opt("gasUsed")) , BooleanConverter.decode(jsonObject.opt("hasInternalTransactions")) , TxLogDetailsListConverter.decode(jsonObject.opt("logs")) , Byte32StringConverter.decode(jsonObject.opt("beaconHash")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( TransactionDetails obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("contractAddress", AionAddressConverter.encode(obj.contractAddress));
                jsonObject.put("hash", Byte32StringConverter.encode(obj.hash));
                jsonObject.put("transactionIndex", IntegerConverter.encode(obj.transactionIndex));
                jsonObject.put("value", BigIntegerHexStringConverter.encode(obj.value));
                jsonObject.put("nrg", LongHexStringConverter.encode(obj.nrg));
                jsonObject.put("nrgPrice", LongHexStringConverter.encode(obj.nrgPrice));
                jsonObject.put("gas", LongHexStringConverter.encode(obj.gas));
                jsonObject.put("gasPrice", LongHexStringConverter.encode(obj.gasPrice));
                jsonObject.put("nonce", DataHexStringConverter.encode(obj.nonce));
                jsonObject.put("from", AionAddressConverter.encode(obj.from));
                jsonObject.put("to", AionAddressConverter.encode(obj.to));
                jsonObject.put("timestamp", LongConverter.encode(obj.timestamp));
                jsonObject.put("input", DataHexStringConverter.encode(obj.input));
                jsonObject.put("blockNumber", LongHexStringConverter.encode(obj.blockNumber));
                jsonObject.put("blockHash", Byte32StringConverter.encode(obj.blockHash));
                jsonObject.put("error", StringConverter.encode(obj.error));
                jsonObject.put("type", ByteHexStringConverter.encode(obj.type));
                jsonObject.put("nrgUsed", LongHexStringConverter.encode(obj.nrgUsed));
                jsonObject.put("gasUsed", LongHexStringConverter.encode(obj.gasUsed));
                jsonObject.put("hasInternalTransactions", BooleanConverter.encode(obj.hasInternalTransactions));
                jsonObject.put("logs", TxLogDetailsListConverter.encode(obj.logs));
                jsonObject.put("beaconHash", Byte32StringConverter.encode(obj.beaconHash));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( TransactionDetails obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("contractAddress", AionAddressConverter.encode(obj.contractAddress));
                jsonObject.put("hash", Byte32StringConverter.encode(obj.hash));
                jsonObject.put("transactionIndex", IntegerConverter.encode(obj.transactionIndex));
                jsonObject.put("value", BigIntegerHexStringConverter.encode(obj.value));
                jsonObject.put("nrg", LongHexStringConverter.encode(obj.nrg));
                jsonObject.put("nrgPrice", LongHexStringConverter.encode(obj.nrgPrice));
                jsonObject.put("gas", LongHexStringConverter.encode(obj.gas));
                jsonObject.put("gasPrice", LongHexStringConverter.encode(obj.gasPrice));
                jsonObject.put("nonce", DataHexStringConverter.encode(obj.nonce));
                jsonObject.put("from", AionAddressConverter.encode(obj.from));
                jsonObject.put("to", AionAddressConverter.encode(obj.to));
                jsonObject.put("timestamp", LongConverter.encode(obj.timestamp));
                jsonObject.put("input", DataHexStringConverter.encode(obj.input));
                jsonObject.put("blockNumber", LongHexStringConverter.encode(obj.blockNumber));
                jsonObject.put("blockHash", Byte32StringConverter.encode(obj.blockHash));
                jsonObject.put("error", StringConverter.encode(obj.error));
                jsonObject.put("type", ByteHexStringConverter.encode(obj.type));
                jsonObject.put("nrgUsed", LongHexStringConverter.encode(obj.nrgUsed));
                jsonObject.put("gasUsed", LongHexStringConverter.encode(obj.gasUsed));
                jsonObject.put("hasInternalTransactions", BooleanConverter.encode(obj.hasInternalTransactions));
                jsonObject.put("logs", TxLogDetailsListConverter.encode(obj.logs));
                jsonObject.put("beaconHash", Byte32StringConverter.encode(obj.beaconHash));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class BlockDetailsConverter{
        public static BlockDetails decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new BlockDetails( LongConverter.decode(jsonObject.opt("number")) , Byte32StringConverter.decode(jsonObject.opt("hash")) , Byte32StringConverter.decode(jsonObject.opt("parentHash")) , ByteArrayConverter.decode(jsonObject.opt("logsBloom")) , DataHexStringConverter.decode(jsonObject.opt("transactionsRoot")) , DataHexStringConverter.decode(jsonObject.opt("statesRoot")) , DataHexStringConverter.decode(jsonObject.opt("receiptsRoot")) , BigIntegerHexStringConverter.decode(jsonObject.opt("difficulty")) , BigIntegerHexStringConverter.decode(jsonObject.opt("totalDifficulty")) , AionAddressConverter.decode(jsonObject.opt("miner")) , LongHexStringConverter.decode(jsonObject.opt("timestamp")) , LongHexStringConverter.decode(jsonObject.opt("gasUsed")) , LongHexStringConverter.decode(jsonObject.opt("gasLimit")) , LongHexStringConverter.decode(jsonObject.opt("nrgUsed")) , LongHexStringConverter.decode(jsonObject.opt("nrgLimit")) , ByteHexStringConverter.decode(jsonObject.opt("sealType")) , BooleanConverter.decode(jsonObject.opt("mainChain")) , DataHexStringConverter.decode(jsonObject.opt("extraData")) , IntegerConverter.decode(jsonObject.opt("size")) , IntegerConverter.decode(jsonObject.opt("numTransactions")) , DataHexStringConverter.decode(jsonObject.opt("txTrieRoot")) , BigIntegerHexStringConverter.decode(jsonObject.opt("blockReward")) , TransactionDetailsListConverter.decode(jsonObject.opt("transactions")) , DataHexStringConverter.decode(jsonObject.opt("nonce")) , DataHexStringConverter.decode(jsonObject.opt("solution")) , DataHexStringConverter.decode(jsonObject.opt("seed")) , DataHexStringConverter.decode(jsonObject.opt("signature")) , DataHexStringConverter.decode(jsonObject.opt("publicKey")) , IntegerConverter.decode(jsonObject.opt("blockTime")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( BlockDetails obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("number", LongConverter.encode(obj.number));
                jsonObject.put("hash", Byte32StringConverter.encode(obj.hash));
                jsonObject.put("parentHash", Byte32StringConverter.encode(obj.parentHash));
                jsonObject.put("logsBloom", ByteArrayConverter.encode(obj.logsBloom));
                jsonObject.put("transactionsRoot", DataHexStringConverter.encode(obj.transactionsRoot));
                jsonObject.put("statesRoot", DataHexStringConverter.encode(obj.statesRoot));
                jsonObject.put("receiptsRoot", DataHexStringConverter.encode(obj.receiptsRoot));
                jsonObject.put("difficulty", BigIntegerHexStringConverter.encode(obj.difficulty));
                jsonObject.put("totalDifficulty", BigIntegerHexStringConverter.encode(obj.totalDifficulty));
                jsonObject.put("miner", AionAddressConverter.encode(obj.miner));
                jsonObject.put("timestamp", LongHexStringConverter.encode(obj.timestamp));
                jsonObject.put("gasUsed", LongHexStringConverter.encode(obj.gasUsed));
                jsonObject.put("gasLimit", LongHexStringConverter.encode(obj.gasLimit));
                jsonObject.put("nrgUsed", LongHexStringConverter.encode(obj.nrgUsed));
                jsonObject.put("nrgLimit", LongHexStringConverter.encode(obj.nrgLimit));
                jsonObject.put("sealType", ByteHexStringConverter.encode(obj.sealType));
                jsonObject.put("mainChain", BooleanConverter.encode(obj.mainChain));
                jsonObject.put("extraData", DataHexStringConverter.encode(obj.extraData));
                jsonObject.put("size", IntegerConverter.encode(obj.size));
                jsonObject.put("numTransactions", IntegerConverter.encode(obj.numTransactions));
                jsonObject.put("txTrieRoot", DataHexStringConverter.encode(obj.txTrieRoot));
                jsonObject.put("blockReward", BigIntegerHexStringConverter.encode(obj.blockReward));
                jsonObject.put("transactions", TransactionDetailsListConverter.encode(obj.transactions));
                jsonObject.put("nonce", DataHexStringConverter.encode(obj.nonce));
                jsonObject.put("solution", DataHexStringConverter.encode(obj.solution));
                jsonObject.put("seed", DataHexStringConverter.encode(obj.seed));
                jsonObject.put("signature", DataHexStringConverter.encode(obj.signature));
                jsonObject.put("publicKey", DataHexStringConverter.encode(obj.publicKey));
                jsonObject.put("blockTime", IntegerConverter.encode(obj.blockTime));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( BlockDetails obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("number", LongConverter.encode(obj.number));
                jsonObject.put("hash", Byte32StringConverter.encode(obj.hash));
                jsonObject.put("parentHash", Byte32StringConverter.encode(obj.parentHash));
                jsonObject.put("logsBloom", ByteArrayConverter.encode(obj.logsBloom));
                jsonObject.put("transactionsRoot", DataHexStringConverter.encode(obj.transactionsRoot));
                jsonObject.put("statesRoot", DataHexStringConverter.encode(obj.statesRoot));
                jsonObject.put("receiptsRoot", DataHexStringConverter.encode(obj.receiptsRoot));
                jsonObject.put("difficulty", BigIntegerHexStringConverter.encode(obj.difficulty));
                jsonObject.put("totalDifficulty", BigIntegerHexStringConverter.encode(obj.totalDifficulty));
                jsonObject.put("miner", AionAddressConverter.encode(obj.miner));
                jsonObject.put("timestamp", LongHexStringConverter.encode(obj.timestamp));
                jsonObject.put("gasUsed", LongHexStringConverter.encode(obj.gasUsed));
                jsonObject.put("gasLimit", LongHexStringConverter.encode(obj.gasLimit));
                jsonObject.put("nrgUsed", LongHexStringConverter.encode(obj.nrgUsed));
                jsonObject.put("nrgLimit", LongHexStringConverter.encode(obj.nrgLimit));
                jsonObject.put("sealType", ByteHexStringConverter.encode(obj.sealType));
                jsonObject.put("mainChain", BooleanConverter.encode(obj.mainChain));
                jsonObject.put("extraData", DataHexStringConverter.encode(obj.extraData));
                jsonObject.put("size", IntegerConverter.encode(obj.size));
                jsonObject.put("numTransactions", IntegerConverter.encode(obj.numTransactions));
                jsonObject.put("txTrieRoot", DataHexStringConverter.encode(obj.txTrieRoot));
                jsonObject.put("blockReward", BigIntegerHexStringConverter.encode(obj.blockReward));
                jsonObject.put("transactions", TransactionDetailsListConverter.encode(obj.transactions));
                jsonObject.put("nonce", DataHexStringConverter.encode(obj.nonce));
                jsonObject.put("solution", DataHexStringConverter.encode(obj.solution));
                jsonObject.put("seed", DataHexStringConverter.encode(obj.seed));
                jsonObject.put("signature", DataHexStringConverter.encode(obj.signature));
                jsonObject.put("publicKey", DataHexStringConverter.encode(obj.publicKey));
                jsonObject.put("blockTime", IntegerConverter.encode(obj.blockTime));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class BlockTemplateConverter{
        public static BlockTemplate decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new BlockTemplate( Byte32StringConverter.decode(jsonObject.opt("previousblockhash")) , LongConverter.decode(jsonObject.opt("height")) , Uint256IntHexStringConverter.decode(jsonObject.opt("target")) , Byte32StringConverter.decode(jsonObject.opt("headerHash")) , BigIntegerHexStringConverter.decode(jsonObject.opt("blockBaseReward")) , BigIntegerHexStringConverter.decode(jsonObject.opt("blockTxFee")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( BlockTemplate obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("previousblockhash", Byte32StringConverter.encode(obj.previousblockhash));
                jsonObject.put("height", LongConverter.encode(obj.height));
                jsonObject.put("target", Uint256IntHexStringConverter.encode(obj.target));
                jsonObject.put("headerHash", Byte32StringConverter.encode(obj.headerHash));
                jsonObject.put("blockBaseReward", BigIntegerHexStringConverter.encode(obj.blockBaseReward));
                jsonObject.put("blockTxFee", BigIntegerHexStringConverter.encode(obj.blockTxFee));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( BlockTemplate obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("previousblockhash", Byte32StringConverter.encode(obj.previousblockhash));
                jsonObject.put("height", LongConverter.encode(obj.height));
                jsonObject.put("target", Uint256IntHexStringConverter.encode(obj.target));
                jsonObject.put("headerHash", Byte32StringConverter.encode(obj.headerHash));
                jsonObject.put("blockBaseReward", BigIntegerHexStringConverter.encode(obj.blockBaseReward));
                jsonObject.put("blockTxFee", BigIntegerHexStringConverter.encode(obj.blockTxFee));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class SubmissionResultConverter{
        public static SubmissionResult decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new SubmissionResult( BooleanConverter.decode(jsonObject.opt("result")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( SubmissionResult obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", BooleanConverter.encode(obj.result));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( SubmissionResult obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("result", BooleanConverter.encode(obj.result));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class ValidateAddressResultConverter{
        public static ValidateAddressResult decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new ValidateAddressResult( BooleanConverter.decode(jsonObject.opt("isvalid")) , AionAddressConverter.decode(jsonObject.opt("address")) , BooleanConverter.decode(jsonObject.opt("ismine")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( ValidateAddressResult obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("isvalid", BooleanConverter.encode(obj.isvalid));
                jsonObject.put("address", AionAddressConverter.encode(obj.address));
                jsonObject.put("ismine", BooleanConverter.encode(obj.ismine));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( ValidateAddressResult obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("isvalid", BooleanConverter.encode(obj.isvalid));
                jsonObject.put("address", AionAddressConverter.encode(obj.address));
                jsonObject.put("ismine", BooleanConverter.encode(obj.ismine));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class MinerStatsConverter{
        public static MinerStats decode(Object str){
            try{
                if(str==null || str.equals(JSONObject.NULL)) return null;
                JSONObject jsonObject = str instanceof JSONObject? (JSONObject)str :new JSONObject(str.toString());
                return new MinerStats( DecimalStringConverter.decode(jsonObject.opt("networkHashRate")) , DecimalStringConverter.decode(jsonObject.opt("minerHashrate")) , DecimalStringConverter.decode(jsonObject.opt("minerHashrateShare")) );
            } catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encodeStr( MinerStats obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("networkHashRate", DecimalStringConverter.encode(obj.networkHashRate));
                jsonObject.put("minerHashrate", DecimalStringConverter.encode(obj.minerHashrate));
                jsonObject.put("minerHashrateShare", DecimalStringConverter.encode(obj.minerHashrateShare));
                return jsonObject.toString();
            }
            catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static Object encode( MinerStats obj){
            try{
                if(obj==null) return null;
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("networkHashRate", DecimalStringConverter.encode(obj.networkHashRate));
                jsonObject.put("minerHashrate", DecimalStringConverter.encode(obj.minerHashrate));
                jsonObject.put("minerHashrateShare", DecimalStringConverter.encode(obj.minerHashrateShare));
                return jsonObject;
            }catch (Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class DataHexStringConverter{
        private static final Pattern regex = Pattern.compile("^0x([0-9a-fA-F][0-9a-fA-F])*$");

        public static ByteArray decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return ByteArrayConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(ByteArray obj){
            if (obj != null){
                String result = ByteArrayConverter.encode(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 2 && s.length() <= 2147483647;
        }
    }

    public static class BigIntegerHexStringConverter{
        private static final Pattern regex = Pattern.compile("^0x[0-9a-fA-F]+$");

        public static BigInteger decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return BigIntegerConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(BigInteger obj){
            if (obj != null){
                String result = BigIntegerConverter.encodeHex(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 3 && s.length() <= 2147483647;
        }
    }

    public static class Uint256IntHexStringConverter{
        private static final Pattern regex = Pattern.compile("^0x[0-9a-fA-F]+$");

        public static BigInteger decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return UnsignedInteger256Converter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(BigInteger obj){
            if (obj != null){
                String result = UnsignedInteger256Converter.encodeHex(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 3 && s.length() <= 2147483647;
        }
    }

    public static class LongHexStringConverter{
        private static final Pattern regex = Pattern.compile("^0x[0-9a-fA-F]+$");

        public static Long decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return LongConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(Long obj){
            if (obj != null){
                String result = LongConverter.encodeHex(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 3 && s.length() <= 18;
        }
    }

    public static class IntHexStringConverter{
        private static final Pattern regex = Pattern.compile("^0x[0-9a-fA-F]+$");

        public static Integer decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return IntegerConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(Integer obj){
            if (obj != null){
                String result = IntegerConverter.encodeHex(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 3 && s.length() <= 10;
        }
    }

    public static class ByteHexStringConverter{
        private static final Pattern regex = Pattern.compile("^0x[0-9a-fA-F]+$");

        public static Byte decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return ByteConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(Byte obj){
            if (obj != null){
                String result = ByteConverter.encodeHex(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 3 && s.length() <= 4;
        }
    }

    public static class Byte32StringConverter{
        private static final Pattern regex = Pattern.compile(".*");

        public static ByteArray decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return DataHexStringConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(ByteArray obj){
            if (obj != null){
                String result = DataHexStringConverter.encode(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 66 && s.length() <= 66;
        }
    }

    public static class Byte64StringConverter{
        private static final Pattern regex = Pattern.compile(".*");

        public static ByteArray decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return DataHexStringConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(ByteArray obj){
            if (obj != null){
                String result = DataHexStringConverter.encode(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 130 && s.length() <= 130;
        }
    }

    public static class DecimalStringConverter{
        private static final Pattern regex = Pattern.compile("^([+-]([1-9][0-9]*|[0-9]+[.][0-9])|[0-9]+([.][0-9])?)[0-9]*$");

        public static String decode(Object object){
            try{
                if(object==null || object.equals(JSONObject.NULL)) return null;
                else if (checkConstraints(object.toString())){
                    return StringConverter.decode(object);
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            } catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }

        public static String encode(String obj){
            if (obj != null){
                String result = StringConverter.encode(obj);
                if(checkConstraints(result))
                    return result;
                else
                    throw ParseErrorRPCException.INSTANCE;
            }
            else{
                return null;
            }
        }

        private static boolean checkConstraints(String s){
            return regex.matcher(s).find() && s.length() >= 1 && s.length() <= 2147483647;
        }
    }

    public static class EcRecoverParamsConverter{
        public static EcRecoverParams decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            String s = object.toString();
            try{
                EcRecoverParams obj;
                if(s.startsWith("[") && s.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 2) throw ParseErrorRPCException.INSTANCE;
                    else obj = new EcRecoverParams( DataHexStringConverter.decode(jsonArray.opt(0)), DataHexStringConverter.decode(jsonArray.opt(1)));
                }
                else if(s.startsWith("{") && s.endsWith("}")){
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.keySet().size() > 2) throw ParseErrorRPCException.INSTANCE;
                    else obj = new EcRecoverParams( DataHexStringConverter.decode(jsonObject.opt("dataThatWasSigned")), DataHexStringConverter.decode(jsonObject.opt("signature")));
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                return obj;
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(EcRecoverParams obj){
            try{
                JSONArray arr = new JSONArray();
                arr.put(0, DataHexStringConverter.encode(obj.dataThatWasSigned));
                arr.put(1, DataHexStringConverter.encode(obj.signature));
                return arr;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class BlockSpecifierConverter{
        public static BlockSpecifier decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            String s = object.toString();
            try{
                BlockSpecifier obj;
                if(s.startsWith("[") && s.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 1) throw ParseErrorRPCException.INSTANCE;
                    else obj = new BlockSpecifier( BlockSpecifierUnionConverter.decode(jsonArray.opt(0)));
                }
                else if(s.startsWith("{") && s.endsWith("}")){
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.keySet().size() > 1) throw ParseErrorRPCException.INSTANCE;
                    else obj = new BlockSpecifier( BlockSpecifierUnionConverter.decode(jsonObject.opt("block")));
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                return obj;
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(BlockSpecifier obj){
            try{
                JSONArray arr = new JSONArray();
                arr.put(0, BlockSpecifierUnionConverter.encode(obj.block));
                return arr;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class SubmitSeedParamsConverter{
        public static SubmitSeedParams decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            String s = object.toString();
            try{
                SubmitSeedParams obj;
                if(s.startsWith("[") && s.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 3) throw ParseErrorRPCException.INSTANCE;
                    else obj = new SubmitSeedParams( Byte64StringConverter.decode(jsonArray.opt(0)), Byte32StringConverter.decode(jsonArray.opt(1)), AionAddressConverter.decode(jsonArray.opt(2)));
                }
                else if(s.startsWith("{") && s.endsWith("}")){
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.keySet().size() > 3) throw ParseErrorRPCException.INSTANCE;
                    else obj = new SubmitSeedParams( Byte64StringConverter.decode(jsonObject.opt("newSeed")), Byte32StringConverter.decode(jsonObject.opt("signingPublicKey")), AionAddressConverter.decode(jsonObject.opt("coinbase")));
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                return obj;
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(SubmitSeedParams obj){
            try{
                JSONArray arr = new JSONArray();
                arr.put(0, Byte64StringConverter.encode(obj.newSeed));
                arr.put(1, Byte32StringConverter.encode(obj.signingPublicKey));
                arr.put(2, AionAddressConverter.encode(obj.coinbase));
                return arr;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class SubmitSignatureParamsConverter{
        public static SubmitSignatureParams decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            String s = object.toString();
            try{
                SubmitSignatureParams obj;
                if(s.startsWith("[") && s.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 2) throw ParseErrorRPCException.INSTANCE;
                    else obj = new SubmitSignatureParams( Byte64StringConverter.decode(jsonArray.opt(0)), Byte32StringConverter.decode(jsonArray.opt(1)));
                }
                else if(s.startsWith("{") && s.endsWith("}")){
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.keySet().size() > 2) throw ParseErrorRPCException.INSTANCE;
                    else obj = new SubmitSignatureParams( Byte64StringConverter.decode(jsonObject.opt("signature")), Byte32StringConverter.decode(jsonObject.opt("sealHash")));
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                return obj;
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(SubmitSignatureParams obj){
            try{
                JSONArray arr = new JSONArray();
                arr.put(0, Byte64StringConverter.encode(obj.signature));
                arr.put(1, Byte32StringConverter.encode(obj.sealHash));
                return arr;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class VoidParamsConverter{
        public static VoidParams decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return new VoidParams();
            String s = object.toString();
            try{
                if(s.equals("[]") || s.equals("{}")) {//TODO This may not be the best way to handle an empty param list
                    return new VoidParams();
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(VoidParams obj){
            try{
                return null;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class SubmitBlockParamsConverter{
        public static SubmitBlockParams decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            String s = object.toString();
            try{
                SubmitBlockParams obj;
                if(s.startsWith("[") && s.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 3) throw ParseErrorRPCException.INSTANCE;
                    else obj = new SubmitBlockParams( DataHexStringConverter.decode(jsonArray.opt(0)), DataHexStringConverter.decode(jsonArray.opt(1)), Byte32StringConverter.decode(jsonArray.opt(2)));
                }
                else if(s.startsWith("{") && s.endsWith("}")){
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.keySet().size() > 3) throw ParseErrorRPCException.INSTANCE;
                    else obj = new SubmitBlockParams( DataHexStringConverter.decode(jsonObject.opt("nonce")), DataHexStringConverter.decode(jsonObject.opt("solution")), Byte32StringConverter.decode(jsonObject.opt("headerHash")));
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                return obj;
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(SubmitBlockParams obj){
            try{
                JSONArray arr = new JSONArray();
                arr.put(0, DataHexStringConverter.encode(obj.nonce));
                arr.put(1, DataHexStringConverter.encode(obj.solution));
                arr.put(2, Byte32StringConverter.encode(obj.headerHash));
                return arr;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class AddressParamsConverter{
        public static AddressParams decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            String s = object.toString();
            try{
                AddressParams obj;
                if(s.startsWith("[") && s.endsWith("]")){
                    JSONArray jsonArray = new JSONArray(s);
                    if(jsonArray.length() > 1) throw ParseErrorRPCException.INSTANCE;
                    else obj = new AddressParams( AionAddressConverter.decode(jsonArray.opt(0)));
                }
                else if(s.startsWith("{") && s.endsWith("}")){
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.keySet().size() > 1) throw ParseErrorRPCException.INSTANCE;
                    else obj = new AddressParams( AionAddressConverter.decode(jsonObject.opt("address")));
                }
                else{
                    throw ParseErrorRPCException.INSTANCE;
                }
                return obj;
            }
            catch(Exception e){
                throw InvalidParamsRPCException.INSTANCE;
            }
        }

        public static Object encode(AddressParams obj){
            try{
                JSONArray arr = new JSONArray();
                arr.put(0, AionAddressConverter.encode(obj.address));
                return arr;
            }catch(Exception e){
                throw ParseErrorRPCException.INSTANCE;
            }
        }
    }

    public static class VersionTypeConverter{
        public static VersionType decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            return VersionType.fromString(object.toString());
        }

        public static String encode(VersionType obj){
            if(obj==null) return null;
            return obj.x;
        }
    }

    public static class BlockEnumConverter{
        public static BlockEnum decode(Object object){
            if(object==null || object.equals(JSONObject.NULL)) return null;
            return BlockEnum.fromString(object.toString());
        }

        public static String encode(BlockEnum obj){
            if(obj==null) return null;
            return obj.x;
        }
    }

    public static class RequestListConverter{
        public static List<Request> decode(Object object){
            if(object == null || object.equals(JSONObject.NULL)) return null;
            JSONArray arr = new JSONArray(object.toString());
            List<Request> temp = new ArrayList<>();
            for(int i=0; i < arr.length(); i++){
                temp.add(RequestConverter.decode(arr.opt(i)));
            }
            return Collections.unmodifiableList(temp);
        }

        public static Object encode(List<Request> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();

            for(int i=0; i < list.size();i++){
                arr.put(RequestConverter.encode(list.get(i)));
            }
            return arr;
        }

        public static String encodesStr(List<Request> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();
            for(int i=0; i < list.size();i++){
                arr.put(RequestConverter.encode(list.get(i)));
            }
            return arr.toString();
        }
    }

    public static class ResponseListConverter{
        public static List<Response> decode(Object object){
            if(object == null || object.equals(JSONObject.NULL)) return null;
            JSONArray arr = new JSONArray(object.toString());
            List<Response> temp = new ArrayList<>();
            for(int i=0; i < arr.length(); i++){
                temp.add(ResponseConverter.decode(arr.opt(i)));
            }
            return Collections.unmodifiableList(temp);
        }

        public static Object encode(List<Response> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();

            for(int i=0; i < list.size();i++){
                arr.put(ResponseConverter.encode(list.get(i)));
            }
            return arr;
        }

        public static String encodesStr(List<Response> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();
            for(int i=0; i < list.size();i++){
                arr.put(ResponseConverter.encode(list.get(i)));
            }
            return arr.toString();
        }
    }

    public static class DataHexStringListConverter{
        public static List<ByteArray> decode(Object object){
            if(object == null || object.equals(JSONObject.NULL)) return null;
            JSONArray arr = new JSONArray(object.toString());
            List<ByteArray> temp = new ArrayList<>();
            for(int i=0; i < arr.length(); i++){
                temp.add(DataHexStringConverter.decode(arr.opt(i)));
            }
            return Collections.unmodifiableList(temp);
        }

        public static Object encode(List<ByteArray> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();

            for(int i=0; i < list.size();i++){
                arr.put(DataHexStringConverter.encode(list.get(i)));
            }
            return arr;
        }

        public static String encodesStr(List<ByteArray> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();
            for(int i=0; i < list.size();i++){
                arr.put(DataHexStringConverter.encode(list.get(i)));
            }
            return arr.toString();
        }
    }

    public static class TxLogDetailsListConverter{
        public static List<TxLogDetails> decode(Object object){
            if(object == null || object.equals(JSONObject.NULL)) return null;
            JSONArray arr = new JSONArray(object.toString());
            List<TxLogDetails> temp = new ArrayList<>();
            for(int i=0; i < arr.length(); i++){
                temp.add(TxLogDetailsConverter.decode(arr.opt(i)));
            }
            return Collections.unmodifiableList(temp);
        }

        public static Object encode(List<TxLogDetails> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();

            for(int i=0; i < list.size();i++){
                arr.put(TxLogDetailsConverter.encode(list.get(i)));
            }
            return arr;
        }

        public static String encodesStr(List<TxLogDetails> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();
            for(int i=0; i < list.size();i++){
                arr.put(TxLogDetailsConverter.encode(list.get(i)));
            }
            return arr.toString();
        }
    }

    public static class TransactionDetailsListConverter{
        public static List<TransactionDetails> decode(Object object){
            if(object == null || object.equals(JSONObject.NULL)) return null;
            JSONArray arr = new JSONArray(object.toString());
            List<TransactionDetails> temp = new ArrayList<>();
            for(int i=0; i < arr.length(); i++){
                temp.add(TransactionDetailsConverter.decode(arr.opt(i)));
            }
            return Collections.unmodifiableList(temp);
        }

        public static Object encode(List<TransactionDetails> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();

            for(int i=0; i < list.size();i++){
                arr.put(TransactionDetailsConverter.encode(list.get(i)));
            }
            return arr;
        }

        public static String encodesStr(List<TransactionDetails> list){
            if(list==null) return null;
            JSONArray arr = new JSONArray();
            for(int i=0; i < list.size();i++){
                arr.put(TransactionDetailsConverter.encode(list.get(i)));
            }
            return arr.toString();
        }
    }

}
