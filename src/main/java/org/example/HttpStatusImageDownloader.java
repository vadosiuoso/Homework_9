package org.example;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class HttpStatusImageDownloader {

    public void downloadStatusImage(int code)  throws FileIsNotAvailableException{
        HttpStatusChecker httpStatusChecker = new HttpStatusChecker();
        String url = httpStatusChecker.getStatusImage(code);
        String fileDirectory = getDirectory(url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        try(Response response = call.execute()) {
            assert response.body() != null;
            try(InputStream in = response.body().byteStream();
                FileOutputStream fileOutputStream = new FileOutputStream(fileDirectory)){
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1){
                        fileOutputStream.write(buffer, 0,bytesRead);
                    }
                }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private String getDirectory(String url)  {
        String currentDir = System.getProperty("user.dir");
        String fileName;
        try {
            fileName = Paths.get(new URL(url).getPath()).getFileName().toString();
            return currentDir + "/" + fileName;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
