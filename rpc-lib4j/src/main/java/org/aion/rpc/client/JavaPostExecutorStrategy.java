package org.aion.rpc.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import org.aion.rpc.errors.HttpException;
import org.apache.http.impl.EnglishReasonPhraseCatalog;

public class JavaPostExecutorStrategy implements PostExecutorStrategy {
    private HttpClient httpClient;
    private final URI jsonRPCServer;

    public JavaPostExecutorStrategy(URI jsonRPCServer){
        this.jsonRPCServer = jsonRPCServer;
        httpClient = HttpClient.newBuilder().build();
    }

    public JavaPostExecutorStrategy(HttpClient httpClient, URI jsonRPCServer) {
        this.httpClient = httpClient;
        this.jsonRPCServer = jsonRPCServer;
    }

    @Override
    public String post(String jsonPayload) throws IOException {
        HttpRequest request= HttpRequest.newBuilder(jsonRPCServer).POST(BodyPublishers.ofString(jsonPayload)).build();
        try {
            HttpResponse response = httpClient.send(request, BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body().toString();
            }else {
                throw new HttpException(EnglishReasonPhraseCatalog.INSTANCE.getReason(response.statusCode(),null), response.statusCode());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "";
        }
    }
}
