<#import "../rust_macros.ftl" as macros/>

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/
/**
* @param code the rpc error code
* @return the RPCException which maps to the supplied code. Otherwise an instance of InternalErrorRPCException.
*/
<#list errors as error>
pub fn ${macros.toRustClassName("${error.error_class}")}() -> Error {
    Error {
        code: ${error.code},
        message: "${error.message}".into(),
        data: None,
    }
}

</#list>
