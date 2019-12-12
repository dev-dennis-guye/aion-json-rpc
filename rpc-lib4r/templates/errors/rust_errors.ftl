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

pub enum RPCError {
    <#list errors as error>
    <#list error.comments as comment>
    /// ${comment}
    </#list>
    ${error.error_class} = ${error.code},
    </#list>
}

impl RPCError{

    fn message(&self) -> String {
        match self{
            <#list errors as error>
            RPCError::${error.error_class} => "${error.message}".into(),
            </#list>
        }
    }

    pub fn to_error(self,extend_message:Option<String>) -> Error {
        Error {
            message: match extend_message{
                Some(m) => format!("{}: {}",self.message(),m),
                None => self.message()
            },
            code: (self as i64).into(),
            data: None,
        }
    }
}

