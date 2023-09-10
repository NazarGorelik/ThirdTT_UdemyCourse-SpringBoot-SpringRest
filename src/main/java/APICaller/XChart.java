package APICaller;

import java_code.dto.MeasurementResponse;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//HERE YOU CAN DRAW TEMPERATURE-CHART

public class XChart {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurement";
        MeasurementResponse measurementResponse = restTemplate.getForObject(url, MeasurementResponse.class);
        List<Double> temperatureList = measurementResponse.getList().stream().map(x->x.getTemperature()).collect(Collectors.toList());

        double[] xData = IntStream.range(0, temperatureList.size()).asDoubleStream().toArray();
        double[] yData = temperatureList.stream().mapToDouble(x -> x).toArray();

        XYChart chart = QuickChart.getChart("Temperatures", "X", "Y", "temperature",
                xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
