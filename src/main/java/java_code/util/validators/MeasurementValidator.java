package java_code.util.validators;

import java_code.dto.MeasurementDTO;
import java_code.models.Measurement;
import java_code.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorsService sensorService;

    @Autowired
    public MeasurementValidator(SensorsService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return MeasurementDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) o;

        if (measurementDTO.getSensor() == null) {
            return;
        }

        if (sensorService.findByName(measurementDTO.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "Нет зарегистрированного сенсора с таким именем!");
    }
}
