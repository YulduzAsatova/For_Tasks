package org.example.repository2;

import org.example.model.Quarter;

import org.example.utils.DbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class QuarterRepo implements Repository2<Quarter, Integer> {
    private final Connection connection = DbConnect.getConnection();
    private static QuarterRepo quarterRepo;

    private QuarterRepo() {
    }

    @Override
    public int save(Quarter model) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  quarter(name, district_id) values (?,?);");
            statement.setString(1, model.getName());
            statement.setInt(2, model.districtId());
            int rs = statement.executeUpdate();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Optional<Quarter> findById(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from quarter where id=?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            Quarter model = new Quarter();
            if (rs.next()) {
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                if (rs.getInt("id") != 0) {
                    model.setDistrict_id(rs.getInt("district_id"));
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
    public List<Quarter> getAll() {
        List<Quarter> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = null;
            rs = statement.executeQuery("select *from region ");
            while (rs.next()) {
                Quarter model = new Quarter();
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                model.setDistrict_id(rs.getInt("district_id"));
                list.add(model);
            }
            statement.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public int update(Quarter model, Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("insert into  quarter(name, district_id) values (?,?)");
           statement.setString(1, model.getName());
           statement.setInt(2,model.getDistrict_id());
            int rs = statement.executeUpdate();
            return  rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public Optional<Quarter> getByName(String name) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("select * from quarter where name=?;");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            Quarter model = new Quarter();
            if (rs.next()) {
                model.setId(rs.getInt("id"));
                model.setName(rs.getString("name"));
                if (rs.getInt("id") != 0) {
                    model.setDistrict_id(rs.getInt("district_id"));
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
            PreparedStatement statement = connection.prepareStatement("delete from quarter where id=?");
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
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT (name) FROM quarter Where name =?;");
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

    public static QuarterRepo getInstance() {
        if (quarterRepo == null) {
            quarterRepo = new QuarterRepo();
        }
        return quarterRepo;
    }
}
