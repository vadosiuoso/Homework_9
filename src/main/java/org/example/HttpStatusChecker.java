package org.example;

import okhttp3.*;

import java.io.IOException;

public class HttpStatusChecker {
    public String getStatusImage(int code){
        String url = String.format("https://http.cat/%s.jpg", code);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.code() == 404){
                throw new RuntimeException("There is not image for HTTP status <"+code+">");
            }
            response.close();
        } catch (IOException ignored) {
        }
        return url;
    }
}
