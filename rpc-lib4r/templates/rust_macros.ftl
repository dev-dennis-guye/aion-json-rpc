<#function toRustParams type>
    <#local typeName = type.name>
    <#if typeName=="submitBlockParams">
        <#return ", H256, Bytes, H256">
    <#elseif typeName=="addressParams">
        <#return ", Address">
    <#elseif typeName=="ecRecoverParams">
        <#return ", Bytes, H512">
    <#elseif typeName=="voidParams">
        <#return "">
    <#elseif typeName=="submitSignatureParams">
        <#return ", H512,H256">
    <#elseif typeName=="submitSeedParams">
        <#return ", H512, H256, Address">
    <#else >
        <#return ", ${toRustType(type)}">
    </#if>
</#function>

<#function toRustType type>
    <#local typeName = type.name>
    <#if typeName=="byte_32_string">
        <#return "H256">
    <#elseif typeName=="byte_64_string">
        <#return "H512">
    <#elseif type.baseType?has_content>
        <#return toRustType(type.baseType)>
    <#elseif typeName=="uint16">
        <#return "u16">
    <#elseif typeName=="uint32">
        <#return "u32">
    <#elseif typeName=="uint64">
        <#return "u64">
    <#elseif typeName=="uint128">
        <#return "U128">
    <#elseif typeName=="uint256">
        <#return "U256">
    <#elseif typeName == "string">
        <#return "String">
    <#elseif typeName == "error">
        <#return "RPCError">
    <#elseif type.nestedType?has_content>
        <#return "Vec<${toRustType(type.nestedType)}>">
    <#elseif typeName == "byte">
        <#return "u8">
    <#elseif typeName == "bool">
        <#return "bool">
    <#elseif typeName == "byte-array">
        <#return "Bytes">
    <#elseif typeName == "blockSpecifier">
        <#return "BlockSpecifier">
    <#elseif typeName == "blockDetails">
        <#return "BlockDetails">
    <#elseif typeName == "blockEnum">
        <#return "BlockEnum">
    <#elseif typeName=="blockSpecifierUnion">
        <#return "BlockSpecifier">
    <#elseif typeName=="resultUnion">
        <#return "ResultUnion">
    <#elseif typeName=="paramUnion">
        <#return "ParamUnion">
    <#elseif typeName == "txDetails">
        <#return "TransactionDetails">
    <#elseif typeName == "address" >
        <#return "Address">
    <#elseif typeName="request">
        <#return "Request">
    <#elseif typeName=="blockTemplate">
        <#return "BlockTemplate">
    <#elseif typeName=="submissionResult">
        <#return "SubmissionResult">
    <#elseif typeName=="validateAddressResult">
        <#return "ValidateAddressResult">
    <#elseif typeName=="minerStats">
        <#return "MinerStats">
    <#elseif typeName=="submitBlockParams">
        <#return "SubmitBlockParams">
    <#elseif typeName=="addressParams">
        <#return "AddressParams">
    <#elseif typeName=="ecRecoverParams">
        <#return "EcRecoverParams">
    <#elseif typeName=="voidParams">
        <#return "VoidParams">
    <#elseif typeName=="version_string">
        <#return "VersionType">
    <#elseif typeName=="long">
        <#return "u64">
    <#elseif typeName=="int">
        <#return "u32">
    <#elseif typeName=="bigint">
        <#return "U256">
    <#elseif typeName=="response">
        <#return "Response">
    <#elseif typeName=="any">
        <#return "Object">
    <#elseif typeName=="txLogDetails">
        <#return "TxLogDetails">
    <#elseif typeName=="submitSignatureParams">
        <#return "SubmitSignatureParams">
    <#elseif typeName=="submitSeedParams">
        <#return "SubmitSeedParams">
    <#elseif typeName=="addressParams">
        <#return "AddressParams">
    <#elseif typeName=="validateAddressResults">
        <#return "ValidateAddressResults">
    <#elseif typeName=="blockSpecifier">
        <#return "BlockSpecifier">
    <#else >
        <#return typeName>
    </#if>
</#function>

<#function toRustClassName className>
    <#if className=="InvalidRequest">
        <#return "invalid_request">
    <#elseif className=="ParseError">
        <#return "parse_error">
    <#elseif className=="MethodNotFound">
        <#return "method_not_found">
    <#elseif className=="InvalidParams">
        <#return "invalid_params">
    <#elseif className=="InternalError">
        <#return "internal_error">
    <#elseif className=="UnsupportedUnityFeature">
        <#return "unsupported_unity_feature">
    <#elseif className=="BlockTemplateNotFound">
        <#return "block_template_not_found">
    <#elseif className=="FailedToSealBlock">
        <#return "failed_to_seal_block">
    <#elseif className=="FailedToComputeMetrics">
        <#return "failed_to_compute_metrics">
    <#else>
        <#return "internal_error">
    </#if>
</#function>

<#function paramsExtractorFromName name>
    <#return name>
</#function>

<#function toSnakeCase name>
    <#if name?matches(r"[a-z_]+")>
        <#return name>
    <#else>
        <#assign res_matches = name?matches("([a-z_]+)([A-Z])")>
        <#assign res = "" >
        <#list res_matches as m>
            <#assign res = "${res}${m?groups[1]}_${m?groups[2]?lower_case}" >
        </#list>
        <#assign tail_matches = name?matches(r"[a-z_A-Z]+[A-Z]([a-z_]+)")>
        <#return "${res}${tail_matches?groups[1]}">

    </#if>
</#function>

<#function toOption fieldType fieldRequired>
    <#if fieldRequired =="false" >
    <#return "Option<${toRustType(fieldType)}>">
    <#else>
    <#return toRustType(fieldType)>
    </#if>
</#function>