package org.example;

import java.io.*;
import java.util.Scanner;

public class HttpImageStatusCli {
    private final BufferedReader reader;
    private final HttpStatusImageDownloader downloader;

    public HttpImageStatusCli(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        downloader = new HttpStatusImageDownloader();
    }
    public void askStatus() {
        System.out.println("Enter HTTP status code");
        while (true){
            int code = input();
            try{
                downloader.downloadStatusImage(code);
                System.out.println("Image for HTTP status <"+code+"> downloaded successfully");
            }catch (FileIsNotAvailableException e){
                System.out.println("There is not image for HTTP status <"+code+">");
                continue;
            }
            break;
        }
    }

    private int input() {
        while (true) {
            try {
                String input;
                try {
                    input = reader.readLine();
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                }
            } catch (IOException e) {
                System.out.println("Unknown error");
            }
        }
    }

    public void close(){
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
