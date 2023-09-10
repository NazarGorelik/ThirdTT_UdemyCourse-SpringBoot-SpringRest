package java_code.dto;

import java.util.List;

public class MeasurementResponse {

    private List<MeasurementDTO> list;

    public MeasurementResponse() {
    }

    public MeasurementResponse(List<MeasurementDTO> list) {
        this.list = list;
    }

    public List<MeasurementDTO> getList() {
        return list;
    }

    public void setList(List<MeasurementDTO> list) {
        this.list = list;
    }
}
