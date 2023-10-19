package org.example.service;

import org.example.model.District;
import org.example.repository.DistrictRepository;
import org.example.repository.QuarterRepository;

public class DistrictService {
    private final DistrictRepository repository = DistrictRepository.getInstance();

    public int save(){
        return repository.save();
    }
}
