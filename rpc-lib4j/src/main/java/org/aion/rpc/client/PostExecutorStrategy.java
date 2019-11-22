package org.aion.rpc.client;

import java.io.IOException;
import java.net.URL;

@FunctionalInterface
public interface PostExecutorStrategy {

    /**
     * Executes an HTTP post.
     * @param jsonPayload the json rpc request to be made.
     * @return the rpc response
     * @throws IOException
     */
    String post(String jsonPayload) throws IOException;
}
