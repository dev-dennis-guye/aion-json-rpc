# Aion RPC Generator
The aion RPC generator allows autogeneration of the interface, errors, and data types associated with the Aion JSON-RPC implementation.
The RPC generator depends to on two resources: an xml specification file and associated template files. 

### Templates
The templates are written in freemarker. Every freemarker template receives the current date along with the following which is unique to each template.

#### Errors  
Error templates are supplied with a list of error templates. Each error within the list contains: error_class, code, message, comments, and modifiable. 

#### Types
Type templates are supplied with lists of: 
* primitiveTypes - is a list of maps which contains a name and comment. These elements are found in all other type maps.
* compositeTypes - is a list of maps which contains a list of fields. Each field map contains a fieldName, type, and required.
* constrainedTypes - is a list of maps which contains a regex, min, max, and baseType.
* enumTypes - is a list of maps which contains an internalType, and list of values. Each field is a value which contains a name and value.
* paramTypes - is a list of maps which contains a list of fields. Each field map contains a fieldName, type, index, and required. A field may also contain a defaultValue. But each template needs to ensure that this value is defined before accessing.
* arrayTypes - is a list of maps which contains a nestedType.
* unionTypes - is a list of maps which contains a list of unionElements and nullable. Each unionElment contains a name, type and comment.
* patterns - is a regex.
* decodeError
* encodeError  

#### RPC
RPC templates are supplied with methods, types and comments. The comments object is a list of strings. Methods contain a name, param(type), returnType, comments and errors.

## Limitations
Currently this tooling only supports java. Additionally, the request and result unions in the specification need to be manually edited anc cannot be auto generated. This will need to be changed as this tool moves into production. 

## Future Plans

1. Generate a rust implementation of the RPC 
2. Generate test code
3. Print a spec template using the cli
