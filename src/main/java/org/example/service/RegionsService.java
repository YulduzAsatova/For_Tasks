package org.example.service;


import org.example.repository.RegionsRepository;

public class RegionsService {
    private final RegionsRepository repository = RegionsRepository.getInstance();
    public int save(){
        return repository.save();
    }
}

