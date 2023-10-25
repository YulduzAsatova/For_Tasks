package org.example.repository2;

import org.example.model.District;
import org.example.utils.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DistrictRepo implements Repository2<District, Integer> {
    private final Connection connection = DbConnect.getConnection();
    private static DistrictRepo districtRepo;

    private DistrictRepo() {
    }


    public int save(District district) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  district(name, region_id) values (?,?);");
            statement.setString(1, district.getName());
            statement.setInt(2, district.getRegion_id());
            int rs = statement.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Optional<District> findById(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select id,name,region_id from district where id=?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            District district = new District();
            if (rs.next()) {
                district.setId(rs.getInt("id"));
                district.setName(rs.getString("name"));
                if (rs.getInt("id") != 0) {
                    district.setRegion_id(rs.getInt("region_id"));
                }
                statement.close();
                return Optional.of(district);
            } else
                return Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<District> getAll() {
        List<District> districts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            rs = statement.executeQuery("select *from district ");
            while (rs.next()) {
                District district = new District();
                district.setId(rs.getInt("id"));
                district.setName(rs.getString("name"));
                district.setRegion_id(rs.getInt("region_id"));
                districts.add(district);
            }
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return districts;
    }

    @Override
    public int update(District district, Integer id) {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("insert into  district(name, region_id) values ('" + district.getName() + "', " + district.getRegion_id() + " ) ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public Optional<District> getByName(String name) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select id,name,region_id from district where name=?;");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            District district = new District();
            if (rs.next()) {
                district.setId(rs.getInt("id"));
                district.setName(rs.getString("name"));
                if (rs.getInt("id") != 0) {
                    district.setRegion_id(rs.getInt("region_id"));
                }
                statement.close();
                return Optional.of(district);
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
            PreparedStatement statement = connection.prepareStatement("delete from district where id=?");
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
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT (name) FROM district Where name =?;");
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

    public static DistrictRepo getInstance() {
        if (districtRepo == null) {
            districtRepo = new DistrictRepo();
        }
        return districtRepo;
    }
}
