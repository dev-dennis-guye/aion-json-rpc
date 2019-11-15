package org.aion.rpc.server;

import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.ResultUnion;

public interface RPC {
    boolean isExecutable(String method);
}
