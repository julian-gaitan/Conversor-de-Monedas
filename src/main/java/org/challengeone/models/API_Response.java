package org.challengeone.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public record API_Response(
        String result,
        @SerializedName("error-type") String errorType,
        String base_code,
        String target_code,
        Double conversion_rate,
        Double conversion_result) {

    private static final String API_KEY = initAPI_KEY();

    private static String initAPI_KEY() {
        String apiKey = "";
        try {
            File myFile = new File(".APIKEY");
            Scanner myReader = new Scanner(myFile);
            if (myReader.hasNextLine()) {
                apiKey = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return apiKey;
    }

    public static API_Response doConversion(CurrencyInfo base, CurrencyInfo target, double amount) {

        URI url = URI.create("https://v6.exchangerate-api.com/v6/"+ API_KEY + "/" + base.code() + "/" + target.code() + "/" + amount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(url)
                                         .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), API_Response.class);
        } catch (IOException | InterruptedException e) {
            return new API_Response("error", e.getMessage(), "", "", 0.0, 0.0);
        }
    }
}
