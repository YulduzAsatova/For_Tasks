package org.example.model;

import lombok.*;

@Data
public class District extends BaseModel<Integer>{
    private Integer region_id;


    @Override
    public Integer getCountry_id() {
        return null;
    }

    @Override
    public Integer regionId() {
        return region_id;
    }

    @Override
    public Integer districtId() {
        return null;
    }
}
