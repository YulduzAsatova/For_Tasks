package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Regions extends BaseModel<Integer>{
    private Integer country_id;

    @Override
    public Integer regionId() {
        return null;
    }

    @Override
    public Integer districtId() {
        return null;
    }
}
