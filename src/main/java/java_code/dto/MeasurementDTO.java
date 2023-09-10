package java_code.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public class MeasurementDTO {

    @NotNull
    @Range(min = -100, max = 100, message = "Temperature should be in range from -100 to 100")
    private Double temperature;

    @NotNull
    private Boolean raining;

    //название перемнной имеет значение, если бы тут было sensorDTO, то нихуя бы не заработало, так как в Json-объекте мы отправляем поле
    //с именем Sensor
    @NotNull
    private SensorDTO sensor;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MeasurementDTO{" +
                "temperature=" + temperature +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
