package org.example;

import okhttp3.*;

import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class HttpStatusChecker {
    public  String getStatusImage(int code){
        String url = String.format("https://http.cat/%s.jpg", code);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.code() == 404){
                throw new RuntimeException();
            }
            response.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
}
