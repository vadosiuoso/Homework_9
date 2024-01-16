package org.example;

import okhttp3.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws FileIsNotAvailableException {
        String url = String.format("https://http.cat/%s.jpg", code);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.code() == 404){
                throw new FileIsNotAvailableException();
            }
            response.close();
        } catch (IOException ignored) {
        }
        return url;
    }
}
