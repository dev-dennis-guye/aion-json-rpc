package org.aion.rpc.client;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;
import java.util.function.Function;
import org.aion.rpc.errors.HttpException;
import org.aion.rpc.errors.RPCExceptions;
import org.aion.rpc.errors.RPCExceptions.InvalidRequestRPCException;
import org.aion.rpc.errors.RPCExceptions.RPCException;
import org.aion.rpc.types.RPCTypes.Request;
import org.aion.rpc.types.RPCTypes.Response;
import org.aion.rpc.types.RPCTypes.RpcError;
import org.aion.rpc.types.RPCTypesConverter.RequestConverter;
import org.aion.rpc.types.RPCTypesConverter.ResponseConverter;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a sample implementation of {@link Provider}. The underlying HTTP library is
 * {@link CloseableHttpClient} which has not been benchmarked to determine performance.
 */
public final class Web3Provider implements Provider {

    private static final Web3Provider WEB_3_PROVIDER = new Web3Provider();
    private Logger logger = LoggerFactory.getLogger(Web3Provider.class);
    private final ExecutorService threadPool;
    private CloseableHttpClient httpclient;
    private final AtomicReference<String> provider;

    private Web3Provider() {
        threadPool = Executors.newCachedThreadPool();// executor for async tasks
        httpclient = HttpClientBuilder.create()
            .setConnectionManager(new PoolingHttpClientConnectionManager()).build();
        // http client to hand requests
        provider = new AtomicReference<>("http://localhost:8545");// the address of the web3 server
    }

    /**
     * @return a singleton instance of Web3Provider
     */
    public static Web3Provider getInstance() {
        return WEB_3_PROVIDER;
    }

    /**
     * Sets the http server for use with the class. Access to the underlying variable is thread safe.
     * However this should only be set by one thread for predicable behaviour.
     * @param provider an http uri.
     */
    public void setProvider(String provider) {
        this.provider.set(provider);
    }

    /**
     *
     * @param logger an instance of {@link org.slf4j.Logger}
     */
    public void setLogger(Logger logger){
        this.logger=logger;
        info("Logging is enabled");
    }

    /**
     * Executes a request asynchronously and returns the future result.
     * @param request the request to be executed
     * @param resultConverter the decoder to be used on the result
     * @param asyncTask the task to be executed upon completion of the request
     * @param <R> the result of the rpc request
     * @param <O> the output of the function
     * @return the future result of the call
     */
    @Override
    public <R, O> CompletableFuture<O> executeAsync(Request request,
        Function<Object, R> resultConverter,
        BiFunction<R, RpcError, O> asyncTask) {
        Objects.requireNonNull(request);
        return CompletableFuture
            .supplyAsync(() -> packageRequest(request, asyncTask, resultConverter), threadPool);
    }

    private void debug(String message, Object... objects){
        if(logger != null && logger.isDebugEnabled()) {
            logger.debug(message, objects);
        }
    }

    private void info(String message, Object... objects){
        if (logger !=null && logger.isInfoEnabled()){
            logger.info(message,objects);
        }
    }

    private void trace(String message, Object... objects){
        if (logger !=null && logger.isTraceEnabled()){
            logger.trace(message, objects);
        }
    }

    private void warn(String message, Object... objects){
        if (logger !=null && logger.isWarnEnabled()){
            logger.warn(message,objects);
        }
    }

    /**
     *
     * @param request the rpc request to be executed
     * @param resultConverter the decoder to be used on the result
     * @param <R> the result of the rpc request
     * @return the result of the request
     * @throws org.aion.rpc.errors.RPCExceptions.RPCException if the rpc request was not successfully executed
     * @throws HttpException if the response was not 200
     */
    @SuppressWarnings("ConstantConditions")
    public <R> R execute(Request request, Function<Object, R> resultConverter) {
        Response response;
        try {
            String jsonPayload = RequestConverter.encodeStr(request);
            debug("Executing request: {}", jsonPayload);

            HttpPost post = buildHttpPost(provider.get(), jsonPayload);
            try (CloseableHttpResponse httpResponse = httpclient.execute(post)) {// execute the http request
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    debug("Request Succeeded!");
                    HttpEntity responseEntity = httpResponse.getEntity();
                    InputStream inputStream = responseEntity.getContent();
                    int i;
                    StringBuilder responseString = new StringBuilder();
                    while ((i = inputStream.read()) != -1) { // read the contents of the response into an array
                        responseString.append((char) i);
                    }
                    if (responseString.length() == 0) {
                        return null;    // if the response is empty then the request was a notify
                                        // so return null.
                    } else {
                        trace(responseString.toString());

                        response = ResponseConverter.decode(responseString.toString());
                        // else create response
                        if (response.error != null) {
                            throw RPCExceptions.fromCode(response.error.code);
                        } else {
                            return resultConverter.apply(response.result);
                        }
                    }
                } else {
                    warn("Http request failed with: {}",
                        httpResponse.getStatusLine().getStatusCode());
                    throw new HttpException(httpResponse.getStatusLine());
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            warn("Failed with exception ", e);
            throw InvalidRequestRPCException.INSTANCE;
        }
    }

    /**
     * Builds the HTTP post to be executed
     * @param provider the web3 socket address
     * @param jsonPayload the request to be executed
     * @return an http post that can be executed by an Http client
     * @throws UnsupportedEncodingException if the json payload is not encoded correctly
     */
    private HttpPost buildHttpPost(String provider, String jsonPayload)
        throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(provider);
        post.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
        post.setHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType());
        post.setEntity(new StringEntity(jsonPayload));
        return post;
    }

    /*
    performs the http request and executes the handler
    the http request is done in the same way as above
    except any exceptions are not thrown but passed on the
    async task
     */
    private <R, O> O packageRequest(Request request, BiFunction<R, RpcError, O> asyncTask,
        Function<Object, R> resultConverter) {
        RpcError error = null;
        Response response;
        try {
            String jsonPayload = RequestConverter.encodeStr(request);
            trace("Executing request: {}", jsonPayload);
            HttpPost post = buildHttpPost(provider.get(), jsonPayload);
            try (CloseableHttpResponse httpResponse = httpclient.execute(post)) {
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    HttpEntity responseEntity = httpResponse.getEntity();
                    InputStream inputStream = responseEntity.getContent();
                    int i;
                    StringBuilder responseString = new StringBuilder();
                    while ((i = inputStream.read()) != -1) {
                        responseString.append((char) i);
                    }
                    debug("Received response: {}", responseString);
                    if (responseString.length() == 0) {
                        return asyncTask.apply(null, null);
                    } else {
                        response = ResponseConverter.decode(responseString.toString());
                        //noinspection ConstantConditions
                        return asyncTask
                            .apply(resultConverter.apply(response.result), response.error);
                    }
                }
            }
        } catch (RPCException e){
            error = e.getError();
        } catch (Exception e) {
            error = InvalidRequestRPCException.INSTANCE.getError();
        }
        return asyncTask.apply(null, error);
    }
}
