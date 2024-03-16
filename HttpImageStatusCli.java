package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HttpImageStatusCli {
    private HttpStatusChecker statusChecker = new HttpStatusChecker();
    private HttpStatusImageDownloader imageDownloader = new HttpStatusImageDownloader();

    public void askStatus() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter HTTP status code:");
        try {
            String input = reader.readLine();
            try {
                int code = Integer.parseInt(input);
                try {
                    String imageUrl = statusChecker.getStatusImage(code);
                    System.out.println("Image URL: " + imageUrl);
                    imageDownloader.downloadStatusImage(code);
                    System.out.println("Image downloaded successfully.");
                } catch (IOException e) {
                    System.out.println("There is no image for HTTP status " + code);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        } catch (IOException e) {
            System.out.println("Error reading input.");
        }
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}
