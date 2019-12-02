<#import "../java_macros.ftl" as macros/>
package org.aion.rpc.errors;

import org.aion.rpc.types.RPCTypes.RpcError;
import org.aion.rpc.types.RPCTypesConverter.RpcErrorConverter;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/
public class RPCExceptions{

    public enum RpcCodeEnums{
        <#list errors as error>${error.error_class?cap_first}_CODE(${error.code})<#if error_has_next>,
        </#if></#list>;
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
        <#list errors as error>if(code == ${error.code}){
            return ${macros.toJavaException(error.error_class)}.INSTANCE;
        }
        else </#list>
            return ${macros.toJavaException("InternalError")}.INSTANCE;
    }

    /**
    * @param code the rpc error code
    * @param message the rpc message
    * @return the RPCException which contains the specified code and message if it can be created. Otherwise an instance of InternalErrorRPCException.
    */
    public static RPCException fromCodeAndMessage(int code, String message){
        <#list errors as error><#if error.modifiable=="true"> if(code == ${error.code}){
            return new ${macros.toJavaException(error.error_class)}(code, message);
        }else</#if></#list>
            return ${macros.toJavaException("InternalError")}.INSTANCE;
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

    <#list errors as error>
    /**
    * <p>Contains errors of the form {"code":${error.code},"message":"${error.message}"}.</p>
    <p><#list error.comments as comment>* ${comment}
    </#list></p>
    */
    public static class ${macros.toJavaException(error.error_class)} extends RPCException{
        public static final ${macros.toJavaException(error.error_class)} INSTANCE = new ${macros.toJavaException(error.error_class)}();
        private ${macros.toJavaException(error.error_class)}(){
            super("{\"code\":${error.code},\"message\":\"${error.message}\"}");
        }<#if error.modifiable=="true">
        public ${macros.toJavaException(error.error_class)}(String appendedMessage){
            super("{\"code\":${error.code},\"message\":\"${error.message}:"+appendedMessage+"\"}");
        }
        ${macros.toJavaException(error.error_class)}(Integer code, String message){
            super(code,message);
        }</#if>
    }

    </#list>
}
