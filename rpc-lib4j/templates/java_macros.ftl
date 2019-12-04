<#function toJavaType type>
    <#local typeName = type.name>
    <#if type.baseType?has_content>
        <#return toJavaType(type.baseType)>
    <#elseif type.nestedType?has_content>
        <#return "${toJavaType(type.nestedType)}[]">
    <#elseif typeName=="uint16">
        <#return "Integer">
    <#elseif typeName=="uint32">
        <#return "Long">
    <#elseif typeName=="uint64">
        <#return "BigInteger">
    <#elseif typeName=="uint128">
        <#return "BigInteger">
    <#elseif typeName=="uint256">
        <#return "BigInteger">
    <#elseif typeName=="any">
        <#return "Object">
    <#elseif typeName=="bool">
        <#return "Boolean">
    <#elseif typeName=="string">
        <#return "String">
    <#elseif typeName=="long">
        <#return "Long">
    <#elseif typeName=="int">
        <#return "Integer">
    <#elseif typeName=="bool">
        <#return "Boolean">
    <#elseif typeName=="byte">
        <#return "Byte">
    <#elseif typeName=="bigInt">
        <#return "BigInteger">
    <#elseif typeName=="byteArray">
        <#return "ByteArray">
    <#elseif typeName=="address">
        <#return "AionAddress">
    <#else>
        <#return toCamelCase(typeName)>
    </#if>
</#function>

<#function toJavaClassName className>
    <#if className=="personal">
        <#return "Personal"/>
    <#elseif className=="ops">
        <#return "Ops">
    <#elseif className=="stratum">
        <#return "Stratum">
    </#if>
</#function>

<#function toJavaException exceptionName>
    <#return "${exceptionName}RPCException">
</#function>

<#function toJavaConverter converterType>
    <#if converterType.nestedType?has_content>
        <#return "${toCamelCase(converterType.nestedType.name)}ListConverter">
    <#else >
        <#return "${toCamelCase(converterType.name)}Converter">
    </#if>
</#function>

<#function toJavaConverterFromName name>
    <#return "${toCamelCase(name)}Converter">
</#function>

<#function toCamelCase typeName>
    <#return "${typeName?cap_first}">
</#function>

<#function resultExtractorFromName type>
    <#if type.baseType?has_content><#return resultExtractorFromName(type.baseType)></#if>
    <#local name = type.name>
    <#if name=="byteArray">
        <#return "r->r.byteArray">
    <#elseif name=="address_array">
        <#return "r->r.addressArray">
    <#elseif name=="bigint">
        <#return "r->r.bigInt">
    <#else>
        <#return "r->r.${name}">
    </#if>
</#function>

<#function paramsExtractorFromName name>
    <#return name>
</#function>
