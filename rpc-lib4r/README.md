# Specification of RPC template in Rust

This document is a draft about implementation details for RPC normalizer and auto generator.

As we know, we use `rpc-gen` to read specs, and generate source files based on template files from project directory(rpc-lib4j for Java or rpc-lib4r for Rust).
The templates include **errors**, **rpc interfaces** and **data types**. However, for different languages and rpc frameworks, these definitions have language specific and framework constrained implementations.
I'd rather describe these details in Rust environment to make this generator more robust and easy to use for us.

## 1. RPC Interfaces

We currently define 10 interfaces in `spec/rpc.xml`

A List of auto generated RPC interfaces:

- personal_ecRecover
- getseed
- submitseed
- submitsignature
- ops_getBlockDetails
- getblocktemplate
- submitblock
- validateaddress
- getDifficulty
- getMinerStats

These interfaces in Rust are defined through `build_rpc_trait!()` which is a macro from parity's json rpc core library.

Each of the interface definitions are composed as follows:

```Rust
#[rpc(name = "some_name")]
fn method_name(&self, param1: InputType1, param2: InputType2, ...) -> Result<ReturnType>;
```

So three parts are included:

1. RPC name
2. input params which are already deserialized.
3. Result including `ReturnType` and implicit `Error`.(Result<ReturnType> is a specific type for generic Result<T, Error>)

What we concerned here is just RPC name and the deserialization process if any, since Errors and Param Types will be defined later.

## 2. Data Types

This part describes data types in Rust which are defined in `spec/types.xml` and generated from `rpc-lib4r/templates/types/rust_types.ftl`.

We have defined several types from a view of template, which include 

- `Union Type`
- `Composite Type`
- `List Type`
- `Enum Type`
- `Primitive Types`

and `params type` is just a wrapper of types defined above, `constrained type` is a further restriction of the existing types.

The data type definitions in Rust are as follows:

- `Union Type` => an algebraic form of enum which acts like a tagged union in C.
- `Composite Type` => struct
- `List Type` => vector
- `Enum Type` => enum that wraps one specific type
- `Primitive Types`
    - `any` => to be removed?
    - `bool` => bool
    - `string` => String
    - `long` => i64
    - `int` => i32
    - `byte` => u8
    - `bigint` => BigInt(it is from third party, need to check implementation details)
    - `byte-array` => vector<u8>
    - `address` => Address(alias of H256)
    - `uint16` => u16
    - `uint32` => u32
    - `uint64` => u64
    - `uint128` => U128
    - `uint256` => U256

A template function `toRustType` defined in `templates/rust_macros.ftl` is used to do the conversion.

## 3. RPC Errors

Currently, we've defined 9 kind of errors, listed as follows:

- InvalidRequest
- ParseError
- MethodNotFound
- InvalidParams
- InternalError
- UnsupportedUnityFeature
- BlockTemplateNotFound
- FailedToSealBlock
- FailedToComputeMetrics

I'd rather implement these errors in a functional way(in `templates/errors/rust_errors.ftl`) which will be easier to be mapped in Rust style.

Generated codes are something like:

```Rust
pub fn invalid_request() -> Error {
    Error {
        code: -32600,
        message: "Invalid Request".into(),
        data: None,
    }
}
```