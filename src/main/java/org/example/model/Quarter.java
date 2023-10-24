package org.example.model;

import lombok.Data;

@Data
public class Quarter extends BaseModel<Integer>{
    private Integer district_id;

    @Override
    public Integer getCountry_id() {
        return null;
    }

    @Override
    public Integer regionId() {
        return null;
    }

    @Override
    public Integer districtId() {
        return district_id;
    }
}
