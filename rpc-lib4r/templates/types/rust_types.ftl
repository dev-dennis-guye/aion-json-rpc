<#import "../rust_macros.ftl" as macros>

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/

<#list unionTypes as unionType>
    <#if unionType.comments?has_content>
    /**
    <#list unionType.comments as comment>
    * ${comment}
    </#list>
    */
    </#if>
    enum ${macros.toRustType(unionType)} {
        <#list unionType.unionElements as unionElement >
        <#if unionElement.comments?has_content>
        /**<#list unionElement.comments as comment>
        * ${comment}
        </#list>*
        */
        </#if>
        ${macros.toCamelCase(unionElement.name)}(${macros.toRustType(unionElement.type)})
        </#list>
    }

</#list>
<#list compositeTypes as composite_type>
    <#if composite_type.comments?has_content>
    /**
    <#list composite_type.comments as comment>
    * ${comment}
    </#list>
    */
    </#if>
    pub struct ${macros.toRustType(composite_type)} {
    <#list composite_type.fields as field>
        <#if field.comments?has_content>
        /**
        <#list field.comments as comment>
        * ${comment}
        </#list>
        */
        </#if>
        pub ${field.fieldName}: ${macros.toRustType(field.type)},
    </#list>
    }

    impl ${macros.toRustType(composite_type)} {
        pub fn new(<#list composite_type.fields as field>${field.fieldName}: ${macros.toRustType(field.type)} <#if field_has_next>,</#if></#list>) -> Self {
            ${macros.toRustType(composite_type)} {
                <#list composite_type.fields as field><#if field.required=="true" >
                // TODO: check ${field.fieldName}
                </#if>
                ${field.fieldName},
                </#list>
            }
        }
    }

</#list>

<#list enumTypes as enum >
    <#if enum.comments?has_content>
        /**
        <#list enum.comments as comment>* ${comment}</#list>
        */
    </#if>
    public enum ${macros.toRustType(enum)} {
        <#list enum.values as value>
        ${value.name} = "${value.value}"<#if value_has_next>,</#if></#list>,
    }
</#list>

<#list paramTypes as paramType>
    <#if paramType.comments?has_content>
        /**
        <#list paramType.comments as comment>* ${comment}</#list>
        */
    </#if>
    pub struct ${macros.toRustType(paramType)} {
    <#list paramType.fields as field>
        <#if field.comments?has_content>
        /**
        <p><#list field.comments as comment>
        * ${comment}
        </#list></p>
        <#if field.defaultValue?has_content>* default value = ${field.defaultValue}</#if>
        */
        </#if>
        pub ${field.fieldName}: ${macros.toRustType(field.type)},
    </#list>
    }

    impl ${macros.toRustType(paramType)} {
        pub fn new(<#list paramType.fields as field>${field.fieldName}: ${macros.toRustType(field.type)}<#if field_has_next>, </#if></#list>) -> Self {
            ${macros.toRustType(paramType)} {
                <#list paramType.fields as field><#if field.required=="true" >
                // TODO: check ${field.fieldName}
                </#if>
                ${field.fieldName},
                </#list>
            }
        }
    }
</#list>
}
