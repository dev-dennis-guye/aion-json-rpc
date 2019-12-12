
/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: 2019-12-11
*
*****************************************************************************/
use super::*;
use std::fmt;
use std::str::FromStr;
use rustc_hex::{ToHex, FromHex};
use serde::ser::{Serialize, Serializer};
use serde::de::{Error, Visitor, Deserialize, Deserializer};

impl Serialize for Bytes {
    fn serialize<S>(&self, serializer: S) -> Result<S::Ok, S::Error>
        where S: Serializer {
        let mut serialized = "0x".to_owned();
        serialized.push_str(self.0.to_hex().as_ref());
        serializer.serialize_str(serialized.as_ref())
    }
}

impl<'a> Deserialize<'a> for Bytes {
    fn deserialize<D>(deserializer: D) -> Result<Bytes, D::Error>
        where D: Deserializer<'a> {
        deserializer.deserialize_any(BytesVisitor)
    }
}

struct BytesVisitor;

impl<'a> Visitor<'a> for BytesVisitor {
    type Value = Bytes;

    fn expecting(&self, formatter: &mut fmt::Formatter) -> fmt::Result {
        write!(formatter, "a 0x-prefixed, hex-encoded vector of bytes")
    }

    fn visit_str<E>(self, value: &str) -> Result<Self::Value, E>
        where E: Error {
        if value.len() >= 2 && &value[0..2] == "0x" && value.len() & 1 == 0 {
            Ok(Bytes::new(FromHex::from_hex(&value[2..]).map_err(|e| {
                Error::custom(format!("Invalid hex: {}", e))
            })?))
        } else if value.len() == 0 {
            Ok(Bytes::new(vec![]))
        } else {
            Err(Error::custom(
                "Invalid bytes format. Expected a 0x-prefixed hex string with even length",
            ))
        }
    }

    fn visit_string<E>(self, value: String) -> Result<Self::Value, E>
        where E: Error {
        self.visit_str(value.as_ref())
    }
}

impl<'a> Deserialize<'a> for BlockSpecifier {
    fn deserialize<D>(deserializer: D) -> Result<BlockSpecifier, D::Error>
        where D: Deserializer<'a> {
        deserializer.deserialize_any(BlockSpecifierVisitor)
    }
}

struct BlockSpecifierVisitor;

impl<'a> Visitor<'a> for BlockSpecifierVisitor {
    type Value = BlockSpecifier;

    fn expecting(&self, formatter: &mut fmt::Formatter) -> fmt::Result {
        write!(
            formatter,
            "a block number or 'latest', 'earliest' or 'pending'"
        )
    }

    fn visit_str<E>(self, value: &str) -> Result<Self::Value, E>
        where E: Error {
        match value {
            "latest" => Ok(BlockSpecifier::Latest),
            // support both "0x<hex>" and "<decimal>" format
            _ if value.starts_with("0x") && value.len()==66 => {
                H256::from_str(&value[2..])
                    .map(BlockSpecifier::Hash)
                    .map_err(|e| Error::custom(format!("Invalid block specifier: {}", e)))
            }
            _ if value.starts_with("0x") && value.len()<=18 => {
                u64::from_str_radix(&value[2..], 16)
                    .map(BlockSpecifier::BlockNumber)
                    .map_err(|e| Error::custom(format!("Invalid block specifier: {}", e)))
            }
            _ if value.starts_with("0x") => {
                Err(Error::custom(format!("Invalid BlockSpecifier format. Expected a 0x-prefixed hex string with limited length or a decimal string")))
            }
            _ => {
                u64::from_str_radix(&value, 10)
                    .map(BlockSpecifier::BlockNumber)
                    .map_err(|e| Error::custom(format!("Invalid block specifier: {}", e)))
            }
        }
    }

    fn visit_string<E>(self, value: String) -> Result<Self::Value, E>
        where E: Error {
        self.visit_str(value.as_ref())
    }

    fn visit_u64<E>(self, v: u64) -> Result<Self::Value, E>
        where E: Error {
        // support decimal format.
        Ok(BlockSpecifier::BlockNumber(v))
    }
}