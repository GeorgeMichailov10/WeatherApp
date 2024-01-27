import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.*;
import org.json.simple.parser.*;

public class Weather211 {
    



    
    public static ArrayList<String> generate(String cityName){
        try{

            ArrayList<String> measurements = new ArrayList<String>();

            //System.out.println("Checkpoint 1");
            String firstPartURL = "https://api.openweathermap.org/data/2.5/weather?q=";
            String secondPartURL = cityName;
            String thirdPartURL = "&appid=eb5c5e445d514b2d61ccf63b7004b2d4";

            String theURL = firstPartURL + secondPartURL + thirdPartURL;

            URL url = new URL(theURL);

            //Read URL from online
            URLConnection urlConnection = url.openConnection();  //Connects to the URL

            //System.out.println("Checkpoint 2");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //Reads the response
            JSONParser parser = new JSONParser(); //Creates a new parser of the JSON stream
            JSONObject responseJson = (JSONObject)parser.parse(bufferedReader);    //new JSON object from the parse stream

            //System.out.println("Checkpoint 3");

            JSONArray weather = (JSONArray)responseJson.get("weather");   //Create another JSONOArray to reach inside the array wrapper to get some of the stats we need
            //System.out.println("check" + weather.toString());
            JSONObject obj = (JSONObject)weather.get(0); //get the object at index 0 in the JSONArray
            //System.out.println("check" + obj.toString());
            String weatherDescription = (String)obj.get("description"); //Get value associated with description

            //System.out.println("Checkpoint 4");


            JSONObject MostDescriptions = (JSONObject)responseJson.get("main");   //Reach inside wrapper to get some of the stats we need
            //System.out.println("check 4.5" + MostDescriptions.toString());
            Double temperature = (Double)MostDescriptions.get("temp");   //Get value associated with temp (IN TERMS OF KELVIN)\
            Double minimumTemperature = (Double)MostDescriptions.get("temp_min"); //Get value associated with temp_min (IN TERMS OF KELVIN)
            Double maximumTemperature = (Double)MostDescriptions.get("temp_max"); //Get value associated with temp_max (IN TERMS OF KELVIN)
            //System.out.println("check 4.7");
            Long humidity = (Long)MostDescriptions.get("humidity"); //Get value associated with humidity (IS A LONG)
            //System.out.println("check 4.8");

            //System.out.println("Checkpoint 5");


            //Convert the following from Kelvin to Farenheit
            
            temperature = (temperature - 273.15) * 9 / 5 + 32;
            minimumTemperature = (minimumTemperature - 273.15) * 9 / 5 + 32;
            maximumTemperature = (maximumTemperature - 273.15) * 9 / 5 + 32;

            //Add to the ArrayList

            measurements.add(cityName);
            measurements.add(String.format("%.1f", temperature) + "\u00B0");
            measurements.add(String.format("%.1f", minimumTemperature) + "\u00B0");
            measurements.add(String.format("%.1f", maximumTemperature) + "\u00B0");
            measurements.add(String.valueOf(humidity));
            measurements.add(weatherDescription);


            //System.out.println("Checkpoint 6");

            return measurements;
            
        } catch (Exception e) {
            //If there is an exception, return an arraylist of length one
            ArrayList<String> isFalse = new ArrayList<String>();
            isFalse.add("false");
            return isFalse;
        }


    }

}