package org.example.repository2;

import org.example.model.Regions;
import org.example.utils.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RegionRepo implements Repository2<Regions, Integer> {
    private final Connection connection = DbConnect.getConnection();
    private static RegionRepo regionRepo;

    private RegionRepo() {
    }


    public int save(Regions region) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  region(name, country_id) values (?,?);");
            statement.setString(1, region.getName());
            statement.setInt(2, region.getCountry_id());
            int rs = statement.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Optional<Regions> findById(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select id,name,country_id from region where id=?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Regions region = new Regions();
            if (rs.next()) {
                region.setId(rs.getInt("id"));
                region.setName(rs.getString("name"));
                if (rs.getInt("id") != 0) {
                    region.setCountry_id(rs.getInt("country_id"));
                }
                statement.close();
                return Optional.of(region);
            } else
                return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Regions> getAll() {
        List<Regions> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            rs = statement.executeQuery("select *from region ");
            while (rs.next()) {
                Regions region = new Regions();
                region.setId(rs.getInt("id"));
                region.setName(rs.getString("name"));
                region.setCountry_id(rs.getInt("country_id"));
                list.add(region);
            }
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Regions region, Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  district(name, region_id) values (?,?)");
            int rs = statement.executeUpdate();
            return  rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Regions> getByName(String name) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from region where name=?;");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            Regions model = new Regions();
            if (rs.next()) {
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                if (rs.getInt("id") != 0) {
                    model.setCountry_id(rs.getInt("country_id"));
                }
                statement.close();
                return Optional.of(model);
            } else
                return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int deleteById(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from region where id=?");
            statement.setInt(1, id);
            int rs = statement.executeUpdate();
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean existsByName(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT (name) FROM region Where name =?;");
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

    public static RegionRepo getInstance() {
        if (regionRepo == null) {
            regionRepo = new RegionRepo();
        }
        return regionRepo;
    }
}
