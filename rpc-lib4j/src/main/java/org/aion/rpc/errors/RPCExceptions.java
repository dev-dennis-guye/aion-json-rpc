package org.aion.rpc.errors;

import org.aion.rpc.types.RPCTypes.RpcError;
import org.aion.rpc.types.RPCTypesConverter.RpcErrorConverter;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-12-12
*
*****************************************************************************/
public class RPCExceptions{

    public enum RpcCodeEnums{
        InvalidRequest_CODE(-32600),
        ParseError_CODE(-32700),
        MethodNotFound_CODE(-32601),
        InvalidParams_CODE(-32602),
        InternalError_CODE(-32603),
        UnsupportedUnityFeature_CODE(-32001),
        BlockTemplateNotFound_CODE(-32002),
        FailedToSealBlock_CODE(-32003),
        FailedToComputeMetrics_CODE(-32004);
        public final int code;
        private static RpcCodeEnums[] values = RpcCodeEnums.values();
        RpcCodeEnums(int code){
            this.code=code;
        }

        /**
        * Returns the enum constant for the specified error code
        * @param errorCode the error code of the constant to be returned
        * @throws IllegalArgumentException if a constant of {@link RpcCodeEnums} does not exist
        *                                   for the specified code
        * @return the enum matching the error code
        */
        public static RpcCodeEnums enumFromCode(int errorCode){
            for(RpcCodeEnums rpcEnum: values){
                if(errorCode == rpcEnum.code){
                    return rpcEnum;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    /**
    * @param code the rpc error code
    * @return the RPCException which maps to the supplied code. Otherwise an instance of InternalErrorRPCException.
    */
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

    /**
    * @param code the rpc error code
    * @param message the rpc message
    * @return the RPCException which contains the specified code and message if it can be created. Otherwise an instance of InternalErrorRPCException.
    */
    public static RPCException fromCodeAndMessage(int code, String message){
         if(code == -32601){
            return new MethodNotFoundRPCException(code, message);
        }else if(code == -32603){
            return new InternalErrorRPCException(code, message);
        }else
            return InternalErrorRPCException.INSTANCE;
    }

    public static class RPCException extends RuntimeException{

        private final transient RpcError error;
        RPCException(String message){
            super(message);
            this.error = RpcErrorConverter.decode(message);
        }

        RPCException(Integer code ,String message){
            this("{\"code\":"+code+",\"message\":\""+message+"\"}");
        }

        /**
        * @return The rpc error that this class represents
        */
        public RpcError getError(){
            return error;
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32600,"message":"Invalid Request"}.</p>
    <p></p>
    */
    public static class InvalidRequestRPCException extends RPCException{
        public static final InvalidRequestRPCException INSTANCE = new InvalidRequestRPCException();
        private InvalidRequestRPCException(){
            super("{\"code\":-32600,\"message\":\"Invalid Request\"}");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32700,"message":"Parse error"}.</p>
    <p>* Occurs if a user submits a malformed json payload
    </p>
    */
    public static class ParseErrorRPCException extends RPCException{
        public static final ParseErrorRPCException INSTANCE = new ParseErrorRPCException();
        private ParseErrorRPCException(){
            super("{\"code\":-32700,\"message\":\"Parse error\"}");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32601,"message":"Method not found"}.</p>
    <p></p>
    */
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

    /**
    * <p>Contains errors of the form {"code":-32602,"message":"Invalid params"}.</p>
    <p>* Occurs if a user fails to supply the correct parameters for a method
    </p>
    */
    public static class InvalidParamsRPCException extends RPCException{
        public static final InvalidParamsRPCException INSTANCE = new InvalidParamsRPCException();
        private InvalidParamsRPCException(){
            super("{\"code\":-32602,\"message\":\"Invalid params\"}");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32603,"message":"Internal error"}.</p>
    <p>* Occurs if the server failed to complete the request
    </p>
    */
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

    /**
    * <p>Contains errors of the form {"code":-32001,"message":"Unity fork is not enabled"}.</p>
    <p>* Occurs if a unity specific service is requested
    </p>
    */
    public static class UnsupportedUnityFeatureRPCException extends RPCException{
        public static final UnsupportedUnityFeatureRPCException INSTANCE = new UnsupportedUnityFeatureRPCException();
        private UnsupportedUnityFeatureRPCException(){
            super("{\"code\":-32001,\"message\":\"Unity fork is not enabled\"}");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32002,"message":"Could not find the block template for the supplied header hash."}.</p>
    <p></p>
    */
    public static class BlockTemplateNotFoundRPCException extends RPCException{
        public static final BlockTemplateNotFoundRPCException INSTANCE = new BlockTemplateNotFoundRPCException();
        private BlockTemplateNotFoundRPCException(){
            super("{\"code\":-32002,\"message\":\"Could not find the block template for the supplied header hash.\"}");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32003,"message":"Could not seal the pow block."}.</p>
    <p></p>
    */
    public static class FailedToSealBlockRPCException extends RPCException{
        public static final FailedToSealBlockRPCException INSTANCE = new FailedToSealBlockRPCException();
        private FailedToSealBlockRPCException(){
            super("{\"code\":-32003,\"message\":\"Could not seal the pow block.\"}");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32004,"message":"Could not compute the POW metrics."}.</p>
    <p></p>
    */
    public static class FailedToComputeMetricsRPCException extends RPCException{
        public static final FailedToComputeMetricsRPCException INSTANCE = new FailedToComputeMetricsRPCException();
        private FailedToComputeMetricsRPCException(){
            super("{\"code\":-32004,\"message\":\"Could not compute the POW metrics.\"}");
        }
    }

}
