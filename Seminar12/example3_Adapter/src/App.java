import interfaces.iMeteoSensor;
import interfaces.iSensorTemperature;
import meteo.AdapterST500;
import meteo.MeteoStore;
import sensors.MS200;
import sensors.ST500Info;

public class App {
    public static void main(String[] args) throws Exception {
      
        
      MeteoStore meteoDB = new MeteoStore();
      iMeteoSensor oldSensor = new MS200(1);
      meteoDB.save(oldSensor);

      iSensorTemperature newSen = new ST500Info();
      AdapterST500 adap = new AdapterST500(newSen);
      meteoDB.save(adap);
    }
}
