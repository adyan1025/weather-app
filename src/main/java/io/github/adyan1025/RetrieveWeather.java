package io.github.adyan1025;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RetrieveWeather {
    JsonObject getJson(String city) {
        try {
            InputStream apiKeyStream = RetrieveWeather.class.getResourceAsStream("/apikey.txt");
            String apiKey = "";
            if (apiKeyStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(apiKeyStream));
                apiKey = br.readLine();
            }
            else {
                System.err.println("File not found!");
            }

            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
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
            return null;
        }
        return null;
    }
}
