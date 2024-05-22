package org.challengeone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String API_KEY = "";
        try {
            File myFile = new File(".APIKEY");
            Scanner myReader = new Scanner(myFile);
            if (myReader.hasNextLine()) {
                API_KEY = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        URI url = URI.create("https://v6.exchangerate-api.com/v6/"+ API_KEY + "/pair/EUR/GBP");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}