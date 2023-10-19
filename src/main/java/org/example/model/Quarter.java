package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.District;

@Data
public class Quarter extends BaseModel<Integer>{
    private District districtId;
}
