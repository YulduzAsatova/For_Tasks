package org.example.model;

import lombok.*;

@Data
public class Country extends BaseModel<Integer>{


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
        return null;
    }
}
