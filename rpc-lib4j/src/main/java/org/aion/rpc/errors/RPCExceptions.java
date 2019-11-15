package org.aion.rpc.errors;

import org.aion.rpc.types.RPCTypes.RPCError;
import org.aion.rpc.types.RPCTypesConverter.RPCErrorConverter;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-11-15
*
*****************************************************************************/
public class RPCExceptions{
    public static RPCException fromCode(int code){
        if(code == -32600){
            return InvalidRequestRPCException.INSTANCE;
        }
        else if(code == -32700){
            return ParseErrorRPCException.INSTANCE;
        }
        else if(code == -32601){
            return MethodNotFoundRPCException.INSTANCE;
        }
        else if(code == -32602){
            return InvalidParamsRPCException.INSTANCE;
        }
        else if(code == -32603){
            return InternalErrorRPCException.INSTANCE;
        }
        else if(code == -32001){
            return UnsupportedUnityFeatureRPCException.INSTANCE;
        }
        else if(code == -32002){
            return BlockTemplateNotFoundRPCException.INSTANCE;
        }
        else if(code == -32003){
            return FailedToSealBlockRPCException.INSTANCE;
        }
        else if(code == -32004){
            return FailedToComputeMetricsRPCException.INSTANCE;
        }
        else 
            return InternalErrorRPCException.INSTANCE;
    }

    public static RPCException fromCodeAndMessage(int code, String message){
         if(code == -32601){
            return new MethodNotFoundRPCException(code, message);
        }else if(code == -32603){
            return new InternalErrorRPCException(code, message);
        }else
            return InternalErrorRPCException.INSTANCE;
    }

    public static class RPCException extends RuntimeException{

        private final transient RPCError error;
        RPCException(String message){
            super(message);
            this.error = RPCErrorConverter.decode(message);
        }

        RPCException(Integer code ,String message){
            this("{\"code\":"+code+",\"message\":\""+message+"\"}");
        }

        public RPCError getError(){
            return error;
        }
    }

    public static class InvalidRequestRPCException extends RPCException{
        public static final InvalidRequestRPCException INSTANCE = new InvalidRequestRPCException();
        private InvalidRequestRPCException(){
            super("{\"code\":-32600,\"message\":\"Invalid Request\"}");
        }
    }

    public static class ParseErrorRPCException extends RPCException{
        public static final ParseErrorRPCException INSTANCE = new ParseErrorRPCException();
        private ParseErrorRPCException(){
            super("{\"code\":-32700,\"message\":\"Parse error\"}");
        }
    }

    public static class MethodNotFoundRPCException extends RPCException{
        public static final MethodNotFoundRPCException INSTANCE = new MethodNotFoundRPCException();
        private MethodNotFoundRPCException(){
            super("{\"code\":-32601,\"message\":\"Method not found\"}");
        }
        public MethodNotFoundRPCException(String appendedMessage){
            super("{\"code\":-32601,\"message\":\"Method not found:"+appendedMessage+"\"}");
        }
        MethodNotFoundRPCException(Integer code, String message){
            super(code,message);
        }
    }

    public static class InvalidParamsRPCException extends RPCException{
        public static final InvalidParamsRPCException INSTANCE = new InvalidParamsRPCException();
        private InvalidParamsRPCException(){
            super("{\"code\":-32602,\"message\":\"Invalid params\"}");
        }
    }

    public static class InternalErrorRPCException extends RPCException{
        public static final InternalErrorRPCException INSTANCE = new InternalErrorRPCException();
        private InternalErrorRPCException(){
            super("{\"code\":-32603,\"message\":\"Internal error\"}");
        }
        public InternalErrorRPCException(String appendedMessage){
            super("{\"code\":-32603,\"message\":\"Internal error:"+appendedMessage+"\"}");
        }
        InternalErrorRPCException(Integer code, String message){
            super(code,message);
        }
    }

    public static class UnsupportedUnityFeatureRPCException extends RPCException{
        public static final UnsupportedUnityFeatureRPCException INSTANCE = new UnsupportedUnityFeatureRPCException();
        private UnsupportedUnityFeatureRPCException(){
            super("{\"code\":-32001,\"message\":\"Unity fork is not enabled\"}");
        }
    }

    public static class BlockTemplateNotFoundRPCException extends RPCException{
        public static final BlockTemplateNotFoundRPCException INSTANCE = new BlockTemplateNotFoundRPCException();
        private BlockTemplateNotFoundRPCException(){
            super("{\"code\":-32002,\"message\":\"Could not find the block template for the supplied header hash.\"}");
        }
    }

    public static class FailedToSealBlockRPCException extends RPCException{
        public static final FailedToSealBlockRPCException INSTANCE = new FailedToSealBlockRPCException();
        private FailedToSealBlockRPCException(){
            super("{\"code\":-32003,\"message\":\"Could not seal the pow block.\"}");
        }
    }

    public static class FailedToComputeMetricsRPCException extends RPCException{
        public static final FailedToComputeMetricsRPCException INSTANCE = new FailedToComputeMetricsRPCException();
        private FailedToComputeMetricsRPCException(){
            super("{\"code\":-32004,\"message\":\"Could not compute the POW metrics.\"}");
        }
    }

}
