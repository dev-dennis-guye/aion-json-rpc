package org.aion.rpc.client;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.aion.rpc.errors.RPCExceptions;
import org.aion.rpc.errors.RPCExceptions.RPCException;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.Response;
import org.aion.rpc.types.RPCTypes.RpcError;
import org.aion.rpc.types.RPCTypes.VersionType;
import org.aion.rpc.types.RPCTypesConverter.RequestConverter;
import org.aion.rpc.types.RPCTypesConverter.ResponseConverter;

/**
 * This class allows the users implement the post method that should be used by passing an
 * implementation of {@link PostExecutorStrategy}.
 */
public final class ConfigurableProvider implements Provider {

    private final PostExecutorStrategy postExecutorStrategy;
    private final ExecutorService executorService;

    /**
     * @param postExecutorStrategy the http client implementation that should be used with this
     *                             object
     * @param executorService      the executor service to be used with async requests
     */
    @SuppressWarnings("WeakerAccess")
    public ConfigurableProvider(PostExecutorStrategy postExecutorStrategy,
        ExecutorService executorService) {
        this.postExecutorStrategy = postExecutorStrategy;
        this.executorService = executorService;
    }

    /**
     * Creates a ConfigurableProvider using an instance of {@link Executors#newCachedThreadPool()}
     * as the default thread pool.
     *
     * @param postExecutorStrategy the http client implementation that should be used with this
     *                             object
     */
    public ConfigurableProvider(PostExecutorStrategy postExecutorStrategy) {
        this(postExecutorStrategy, Executors.newCachedThreadPool());
        configurePool();
    }

    private void configurePool() {
        if (executorService instanceof ThreadPoolExecutor) {
            ((ThreadPoolExecutor) executorService)
                .setMaximumPoolSize(Runtime.getRuntime().availableProcessors() * 2);
            ((ThreadPoolExecutor) executorService).setCorePoolSize(2);
        }
    }

    @Override
    public <R> R execute(final Request request, final Function<Object, R> resultConverter) {
        Response response;
        try {
            response = doExecution(request);
        } catch (Exception e) {
            throw new RuntimeException(e);//TODO replace this with a client specific exception
        }
        if (response.error == null) {
            return resultConverter.apply(response.result);
        } else {
            RPCException exception = RPCExceptions.fromCodeAndMessage(response.error.code,
                response.error.message);// retrieve the contextual information
            if (exception.getError().code.compareTo(response.error.code)
                == 0) { // check if the exception contained any contextual information
                throw exception;
            } else {
                throw RPCExceptions
                    .fromCode(response.error.code);// otherwise extract the real exception
            }
        }
    }

    @Override
    public <R, O> CompletableFuture<O> executeAsync(final Request request,
        final Function<Object, R> resultConverter, final BiFunction<R, RpcError, O> asyncTask) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                final Response response = doExecution(request);
                return asyncTask
                    .apply(resultConverter.apply(response.result), response.error);
            } catch (RPCException e) {
                return asyncTask.apply(null,e.getError());
            } catch (Exception e) { // catch runtime exceptions and io exceptions here
                return asyncTask.apply( null, new RpcError(0,
                    "Client error(" + e.getClass().getSimpleName() + "): " + e.getMessage()));
            }
        }, executorService);
    }


    private Response doExecution(final Request request) throws IOException {
        return ResponseConverter.decode(
            postExecutorStrategy.post(RequestConverter.encodeStr(request)));
    }
}
