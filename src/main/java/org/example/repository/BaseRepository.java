package org.example.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.example.model.BaseModel;
import org.example.model.Regions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class  BaseRepository <ENTITY extends BaseModel<ID>,ID> implements Repository<ENTITY,ID> {

    protected abstract String getFilePath();
    protected abstract  String getFileString();
    //protected abstract  String tableName();
    protected abstract  String insertDataBase();

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/lesson";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "postgres";

    @Override
    public int save() {
        List<ENTITY>entities = readFromFile();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataBase());

            for (ENTITY entity : entities) {
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                preparedStatement.addBatch();
            }
            int[] result = preparedStatement.executeBatch();
            System.out.println("Rows affected: " + result.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @Override
    public ENTITY findById(ID id) {
        return null;
    }

    @Override
    public List<ENTITY> getAll() {
        return null;
    }

    @Override
    public ENTITY update(ID id) {
        return null;
    }

    @Override
    public void deleteById(ID id) {

    }
    protected List<ENTITY> readFromFile(){
        try {
            String file = new String(Files.readAllBytes(Paths.get(getFilePath())));
            JsonObject jsonObject = JsonParser.parseString(file).getAsJsonObject();
            JsonArray regionsArray = jsonObject.getAsJsonArray(getFileString());
            Type type = new TypeToken<List<Regions>>() {
            }.getType();
            List<ENTITY> entities = new Gson().fromJson(regionsArray, type);
            return entities;
        }catch(IOException e){
            throw new RuntimeException( e );
        }
    }
}

