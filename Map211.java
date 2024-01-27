import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.Desktop;
import java.io.File;



public class Map211 {
    
    static String html;
    static String weather;
    static String mapFileName = "myMap.html";
    static ArrayList<String> weatherInfo = new ArrayList<>();

    public Map211(ArrayList<String> weatherInfo, String mapType, int zoom) throws IOException{
        String city = weatherInfo.get(0);
        String weather = city.toUpperCase() + " | weather: " + weatherInfo.get(5) +" | temp: " + weatherInfo.get(1) + " | low: " + weatherInfo.get(2) +" | high: " + weatherInfo.get(3) + " | humidity: " + weatherInfo.get(4) + "%";
        
        //Creates the HTML file
        writeHTML(weather, city, mapType, zoom);

        //This is what creates the html file in the project folder
        String URL = mapFileName;
        File htmlFile = new File(URL);
        Desktop.getDesktop().browse(htmlFile.toURI());
    }

    //Code for the HTML file generation
    public static void writeHTML(String weather, String city, String mapType, int zoom){
        html = "<!DOCTYPE html>"
        + "<html>"
        + "<body>"
        + "<h2>"
        +weather
        + "</h2>"
        + "<iframe"
        + "   width=1200"
        + "   height=900"
        + "   style=border:0"
        + "   loading=lazy"
        + "   allowfullscreen"
        + "   referrerpolicy=\"no-referrer-when-downgrade\""
        + "src=\"https://www.google.com/maps/embed/v1/place?key=AIzaSyA6XB9FGnJHtjvI9GM8mxDxR9FUZeQ7j60&q=" + city + "&zoom=" + zoom + "&maptype=" + mapType + "\""
        + "</iframe>"
        + "</body>"
        + "</html>";

        File f = new File(mapFileName);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(html);
            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}