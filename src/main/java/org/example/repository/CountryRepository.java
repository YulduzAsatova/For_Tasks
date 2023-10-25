package org.example.repository;

import org.example.model.Country;

import java.util.Optional;

public class CountryRepository extends BaseRepository<Country,Integer>{
    private static final String PATH = "country.json";
    private static final String STRING = "country";
    private static final String INSERTQUERY = "INSERT INTO country( id, name) VALUES(?, ?)";
    private static CountryRepository repository;
    @Override
    protected String getFilePath() {
        return PATH;
    }

    @Override
    public Country findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public Optional<Country> getByName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    protected String getFileString() {
        return STRING;
    }

    @Override
    protected String insertDataBase() {
        return INSERTQUERY;
    }
    private CountryRepository(){

    }

    public static CountryRepository getInstance(){
        if(repository == null){
            repository = new CountryRepository();
        }
        return repository;
    }
}
