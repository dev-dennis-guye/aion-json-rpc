package org.aion.rpc.client;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.RPCError;
import org.aion.rpc.types.RPCTypes.ResultUnion;

/**
 *
 */
public interface Provider {

    /**
     * @param request the rpc request to be executed
     * @param resultConverter the converter to be used to extract the result
     * @param <R> the expected response type
     * @throws org.aion.rpc.errors.RPCExceptions.RPCException
     * @return the RPC result
     */
    <R> R execute(Request request, Function<ResultUnion, R> resultConverter);

    /**
     * Allows a client application to asynchronously execute tasks on the result or error of a remote procedure call.
     * The errors are specified by the exceptions found in {@link org.aion.rpc.errors.RPCExceptions}.
     * @param request the rpc request to be executed
     * @param resultConverter the converter to be used to extract the result
     * @param asyncTask the task to be executed on the result or error
     * @param <R> the expected response type
     * @param <O> the output type of the asyncTask
     * @return the result of the async task
     */
    <R, O> CompletableFuture<O> executeAsync(Request request,
        Function<ResultUnion, R> resultConverter,
        BiFunction<R, RPCError, O> asyncTask);
}
