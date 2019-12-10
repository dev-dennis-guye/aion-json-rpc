package org.aion.rpc.errors;

import org.aion.rpc.types.RPCTypes.RpcError;
import org.aion.rpc.types.RPCTypesConverter.RpcErrorConverter;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-12-11
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
        FailedToComputeMetrics_CODE(-32004),
        NullReturn_CODE(-32005),
        TxFailed_CODE(-32006);
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
        else if(code == -32005){
            return NullReturnRPCException.INSTANCE;
        }
        else if(code == -32006){
            return TxFailedRPCException.INSTANCE;
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
         if(code == -32700){
            return new ParseErrorRPCException(code, message);
        }else if(code == -32601){
            return new MethodNotFoundRPCException(code, message);
        }else if(code == -32603){
            return new InternalErrorRPCException(code, message);
        }else if(code == -32005){
            return new NullReturnRPCException(code, message);
        }else if(code == -32006){
            return new TxFailedRPCException(code, message);
        }else
            return InternalErrorRPCException.INSTANCE;
    }

    public static class RPCException extends RuntimeException{

        private final transient RpcError error;
        RPCException(final Integer code , final String message){
            super("{\"code\":"+code+",\"message\":\""+message+"\"}");
            error = new RpcError(code, message);
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
        public InvalidRequestRPCException(){
            super(-32600,"Invalid Request");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32700,"message":"Parse error"}.</p>
    <p>* Occurs if a user submits a malformed json payload
    </p>
    */
    public static class ParseErrorRPCException extends RPCException{
        public static final ParseErrorRPCException INSTANCE = new ParseErrorRPCException();
        public ParseErrorRPCException(){
            super(-32700,"Parse error");
        }
        public ParseErrorRPCException(String appendedMessage){
            super(-32700,"Parse error:"+appendedMessage);
        }
        ParseErrorRPCException(Integer code, String message){
            super(code,message);
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32601,"message":"Method not found"}.</p>
    <p></p>
    */
    public static class MethodNotFoundRPCException extends RPCException{
        public static final MethodNotFoundRPCException INSTANCE = new MethodNotFoundRPCException();
        public MethodNotFoundRPCException(){
            super(-32601,"Method not found");
        }
        public MethodNotFoundRPCException(String appendedMessage){
            super(-32601,"Method not found:"+appendedMessage);
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
        public InvalidParamsRPCException(){
            super(-32602,"Invalid params");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32603,"message":"Internal error"}.</p>
    <p>* Occurs if the server failed to complete the request
    </p>
    */
    public static class InternalErrorRPCException extends RPCException{
        public static final InternalErrorRPCException INSTANCE = new InternalErrorRPCException();
        public InternalErrorRPCException(){
            super(-32603,"Internal error");
        }
        public InternalErrorRPCException(String appendedMessage){
            super(-32603,"Internal error:"+appendedMessage);
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
        public UnsupportedUnityFeatureRPCException(){
            super(-32001,"Unity fork is not enabled");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32002,"message":"Could not find the block template for the supplied header hash."}.</p>
    <p></p>
    */
    public static class BlockTemplateNotFoundRPCException extends RPCException{
        public static final BlockTemplateNotFoundRPCException INSTANCE = new BlockTemplateNotFoundRPCException();
        public BlockTemplateNotFoundRPCException(){
            super(-32002,"Could not find the block template for the supplied header hash.");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32003,"message":"Could not seal the pow block."}.</p>
    <p></p>
    */
    public static class FailedToSealBlockRPCException extends RPCException{
        public static final FailedToSealBlockRPCException INSTANCE = new FailedToSealBlockRPCException();
        public FailedToSealBlockRPCException(){
            super(-32003,"Could not seal the pow block.");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32004,"message":"Could not compute the POW metrics."}.</p>
    <p></p>
    */
    public static class FailedToComputeMetricsRPCException extends RPCException{
        public static final FailedToComputeMetricsRPCException INSTANCE = new FailedToComputeMetricsRPCException();
        public FailedToComputeMetricsRPCException(){
            super(-32004,"Could not compute the POW metrics.");
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32005,"message":"Block chain rpc returned null"}.</p>
    <p></p>
    */
    public static class NullReturnRPCException extends RPCException{
        public static final NullReturnRPCException INSTANCE = new NullReturnRPCException();
        public NullReturnRPCException(){
            super(-32005,"Block chain rpc returned null");
        }
        public NullReturnRPCException(String appendedMessage){
            super(-32005,"Block chain rpc returned null:"+appendedMessage);
        }
        NullReturnRPCException(Integer code, String message){
            super(code,message);
        }
    }

    /**
    * <p>Contains errors of the form {"code":-32006,"message":"Failed to execute transaction"}.</p>
    <p>* Occurs when a transaction failed.
    * The error will always contain contextual information on why the transaction failed to execute.
    </p>
    */
    public static class TxFailedRPCException extends RPCException{
        public static final TxFailedRPCException INSTANCE = new TxFailedRPCException();
        public TxFailedRPCException(){
            super(-32006,"Failed to execute transaction");
        }
        public TxFailedRPCException(String appendedMessage){
            super(-32006,"Failed to execute transaction:"+appendedMessage);
        }
        TxFailedRPCException(Integer code, String message){
            super(code,message);
        }
    }

}
