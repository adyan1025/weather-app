package io.github.adyan1025;

import com.google.gson.JsonObject;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the city: ");
        String city = scanner.nextLine();

        retrieveWeather weatherObject = new retrieveWeather();
        JsonObject weather = weatherObject.getJson(city);
        if (weather == null) {
            System.out.println("ERROR!");
            return;
        }
        JsonObject tempObj = weather.getAsJsonObject("main");

        String temp = tempObj.get("temp").toString();
        temp = kelvinToFahrenheit(temp);
        city = removeQuote(city);
        System.out.println("The temperature at "+city+" is "+temp+" degrees!");

        System.out.println("Try again? (1 for yes 0 for no)");
        int repeat = scanner.nextInt();
        if (repeat == 1) {
            main(args);
        }
        else {
            return;
        }
    }

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