package org.example.model;

import lombok.*;

@Data
public abstract  class BaseModel <ID>{
    protected Integer id;
    protected String name;

    public abstract Integer getCountry_id();
    public abstract Integer regionId();
    public abstract Integer districtId();
}
