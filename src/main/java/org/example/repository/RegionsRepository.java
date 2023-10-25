package org.example.repository;

import org.example.model.Regions;

import java.util.Optional;

public class RegionsRepository extends BaseRepository<Regions,Integer>{
    private static final String PATH = "regions.json";
    private static final String STRING = "regions";
    private static final String INSERTQUERY = "INSERT INTO region(id, name, country_id) VALUES (?, ?,?)";
    private static RegionsRepository repository;

    private RegionsRepository(){

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


    public static RegionsRepository getInstance(){
        if(repository == null){
            repository = new RegionsRepository();
        }
        return repository;
    }

    @Override
    public Optional<Regions> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
