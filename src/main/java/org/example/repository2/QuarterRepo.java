package org.example.repository2;


import lombok.SneakyThrows;
import org.example.model.Quarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;


public class QuarterRepo {


    public Quarter getById(int id){
        return
                jdbcTemplate.query("select * from quarter where id="+id, new ResultSetExtractor<Quarter>() {
                    @SneakyThrows
                    @Override
                    public Quarter extractData(ResultSet rs) throws SQLException, DataAccessException {
                        Quarter quarter=new Quarter();
                        if (rs.next()) {
                            quarter.setId(rs.getInt("id"));
                            quarter.setName(rs.getString("name"));
                            quarter.setDistrict(districtRepo.getById(rs.getInt("district_id")).orElseThrow(() -> new ClassNotFoundException()));
                        }
                        return quarter;
                    }
                });
    }

    public void  add(Quarter quarter) {
       int rs= jdbcTemplate.update("insert into quarter(name, district_id) VALUES (?,?)",
                quarter.getName(),quarter.getDistrict_id());
        //System.out.println(rs);
    }

    public Quarter getByName(String name){
        return  jdbcTemplate.query("select * from quarter where name=?", new ResultSetExtractor<Quarter>() {
            @Override
            public Quarter extractData(ResultSet rs) throws SQLException, DataAccessException {
               Quarter quarter=new Quarter();
                if (rs.next()){
                    quarter.setId(rs.getInt("id"));
                    quarter.setName(rs.getString("name"));
                    quarter.setDistrict_id(rs.getInt("district_id"));
                }
                return quarter;
            }
        },name);

    }
}
