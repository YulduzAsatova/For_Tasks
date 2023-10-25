package org.example.repository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.example.model.*;
import org.example.utils.DbConnect;

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

public abstract class BaseRepository<ENTITY extends BaseModel<ID>, ID> implements Repository<ENTITY, ID> {

    protected abstract String getFilePath();

    protected abstract String getFileString();

    //protected abstract  String tableName();
    protected abstract String insertDataBase();


    @Override
    public int save(String entityName) {
        List<ENTITY> entities = readFromFile(entityName);
        try  {
            Connection connection= DbConnect.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataBase());

            for (ENTITY entity : entities) {
                System.out.println(entity.getId() + "-" + entity.getName() + "-" + entity.getCountry_id() + "-" +
                        entity.regionId() + "-" + entity.districtId());
                preparedStatement.setInt(1, entity.getId());
                preparedStatement.setString(2, entity.getName());
                //uchtasidan bittasi 3 parameterga olinadi
                if (entity.getCountry_id() != null) {
                    preparedStatement.setInt(3, entity.getCountry_id());
                }
                if (entity.regionId() != null) {
                    preparedStatement.setInt(3, entity.regionId());
                }
                if (entity.districtId() != null) {
                    preparedStatement.setInt(3, entity.districtId());
                }
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

    protected List<ENTITY> readFromFile(String entityName) {
        try {
            String file = new String(Files.readAllBytes(Paths.get(getFilePath())));
            JsonObject jsonObject = JsonParser.parseString(file).getAsJsonObject();
            JsonArray regionsArray = jsonObject.getAsJsonArray(getFileString());
            if (entityName.equals("country")) {
                Type type = new TypeToken<List<Country>>() {
                }.getType();
                List<ENTITY> entities = new Gson().fromJson(regionsArray, type);
                return entities;
            }
            if (entityName.equals("region")) {
                Type type = new TypeToken<List<Regions>>() {
                }.getType();
                List<ENTITY> entities = new Gson().fromJson(regionsArray, type);
                return entities;
            }
            if (entityName.equals("district")) {
                Type type = new TypeToken<List<District>>() {
                }.getType();
                List<ENTITY> entities = new Gson().fromJson(regionsArray, type);
                return entities;
            }
            if (entityName.equals("quarter")) {
                Type type = new TypeToken<List<Quarter>>() {
                }.getType();
                List<ENTITY> entities = new Gson().fromJson(regionsArray, type);
                return entities;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

