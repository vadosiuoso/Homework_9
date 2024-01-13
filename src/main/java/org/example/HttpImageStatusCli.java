package org.example;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

public class HttpImageStatusCli {

    public void askStatus() {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter HTTP status code");
            String input = reader.readLine();
            if(checkStatus(input)){
                HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
                httpStatusImageDownloader.downloadStatusImage(Integer.parseInt(input));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private boolean checkStatus(String code){
        String url = String.format("https://http.cat/%s.jpg", code);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            if(response.code() == 404){
                System.out.println("There is not image for HTTP status <"+code+">");
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
