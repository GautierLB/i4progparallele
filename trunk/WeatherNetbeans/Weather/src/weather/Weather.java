/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.* ;



/**
 *
 * @author Gautier
 */
public class Weather {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String uri ="http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/CA/San_Francisco.json";
            URL url = new URL(uri);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/xml");
            InputStream json = connection.getInputStream();
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(json, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
            {
                responseStrBuilder.append(inputStr);
            }
            JSONObject obj = new JSONObject(responseStrBuilder.toString());
            System.out.println(obj.toString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }
    
}
