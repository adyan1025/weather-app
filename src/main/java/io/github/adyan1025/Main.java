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
            String feels = tempObj.get("feels_like").toString();

            temp = kelvinToFahrenheit(temp);
            feels = kelvinToFahrenheit(feels);
            city = formatCity(city);
            frame.setTemp(
                    "<html>City: " + city +
                    "<br>Temperature: " + temp +"°"+
                    "<br>Feels Like: " + feels +"°"+
                    "<html/>"
            );
        }
    }

    //Kelvin to Fahrenheit formula and formats city name
    public static String kelvinToFahrenheit(String kelvinString) {
        double kelvin = Double.parseDouble(kelvinString);
        int fahrenheit = (int) ((kelvin - 273.15) * 9/5 + 32);
        return String.valueOf(fahrenheit);
    }
    public static String formatCity(String city) {
        city = removeQuote(city);
        city = capitalizeFirstLetter(city);
        return city;
    }
    public static String removeQuote(String word) {
        if (word == null || word.length() < 2 || word.charAt(0) != '"' || word.charAt(word.length() - 1) != '"') {
            return word;
        }
        return word.substring(1, word.length() - 1);
    }
    public static String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}