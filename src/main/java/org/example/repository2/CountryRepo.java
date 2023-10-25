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


public class CountryRepo {
    private final Connection connection = DbConnect.getConnection();
    private static CountryRepo countryRepo;

    private CountryRepo() {
    }

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

    public void update(Country country) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("update country set name = ? where id=?;");
        statement.setString(1,country.getName());
        statement.setInt(2,country.getId());
        int rs = statement.executeUpdate();
        statement.close();
    }

    public boolean existByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT (name) FROM country Where name =?;");
            statement.setString(1,name);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            boolean recordFound = resultSet.next();
            return recordFound;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Optional<Country> getById(int id) {
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

    public void add(Country country) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("insert into country(name) values( '" + country.getName() + "' ) ");
        statement.close();
    }

    public Country getByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from country where name= '" + name + "' ");
        Country country = new Country();
        while (rs.next()) {
            country.setId(rs.getInt("id"));
            country.setName(rs.getString("name"));
        }
        statement.close();
        return country;
    }

    public static CountryRepo getInstance() {
        if (countryRepo == null) {
            countryRepo = new CountryRepo();
        }
        return countryRepo;
    }
}
