<#import "../rust_macros.ftl" as macros/>
package org.aion.rpc.errors;

import org.aion.rpc.types.RPCTypes.RPCError;
import org.aion.rpc.types.RPCTypesConverter.RPCErrorConverter;
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/
public class RPCExceptions{
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

        private final transient RPCError error;
        RPCException(String message){
            super(message);
            this.error = RPCErrorConverter.decode(message);
        }

        RPCException(Integer code ,String message){
            this("{\"code\":"+code+",\"message\":\""+message+"\"}");
        }

        /**
        * @return The rpc error that this class represents
        */
        public RPCError getError(){
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
