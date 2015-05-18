/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weather;


/**
 *
 * @author Gautier
 */
public class Weather {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainPanel frame = new MainPanel();
        frame.setVisible(true);
        
        new WeatherThread("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/FR/Paris.json", 
                frame.ImageParis, 
                frame.InfosParis).start();
        new WeatherThread("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/zmw:00000.1.03772.json", 
                frame.ImageLondres, 
                frame.InfosLondres).start();
       new WeatherThread("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/FL/Miami.json", 
                frame.ImageMiami, 
                frame.InfosMiami).start();
        new WeatherThread("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/AU/Sydney.json", 
                frame.ImageSydney, 
                frame.InfosSydney).start();
        new WeatherThread("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/zmw:00000.1.54511.json", 
                frame.ImagePekin, 
                frame.InfosPekin).start();
        new WeatherThread("http://api.wunderground.com/api/5ffe04dce1671bdc/conditions/q/BR/Rio.json", 
                frame.ImageRio, 
                frame.InfosRio).start();
        
        
    }
    
}
