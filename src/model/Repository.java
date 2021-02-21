package model;

import java.io.FileInputStream;
import java.sql.*;
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

    public static void main(String[] args) {
        Repository r = new Repository();
        System.out.println(r.checkPassword("Froyo Doe","lösenord"));
    }

    public Repository(){
        try{
            properties.load(new FileInputStream("src/model/properties/Setting.Properties"));
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

    public Customer checkPassword(String username, String password){
        ResultSet rs = null;
        try(Connection connection = addConnection();
            PreparedStatement statement = connection.prepareStatement("select * from customer where name = ?;")){
            statement.setString(1,username);
            rs = statement.executeQuery();

            if (rs.next()) {
                String tName = rs.getString("name");
                String tPassword = rs.getString("password");

                if (username.equals(tName) && password.equals(tPassword))
                    return new Customer(rs.getInt("id"));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
