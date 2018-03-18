package misc;

import entity.City;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LocalTimeParser {

    public LocalTimeParser(){}

    public String getLocalTime(City city){
        String localTime = null;

        String latitude = city.getLatitude();
        String longitude = city.getLongitude();

        try {
            URL url = new URL("http://new.earthtools.org/timezone/" + latitude + "/" + longitude);

            URLConnection connection = url.openConnection();
            connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line, saveLine = null;

            while ((line = bufferedReader.readLine()) != null){
                if(line.contains("<localtime>")){
                    saveLine = line;
                    break;
                }
            }
            bufferedReader.close();

            localTime = saveLine.substring(saveLine.indexOf('>') + 1);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return city.getName() + ": " + localTime  + "<br>";
    }
}
