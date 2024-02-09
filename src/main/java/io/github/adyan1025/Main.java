package io.github.adyan1025;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        Frame frame = new Frame();

        while (true) {
            getWeather(frame);
        }

    }

    public static void getWeather(Frame frame) {
        String city;
        while (frame.getCity().isEmpty()) {
            try {
                Thread.sleep(100); // Sleep to avoid busy waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        city = frame.getCity();

        RetrieveWeather weatherObject = new RetrieveWeather();
        JsonObject weather = weatherObject.getJson(city);

        if (weather == null) {
            frame.setTemp("CITY NOT FOUND!");
        }
        else {
            JsonObject tempObj = weather.getAsJsonObject("main");
            String temp = tempObj.get("temp").toString();

            temp = kelvinToFahrenheit(temp);
            //city = removeQuote(city);
            frame.setTemp("The temperature is " + temp);
        }
    }

    //kelvin to fahrenheit formula and removing a quote program
    public static String kelvinToFahrenheit(String kelvinString) {
        double kelvin = Double.parseDouble(kelvinString);
        int fahrenheit = (int) ((kelvin - 273.15) * 9/5 + 32);
        return String.valueOf(fahrenheit);
    }
    public static String removeQuote(String word) {
        if (word == null || word.length() < 2 || word.charAt(0) != '"' || word.charAt(word.length() - 1) != '"') {
            return word;
        }
        return word.substring(1, word.length() - 1);
    }
}