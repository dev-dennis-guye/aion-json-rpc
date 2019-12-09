<#import "../rust_macros.ftl" as macros/>

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/

build_rpc_trait! {
    pub trait RpcServer {
        <#list methods as method>
        #[rpc(name = "${method.name}")]
        fn ${macros.toSnakeCase(method.name)}(&self, ${macros.toRustType(method.param)}) -> Result<${macros.toRustType(method.returnType)}>;

        </#list>
    }
}
