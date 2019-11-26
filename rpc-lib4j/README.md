# Aion RPC
`rpc-lib4j` is a java implementation of the the JSON RPC specification. 

## Library

The purpose of this library is to supply both a server and client implementation of the RPC specification.

### Client

The client implementation includes:
* Web3Provider - Is an implementation of Provider based on Apache HTTP Components. This HTTP client 
executes JSON RPC requests and decodes any response.
* RPCClientMethods(GENERATED) - packages and executes executes requests. 
* SimpleIdGenerator - Is an implementation of IDGeneratorStrategy. This class generates the ID for
each JSON RPC request.

### Server

The server package provides: 
* RPCServerMethods - This interface that includes all the JSON RPC methods that need to be implemented. 

### Errors

The server package contains both RPC exceptions and client side exceptions: 
* RPCExceptions - This includes is all errors related to the RPC spec.
* HttpException - This is a client side exception for use with Provider.

### Types

The types package contains all the type definitions used for communicating with an RPC server: 
* RPCTypes - Contains structs and union definitions. 
* RPCTypesConverters - Contains static methods for encoding and decoding RPC messages.

## Templates

To edit this library the templates should be edited and the files should be regenerated. 
The rpc-gen application specifies all the variables that can be used in a template file. 

## Configuration 

The configuration file is used to point to the spec files and define the names of the output java files.


