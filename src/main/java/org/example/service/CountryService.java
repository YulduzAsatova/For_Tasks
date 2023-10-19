package org.example.service;

import org.example.repository.CountryRepository;
import org.example.repository.DistrictRepository;

public class CountryService {
    private final CountryRepository repository = CountryRepository.getInstance();

    public int save(){
        return repository.save();
    }
}
