package org.aion.rpc.client;
@FunctionalInterface
public interface IDGeneratorStrategy {
    /**
     * Generates a unique ID for use with a json rpc request.
     * @return ID for use with this request
     */
    int generateID();
}
