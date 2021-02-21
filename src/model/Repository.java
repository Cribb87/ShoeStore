package model;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    private List<ShoeCategory> shoeCategories = new ArrayList<>();
    private List<ShoeColor> shoeColors = new ArrayList<>();

    public static void main(String[] args) {
        Repository r = new Repository();
//        System.out.println(r.checkPassword("Froyo Doe","lösenord"));

    }

    public Repository(){
        try{
            properties.load(new FileInputStream("src/model/properties/Setting.Properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e){
            e.printStackTrace();
        }
        addAllCategories();
        shoeCategories.forEach(System.out::println);
    }

    private Connection addConnection() throws SQLException {
        return  DriverManager.getConnection(properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
    }

    private void addAllCategories(){
        ResultSet rs = null;
        try(Connection connection = addConnection();
            PreparedStatement state1 = connection.prepareStatement("select * from category")) {
            rs = state1.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                shoeCategories.add(new ShoeCategory(id,name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public List<Shoe> getAllShoes(){
        List<Shoe> shoeList = new ArrayList<>();
        ResultSet rs = null;
        try(Connection connection = addConnection();
        PreparedStatement state1 = connection.prepareStatement("select * from shoes")) {
            rs = state1.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoeList;
    }

}
