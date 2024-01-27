/*George Michailov
 *CTCID: 202485251
 *Description: This program returns the current weather of a city and an image of the city at the user's choice
 */


import java.util.ArrayList;
import java.util.Scanner;

public class myWeatherApp {
    
    

    public static void main(String[] args){

        //Creat all needed variables        
        String cityName;
        String mapType;  
        int zoomLevel;   
        String weather;
        String temperature;
        String lowTemp;
        String highTemp;
        int humidity;

        
        System.out.println("Welcome to Weather 211 - Spring 2022");
        
        //Get city name
        Scanner console = new Scanner(System.in);
        System.out.println("Input a city name");
        cityName = console.nextLine();
        
        
        //Use method to generate either a String arraylist with all of our needed measurements of length 6 or return an arraylist with only "false"
        ArrayList<String> measurements = Weather211.generate(cityName);

        //If the arraylist has length one, the city name is not valid, so keep iterating until a valid city is given
        while(measurements.size() == 1){
            System.out.println("This is not a valid city. Please enter a valid city.");
            cityName = console.nextLine();
            measurements = Weather211.generate(cityName);
        }

        //Assign corresponding values to the variables
        temperature = measurements.get(1);
        lowTemp = measurements.get(2);
        highTemp = measurements.get(3);
        humidity = Integer.valueOf(measurements.get(4));
        weather = measurements.get(5);


        System.out.println("Select a map type: 1)  roadmap   2)  satellite");
        int entry = console.nextInt();
        console.nextLine();
        if(entry == 1){
            mapType = "roadmap";
        }
        else{
            mapType = "satellite";
        }
        
        System.out.println("Select zoom level of the map: 0 ~ 21     (Default = 14)");
        zoomLevel = console.nextInt();
        console.nextLine();
        
        //IF zoom is an illegal value, default to 14
        if(zoomLevel < 0 || zoomLevel > 21){
            zoomLevel = 14;
        }

        //Generate the HTML file
        try{
            Map211 map = new Map211(measurements, mapType, zoomLevel);
        } catch (Exception e){
            System.out.println("error");
        }

        //Print the measurements to the console
        System.out.println("Current Weather: [" + cityName + "]\n");
        System.out.println(cityName +"\n" + weather + "\ntemp: " + temperature + "\nlow: " + lowTemp + "\nhigh: " + highTemp + "\nHumidity: " + humidity +"%");

        console.close();

    }

}
