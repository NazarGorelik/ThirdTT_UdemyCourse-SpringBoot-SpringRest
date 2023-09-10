package java_code.controllers;

import jakarta.validation.Valid;
import java_code.dto.MeasurementDTO;
import java_code.dto.MeasurementResponse;
import java_code.models.Measurement;
import java_code.services.MeasurementService;
import java_code.util.ErrorUtil;
import java_code.util.validators.MeasurementValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private ErrorUtil errorUtil = new ErrorUtil();

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping()
    public MeasurementResponse findAll() {
        return new MeasurementResponse(measurementService.findAll().stream().map(x -> measurementService.parseToMeasurementDTO(x))
                .collect(Collectors.toList()));
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return (int) measurementService.findAll().stream().map(x -> x.getRaining() == true).count();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);

        if (bindingResult.hasErrors())
            errorUtil.returnErrorsToClient(bindingResult);

        Measurement measurement = measurementService.parseToMeasurement(measurementDTO);
        measurementService.save(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
