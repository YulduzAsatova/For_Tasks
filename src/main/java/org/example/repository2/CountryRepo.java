package org.example.repository2;


import lombok.SneakyThrows;
import org.example.model.Country;
import org.example.repository.CountryRepository;
import org.example.utils.DbConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CountryRepo implements Repository2<Country, Integer> {
    private final Connection connection = DbConnect.getConnection();
    private static CountryRepo countryRepo;

    private CountryRepo() {
    }

    @Override
    public int save(Country country) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into country( name) VALUES (?)");
            statement.setString(1, country.getName());
            int rs = statement.executeUpdate();
            statement.close();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Country> findById(Integer id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select *from country where  id=" + id + "");
            Country country = new Country();
            while (rs.next()) {
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
            }
            statement.close();
            return Optional.of(country);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Country> getAll() {
        List<Country> countries = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            rs = statement.executeQuery("select *from country ");
            while (rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
                countries.add(country);
            }
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countries;
    }

    @Override
    public int update(Country country, Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update country set name = ? where id=?;");
            statement.setString(1, country.getName());
            statement.setInt(2, id);
            int rs = statement.executeUpdate();
            statement.close();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean existsByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT (name) FROM country Where name =?;");
            statement.setString(1, name);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            boolean recordFound = resultSet.next();
            return recordFound;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<Country> getByName(String name) {
       PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from country where name= ?;");
            ResultSet rs = statement.executeQuery();
            Country country = new Country();
            while (rs.next()) {
                country.setId(rs.getInt("id"));
                country.setName(rs.getString("name"));
            }
            statement.close();
            return Optional.of(country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public int deleteById(Integer integer) {
        return 0;
    }

    public static CountryRepo getInstance() {
        if (countryRepo == null) {
            countryRepo = new CountryRepo();
        }
        return countryRepo;
    }
}
