package org.aion.rpc.constants;
import java.util.List;
import java.util.NoSuchElementException;

/******************************************************************************
*
* AUTO-GENERATED SOURCE FILE.  DO NOT EDIT MANUALLY -- YOUR CHANGES WILL
* BE WIPED OUT WHEN THIS FILE GETS RE-GENERATED OR UPDATED.
* GENERATED: ${date}
*
*****************************************************************************/
public enum Method{
    <#list methods as method>${method.name?cap_first}("${method.name}", "${method.namespace}")<#if method_has_next>,
    </#if></#list>;
    /**
    * The name used to specify this method within the RPC.
    */
    public final String name;
    /**
    * The namespace to which this method belongs.
    */
    public final String namespace;
    private static final List<Method> values = List.of(Method.values());
    private Method(String name, String namespace){
        this.name=name;
        this.namespace = namespace;
    }

    /**
    * Returns the method constant using the specified method name
    * @param name the method name
    * @throws NoSuchElementException if a constant for the specified name could now be found
    * @return the method constant
    */
    public static Method constantFromName(String name){
        for(Method method: Method.values){
            if(method.name.equals(name)) return method;
        }
        throw new NoSuchElementException();
    }
}