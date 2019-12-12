<#import "../rust_macros.ftl" as macros/>
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

build_rpc_trait! {
    pub trait Stratum {
        <#list methods as method>
        <#if method.namespace=="stratum">
        #[rpc(name = "${method.name}")]
        fn ${macros.toSnakeCase(method.name)}(&self${macros.toRustParams(method.param)}) -> <#if method.async=="true">BoxFuture<#else>Result</#if><${macros.toRustType(method.returnType)}>;

        </#if>
        </#list>
    }
}
