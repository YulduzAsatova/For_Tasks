package org.example.repository;

import org.example.model.Quarter;

public class QuarterRepository extends BaseRepository<Quarter,Integer>{
    private static final String PATH = "quarter.json";
    private static final String STRING = "quarter";
    private static final String INSERTQUERY = "INSERT INTO quarter (id,name, district_id) VALUES (?, ?, ?)";
    private static QuarterRepository repository;

    private QuarterRepository(){

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

    public static QuarterRepository getInstance(){
        if(repository == null){
            repository = new QuarterRepository();
        }
        return repository;
    }
}
