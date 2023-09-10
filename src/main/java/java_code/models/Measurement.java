package java_code.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Range(min = -100, max = 100, message = "Temperature should be in range from -100 to 100")
    @Column(name = "temperature")
    private Double temperature;

    @NotNull
    @Column(name = "raining")
    private Boolean raining;

    @Column(name = "measured_at")
    private LocalDateTime measured_at;

    @NotNull
    @ManyToOne
    @JoinColumn(name="sensor_name", referencedColumnName = "name")
    private Sensor sensor;


    public Measurement() {
    }

    public Measurement(double temperature, boolean raining) {
        this.temperature = temperature;
        this.raining = raining;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDateTime getMeasured_at() {
        return measured_at;
    }

    public void setMeasured_at(LocalDateTime measured_at) {
        this.measured_at = measured_at;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
