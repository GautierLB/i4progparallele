/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weather;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            JsonFactory jsonF = new JsonFactory();
            JsonParser jp = jsonF.createParser(new URL("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/CA/San_Francisco.json")); 
            
        } catch (IOException ex) {
            Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
