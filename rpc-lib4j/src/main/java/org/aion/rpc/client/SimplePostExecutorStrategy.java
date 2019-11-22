package org.aion.rpc.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import org.aion.rpc.errors.HttpException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public final class SimplePostExecutorStrategy implements PostExecutorStrategy {

    private final CloseableHttpClient httpclient;
    private final URI uri;

    public SimplePostExecutorStrategy(URI uri) {
        this.uri = uri;
        httpclient = HttpClientBuilder.create()
            .setConnectionManager(new PoolingHttpClientConnectionManager()).build();
    }

    @Override
    public String post(String jsonPayload)
        throws IOException {
        HttpPost post = buildHttpPost(jsonPayload);
        StringBuilder stringBuilder = new StringBuilder();
        try (CloseableHttpResponse response = httpclient.execute(post)) {
            if (response.getStatusLine().getStatusCode() != 200)
                throw new HttpException(
                    response.getStatusLine());// check that the request succeeded
            else {
                InputStream inputStream = response.getEntity().getContent();

                int i;
                while ((i = inputStream.read())
                    != -1) { // read the contents of the response into an array
                    stringBuilder.append((char) i);
                }
            }
        }
        return stringBuilder.toString();
    }

    private HttpPost buildHttpPost(String jsonPayload)
        throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(uri);
        post.setHeader("Accept", ContentType.APPLICATION_JSON.getMimeType());
        post.setHeader("Content-type", ContentType.APPLICATION_JSON.getMimeType());
        post.setEntity(new StringEntity(jsonPayload));
        return post;
    }
}
