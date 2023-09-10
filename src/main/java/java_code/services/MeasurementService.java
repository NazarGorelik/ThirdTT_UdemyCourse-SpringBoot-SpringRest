package java_code.services;

import java_code.dto.MeasurementDTO;
import java_code.models.Measurement;
import java_code.models.Sensor;
import java_code.repositories.MeasurementRepository;
import java_code.repositories.SensorRepository;
import java_code.util.exceptions.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement){
        measurement.setMeasured_at(LocalDateTime.now());
        Optional<Sensor> sensorInDB = sensorRepository.findByName(measurement.getSensor().getName());
        if(sensorInDB.isPresent()){
            measurement.setSensor(sensorInDB.get());
        }else{
            throw new SensorNotFoundException();
        }
        measurementRepository.save(measurement);
    }

    public Measurement parseToMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    public MeasurementDTO parseToMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
