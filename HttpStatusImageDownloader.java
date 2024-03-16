package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class HttpStatusImageDownloader {
    private static final String BASE_URL = "https://http.cat/";

    public void downloadStatusImage(int code) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + code + ".jpg";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            try (InputStream inputStream = response.body().byteStream();
                 OutputStream outputStream = new FileOutputStream(code + ".jpg")) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}

