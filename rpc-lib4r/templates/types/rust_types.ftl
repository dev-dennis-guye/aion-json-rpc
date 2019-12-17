<#import "../rust_macros.ftl" as macros>
/*******************************************************************************
 * Copyright (c) 2018-2019 Aion foundation.
 *
 *     This file is part of the aion network project.
 *
 *     The aion network project is free software: you can redistribute it
 *     and/or modify it under the terms of the GNU General Public License
 *     as published by the Free Software Foundation, either version 3 of
 *     the License, or any later version.
 *
 *     The aion network project is distributed in the hope that it will
 *     be useful, but WITHOUT ANY WARRANTY; without even the implied
 *     warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *     See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with the aion network project source files.
 *     If not, see <https://www.gnu.org/licenses/>.
 *
 ******************************************************************************/

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/
use super::*;

<#list compositeTypes as composite_type>
<#if composite_type.name=="request" || composite_type.name=="response" || composite_type.name=="error"><#else>
<#if composite_type.comments?has_content>
/**
<#list composite_type.comments as comment>
* ${comment}
</#list>
*/
</#if>
#[derive(Debug,Default,PartialEq,Clone,Hash,Serialize)]
pub struct ${macros.toRustType(composite_type)} {
<#list composite_type.fields as field>
    <#if field.comments?has_content>
    /**
    <#list field.comments as comment>
    * ${comment}
    </#list>
    */
    </#if>
    <#if field.fieldName?matches(r"[a-z_]+")><#if field.fieldName=="type">
    #[serde(rename = "type")]
    </#if><#else>
    #[serde(rename = "${field.fieldName}")]
    </#if>
    <#if field.required=="false">
    #[serde(skip_serializing_if = "Option::is_none")]
    </#if>
    pub <#if field.fieldName == "type">tx_type<#else>${macros.toSnakeCase(field.fieldName)}</#if>: ${macros.toOption(field.type, field.required)},

</#list>
}

</#if>
</#list>

<#list unionTypes as union_type>
<#if union_type.name=="blockSpecifierUnion">
<#if union_type.comments?has_content>
<#list union_type.comments as comment>
/// ${comment}
</#list>
</#if>
#[derive(Debug,PartialEq,Clone,Hash)]
pub enum ${macros.toRustType(union_type)} {
<#list union_type.unionElements as field>
    <#if field.comments?has_content>
    <#list field.comments as comment>
    /// ${comment}
    </#list>
    </#if>
    <#if field.name=="blockEnum">
      <#list enumTypes as enum>
        <#if enum.name==field.name>
          <#list enum.values as value>
    ${value.name?capitalize},
          </#list>
        </#if>
      </#list>
    <#else>
    ${field.name?cap_first}(${macros.toRustType(field.type)}),
    </#if>

</#list>
}

</#if>
</#list>

/// Wrapper structure around vector of bytes.
#[derive(Debug, PartialEq, Eq, Default, Hash, Clone)]
pub struct Bytes(pub Vec<u8>);

impl Bytes {
    /// Simple constructor.
    pub fn new(bytes: Vec<u8>) -> Bytes { Bytes(bytes) }
    /// Convert back to vector
    pub fn into_vec(self) -> Vec<u8> { self.0 }
}

impl From<Vec<u8>> for Bytes {
    fn from(bytes: Vec<u8>) -> Bytes { Bytes(bytes) }
}

impl Into<Vec<u8>> for Bytes {
    fn into(self) -> Vec<u8> { self.0 }
}