package APICaller;

import java_code.dto.MeasurementResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.*;
//HERE YOU CAN TEST YOUR API
public class ApiCaller {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String sensorName = "Sensor Test";

        //POST
        String url = "http://localhost:8080/measurement/add";
        for(int i=0;i<500;i++){
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(make1000RequestsMap(sensorName), headers);

            restTemplate.postForObject(url, entity, String.class);
        }

        //GET
        String urlGet = "http://localhost:8080/measurement";
        MeasurementResponse measurementResponse = restTemplate.getForObject(urlGet, MeasurementResponse.class);
        measurementResponse.getList().forEach(x-> System.out.println(x));
    }

    static Map<String, Object> make1000RequestsMap(String sensorName) {
        Random rand = new Random();
        Map<String, Object> map = new HashMap<>();
        map.put("temperature", rand.nextInt(-100, 101));
        map.put("raining", rand.nextBoolean());
        map.put("sensor", Map.of("name", sensorName));
        return  map;
    }
}
