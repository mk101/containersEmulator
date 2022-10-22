package com.example.emulator.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.http.HttpClient;

public class PostSendService implements SendService {

    @Override
    public void send(String message) {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("http://127.0.0.1:81/");

            StringEntity entity = new StringEntity(message);
            request.setEntity(entity);

            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");

            CloseableHttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.err.println(EntityUtils.toString(response.getEntity()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
