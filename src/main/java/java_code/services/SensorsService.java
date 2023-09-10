package java_code.services;

import java_code.dto.SensorDTO;
import java_code.models.Sensor;
import java_code.repositories.SensorRepository;
import java_code.util.exceptions.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorRepository sensorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsService(SensorRepository sensorRepository, ModelMapper modelMapper) {
        this.sensorRepository = sensorRepository;
        this.modelMapper = modelMapper;
    }

    public List<Sensor> findAll(){
        return sensorRepository.findAll();
    }

    public Sensor findById(int id){
        Optional<Sensor> sensorOptional = sensorRepository.findById(id);
        return sensorOptional.orElseThrow(()->new SensorNotFoundException());
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Sensor parseToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    public SensorDTO parseToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }
}
