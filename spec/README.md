# The RPC Specification
The rpc specification is made up of three files: 
1. errors.xml - contains the error definitions, codes, and messages
2. rpc.xml - contains the signature of rpc methods and any associated errors 
3. types.xml - contains the definition of regex for each primitive data type and type definitions.

## Typing
There are 7 data types that can be defined: 
#### Primitive data types
These data types are the basic data types used by the rpc and the blockchain. As such, these will need to be manually defined in any implementation of the spec. 
```xml
<type-primitive typeName="address"/> <!--All types have an associated name-->
<!--Use camelcase when naming primitives.-->
```
#### Constrained data types 
These data types are primitives that have been constrained by a min and max size and a regex. Eg. an int_hex_string is defined by the regex `^0x[a-fA-F0-9]+$`. Its size is limited to 4 bytes and since a hex string encodes one byte in two characters an int hex string will have a max length of 8 characters. All hex strings encoded must start with 0x so this gives a maximum length of 10. 
```xml
<type-constrained baseType="int" min="3" max="10" regex="^0x[0-9a-fA-F]+$" typeName="intHexString"/>
<!--Use camelcase when naming constrained types.-->
```
#### Enum data types
These are constant values used by the RPC. Eg. The json rpc version can only be "2.0".
```xml
<type-enum typeName="versionType" internalType = "string"><!--internalType specifies the data type of each enum value-->
    <value name="Version2" var="2.0"/><!--Each value specifies the name of the constant and its value(var)-->
</type-enum>
<!--Use camelcase when naming enum types.-->
```
#### Array data types
These are arrays which contained a nested value. 
```xml
<type-list typeName="request_array" nestedType="request"/> <!--The nested Type is the type which this array can contain-->
<!--Array types use the type definition of <base type name>_array-->
```
#### Composite data types
These are data structures group related data together. Each field is associated with a name, type, and a required attribute.
```xml
<type-composite typeName="response">
    <comment>This is the standard response body for a JSON RPC Request</comment>
    <field fieldName="id" required="false" type="int"/> <!--Each field must define a name, type and whether any representation in the RPC requires the field is populated-->
    <field fieldName="result" required="false" type="resultUnion"/>
    <field fieldName="error" required="false" type="error"/>
    <field fieldName="jsonrpc" required="true" type="versionType"/>
</type-composite>
<!--Use camelcase when naming composite types.-->
```
#### Param data types
These are similar to composite data types but they are used to package the parameters of a method. Each field is similar to those in a composite data type but with an additional index field because params can be encoded as either an array or a struct. 
```xml
<!--The index attributes specifies the order of the parameters.
A defaultValue attribute can optionally be added if a field is not required-->        
<type-params-wrapper typeName="submitSignatureParams">
    <field fieldName="signature" index="0" required="true" type="byte64String"/>
    <field fieldName="sealHash" index="1" required="true" type="byte32String"/>
</type-params-wrapper>
<!--Use camelcase when naming param types and append params or param as a suffix.-->
```
#### Union data types
These are used to define any value that could be defined using different types.
```xml
<type-union typeName="blockSpecifierUnion" nullable="true"><!--Nullable indicates that this union can equal null-->
    <comment>Specifies a block</comment>
    <union-element type="byte-array" name="hash"/><!--each union element has a type and an associated name-->
    <union-element type="long" name="blockNumber"/>
    <union-element type="blockEnum" name="blockEnum"/>
</type-union>
<!--Use camelcase when naming union types and append union as a suffix.-->
```

## Methods
Method declarations are used to generate the interfaces which need to be implemented. They contain three required components and two optional.
```xml
<method name="submitsignature" param="submitSignatureParams" returnType="bool"><!--The param and returnTypes must be defined within the types.xml file and the name must be unique to this method.-->
    <errors> <!--Optional These are the errors that can be thrown by this method. This contextual information can be used to generate test cases-->
        <error value="UnsupportedUnityFeature"/>
        <error value="BlockTemplateNotFound"/>
    </errors>
    <comment>Allows a POS validator to submit their signature to seal a block.</comment><!--Optional-->
</method>
```
This declaration will give us a method signature `bool submitsignature(submitSignatureParams) throws UnsupportedUnityFeature, BlockTemplateNotFound` which can then be interpreted by the template files.

## Errors
The JSON-RPC implementation is able to return errors if the call fails.
```xml
<!--Error class is type of error that will be generated by this definition-->
<!--Code is required by the JSON-RPC spec.-->
<!--Message is contextual based on this error-->
<!--Modifiable indicates whether information can be appended to error message or whether the error must be treated as a constant.--> 
<error error_class="UnsupportedUnityFeature" code="-32001" message="Unity fork is not enabled" modifiable="false">
    <comment>Occurs if a unity specific service is requested</comment>
</error>
```
