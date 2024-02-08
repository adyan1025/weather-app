package io.github.adyan1025;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveWeather {
    JsonObject getJson(String city) {
        try {
            APIKey api = new APIKey();
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + api.getAPIKey();
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line = reader.readLine();

            while(line != null) {
                response.append(line);
                line = reader.readLine();
            }
            reader.close();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(String.valueOf(response), JsonObject.class);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                return jsonObject;
            }
            else {
                System.out.println("Error: "+ responseCode);
            }
            connection.disconnect();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
