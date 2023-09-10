package java_code.controllers;

import jakarta.validation.Valid;
import java_code.dto.SensorDTO;
import java_code.models.Sensor;
import java_code.services.SensorsService;
import java_code.util.ErrorUtil;
import java_code.util.validators.SensorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;
    private final SensorValidator sensorValidator;
    private ErrorUtil errorUtil = new ErrorUtil();

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<SensorDTO> findAll() {
        return sensorsService.findAll().stream().map(x -> sensorsService.parseToSensorDTO(x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SensorDTO findById(@PathVariable("id") int id) {
        Sensor sensor = sensorsService.findById(id);
        return sensorsService.parseToSensorDTO(sensor);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors())
            errorUtil.returnErrorsToClient(bindingResult);

        Sensor sensor = sensorsService.parseToSensor(sensorDTO);
        sensorsService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}