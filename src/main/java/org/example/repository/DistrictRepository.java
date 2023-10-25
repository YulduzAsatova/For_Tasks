package org.example.repository;

import org.example.model.District;

import java.util.Optional;

public class DistrictRepository extends BaseRepository<District,Integer>{
    private static final String PATH = "district.json";
    private static final String STRING = "district";
    private static final String INSERTQUERY = "INSERT INTO district (id,  name,region_id) VALUES (?, ?, ?)";
    private static DistrictRepository repository;

    private DistrictRepository(){

    }

    @Override
    protected String getFilePath() {
        return PATH;
    }

    @Override
    protected String getFileString() {
        return STRING;
    }

    @Override
    protected String insertDataBase() {
        return INSERTQUERY;
    }


    public static DistrictRepository getInstance(){
        if(repository == null){
            repository = new DistrictRepository();
        }
        return repository;
    }

    @Override
    public Optional<District> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
