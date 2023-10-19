package org.example.repository;

import org.example.model.Country;

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
