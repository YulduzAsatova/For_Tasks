package org.example.service;

import org.example.repository.QuarterRepository;

public class QuarterService {
    private final QuarterRepository repository = QuarterRepository.getInstance();

    public int save(){
        return repository.save("quarter");
    }
}
