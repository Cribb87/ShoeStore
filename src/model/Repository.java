package model;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Emil Johansson
 * Date: 2021-02-21
 * Time: 11:20
 * Project: ShoeStore
 * Package: model
 */
public class Repository {
    private Properties properties = new Properties();


    public Repository(){
        try{
            properties.load(new FileInputStream("src/properties/Setting.Properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private Connection addConnection() throws SQLException {
        return  DriverManager.getConnection(properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
    }


}
