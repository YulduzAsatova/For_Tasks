package org.example.utils;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
   public static Connection connection = null;
    private static String dbname=null;
    static  {
       try {
           Class.forName("org.postgresql.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       StringBuilder stringBuilder=new StringBuilder();
       stringBuilder.append("jdbc:postgresql://localhost:5432/");
       stringBuilder=StringUtils.hasText(dbname)?stringBuilder.append(dbname) :stringBuilder.append("lesson");

       try {
           connection = DriverManager.getConnection(stringBuilder.toString(), "postgres", "123");
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

    private static  void  setDb(String dbName){
      if (dbName==null){
          dbname="lesson";
      }
     }

    public static Connection getConnection() {
       if (connection == null) {
           try {
               StringBuilder stringBuilder=new StringBuilder();
               stringBuilder.append("jdbc:postgresql://localhost:5432/");
               if (StringUtils.hasText(dbname)) {
                   stringBuilder.append(dbname);
               } else {
                   stringBuilder.append("lesson");
               }
               System.out.println("Connection ulandi");
               connection = DriverManager.getConnection(stringBuilder.toString(), "postgres", "123");
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }

       }
        return connection;
    }


    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        return getConnection().createStatement();
    }

    public static void close() throws SQLException, ClassNotFoundException {
         getConnection().close();

    }

    public static void initTables()  {
        ScriptRunner sr = null;
        try {
            sr = new ScriptRunner(getConnection());
            Reader reader = new BufferedReader(new FileReader("src/main/java/org/example/utils/data.sql "));
            sr.runScript(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
