package org.aion.rpc.client;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.RPCError;
import org.aion.rpc.types.RPCTypes.ResultUnion;

public interface Provider {

    <R> R execute(Request request, Function<ResultUnion, R> resultConverter);

    <R, O> CompletableFuture<O> executeAsync(Request request,
        Function<ResultUnion, R> resultConverter,
        BiFunction<R, RPCError, O> asyncTask);
}
