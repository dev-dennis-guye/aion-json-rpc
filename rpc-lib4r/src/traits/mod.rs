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

mod stratum;
mod ops;
mod personal;

use super::types::*;
use super::errors::*;
use jsonrpc_core::{Result,BoxFuture};
use aion_types::{H256,H512,U256};
pub use self::stratum::Stratum;
pub use self::ops::Ops;
pub use self::personal::Personal;