package org.challengeone;

import com.google.gson.Gson;
import org.challengeone.models.API_Response;
import org.challengeone.models.CurrencyInfo;

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

        System.out.println(CurrencyInfo.CURRENCY_LIST.toString());
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

        URI url = URI.create("https://v6.exchangerate-api.com/v6/"+ API_KEY + "/latest/USD");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        API_Response apiResponse = new Gson().fromJson(response.body(), API_Response.class);
        System.out.println(apiResponse);
    }
}