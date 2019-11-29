<#import "../java_macros.ftl" as macros/>
use std::collections::{HashMap,HashSet};


/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/
trait RPCServerMethods{
    /**
    *
    * @param request the client request
    * @param rpc the rpc implementation to be used in fulfilling this request.
    * @return the result of this request
    */
    fn execute(request : Request, rpc : RPCServerMethods ) -> Result<Response,Error>{
        //check that the request can be fulfilled by this class
        <#list methods as method>if request.method == "${method.name}" {
            let params : ${macros.toJavaType(method.param)} = match ${macros.toJavaConverter(method.param)}::decode(request.params) {
                Some(params) => params,
                None => {
                    return Err( Error::${macros.toJavaException("InvalidParams")});
                }
            };
            let result : ${macros.toJavaType(method.returnType)} = rpc.${method.name}(<#list method.param.fields as parameter>params.${parameter.fieldName}<#if parameter_has_next>,</#if></#list>)?;
            res = ${macros.toJavaConverter(method.returnType)}.encode(result);
        } else </#list>
        {
            return Err(Err::${macros.toJavaException("MethodNotFound")});
        }
        return res;
    }

    fn list_methods() -> HashSet<String>{
        let mut set = HashSet::new();
        <#list methods as method>
        set.insert( "${method.name}".to_string());
        </#list>
        set
    }

    <#list methods as method>
        <#if method.comments?has_content>
            <#list method.comments as comment>
    /// ${comment}
            </#list>
        </#if>
        <#if method.param.fields?has_content>
            <#list method.param.fields as parameter>
    /// param `${parameter.fieldName}` <#list parameter.comments as comment>${comment} <#if comment_has_next>
    /// </#if></#list>
            </#list>
        </#if>
    /// `Return` <#if method.returnType.comments?has_content><#list method.returnType.comments as comment>${comment}
    </#list>
    </#if>

    fn ${method.name}(<#list method.param.fields as parameter>${parameter.fieldName} : ${macros.toJavaType(parameter.type)}<#if parameter_has_next>, </#if></#list>) -> Result<${macros.toJavaType(method.returnType)},Error>;

    </#list>


    fn method_interface_map() -> HashMap<String,String>{
        let mut map = HashMap::new();
    <#list methods as method>
        map.insert("${method.name}".to_string(), "${method.namespace}".to_string());
    </#list>
        map
    }

}
