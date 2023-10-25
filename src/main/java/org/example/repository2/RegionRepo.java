package org.example.repository2;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;



public class RegionRepo {

    private  final  Connection   connection= DbConnect.getConnection();
    private  final Statement    statement=DbConnect.getStatement();

    @Autowired
    private CountryRepo countryRepo;
 @Autowired
 private JdbcTemplate jdbcTemplate;


    public RegionRepo() throws SQLException, ClassNotFoundException {
    }


    @SneakyThrows
    public Optional<Region>   getById(int id){
        ResultSet    rs=statement.executeQuery("select id, name,country_id from region where id="+id+"");
           Region    region=new   Region();
        while (rs.next()){
            region.setId(rs.getInt("id"));
            region.setName(rs.getString("name"));
            Country   country= countryRepo.getById(rs.getInt("country_id"))
                             .orElseThrow(()-> new ClassNotFoundException("Country topilmadi"));
            region.setCountry(country);


        }
        return   Optional.of(region);
    }

    public void  add(Region region) {
        int rs= jdbcTemplate.update("insert into region(name, country_id) VALUES (?,?)",
                region.getName(),region.getCountry_id());
        System.out.println(rs);
    }
    public Region getByName(String name){
        return  jdbcTemplate.query("select * from region where name=?", new ResultSetExtractor<Region>() {
            @Override
            public Region extractData(ResultSet rs) throws SQLException, DataAccessException {
             Region region=new Region();
               if (rs.next()){
                   region.setId(rs.getInt("id"));
                   region.setName(rs.getString("name"));
                   region.setCountry_id(rs.getInt("country_id"));
               }
                return region;
            }
        },name);

    }


}
