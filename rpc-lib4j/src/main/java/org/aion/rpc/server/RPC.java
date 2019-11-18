package org.aion.rpc.server;

import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.ResultUnion;

public interface RPC {

    /**
     *
     * @param method the RPC method to be executed
     * @return a boolean indicating whether this method can be executed by this RPC implementation.
     */
    boolean isExecutable(String method);
}
