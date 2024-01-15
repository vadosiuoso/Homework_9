package org.example;

import java.io.*;

public class HttpImageStatusCli {

    public void askStatus() {

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("Enter HTTP status code");
            String input = reader.readLine();
            int code = 0;
            try{
                code = Integer.parseInt(input);
            }catch (NumberFormatException e){
                throw new NumberFormatException("Please enter valid number");
            }
            HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
            downloader.downloadStatusImage(code);
        }catch (IOException ignored){

        }
    }


}
