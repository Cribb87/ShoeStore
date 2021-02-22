package model;

import javax.sound.midi.ShortMessage;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Emil Johansson
 * Date: 2021-02-21
 * Time: 13:53
 * Project: ShoeStore
 * Package: model
 */
public class Repository {
        private Properties properties = new Properties();
        private int orderID = 6;




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


           System.out.println(getOrder());

        }

        private Connection addConnection() throws SQLException {
            return  DriverManager.getConnection(properties.getProperty("connectionString"),
                    properties.getProperty("name"),
                    properties.getProperty("password"));
        }

        private void addCategoryToShoes(List<Shoe> shoes){
            try (Connection connection = addConnection();
            PreparedStatement statement = connection.prepareStatement("select * from categoryGroup " +
                    "join category on category.id = categoryGroup.categoryid");
                 ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    int shoeId = rs.getInt("shoeid");
                    for (Shoe s : shoes){
                        if (s.getId() == shoeId)
                            s.addCategory(new ShoeCategory(rs.getInt("categorygroup.CategoryID"),rs.getString("category.name")));
                    }
                }


            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        private void addColorToShoes(List<Shoe> shoes){
            try (Connection connection = addConnection();
                 PreparedStatement statement = connection.prepareStatement("select * from colorGroup " +
                         "join color on color.id = colorGroup.colorid");
                 ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    int shoeId = rs.getInt("shoeid");
                    for (Shoe s : shoes){
                        if (s.getId() == shoeId)
                            s.addColor(new ShoeColor(rs.getInt("colorGroup.colorid"),rs.getString("color.shade")));
                    }
                }


            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        public List<Shoe> getAllShoes(){
            List<Shoe> shoes = new ArrayList<>();
            try(Connection connection = addConnection();
                PreparedStatement statement = connection.prepareStatement("select * from shoe " +
                        "join size on size.id = shoe.SizeID " +
                        "join brand on brand.id = shoe.BrandID " +
                        "join price on price.id = shoe.PriceID");
                ResultSet rs = statement.executeQuery()){
                while (rs.next()){
                    int id = rs.getInt("ID");
                    Price price = new Price(rs.getInt("shoe.priceId"),rs.getInt("price.amount"),rs.getString("price.currency"));
                    Brand brand = new Brand(rs.getInt("shoe.brandId"),rs.getString("brand.name"));
                    Size size = new Size(rs.getInt("shoe.sizeId"),rs.getInt("size.number"));
                    shoes.add(new Shoe(id,price,brand,size));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            addCategoryToShoes(shoes);
            addColorToShoes(shoes);
            return shoes;
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
                    else if (tName.isEmpty() || tPassword.isEmpty()){
                        System.out.println("Du måste ange användarnamn och lösenord");
                    }
                }


            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
    }

        public String addToCart(int customerID,int shoeID){
            try (Connection connection = addConnection();
            CallableStatement statement = connection.prepareCall("call addToCart(?,?,?)")){
                statement.setInt(1,customerID);
                statement.setInt(2,orderID);
                statement.setInt(3,shoeID);
                statement.registerOutParameter(2,Types.INTEGER);
                statement.execute();

                if (orderID == 0)
                    orderID = statement.getInt(2);
                return "Skon har lagts till i din order";

            }catch (SQLException e){
                e.printStackTrace();
            }
            return "Något blev fel";
        }

        private Shoe getShoe(List<Shoe> shoes, int shoeId){
            for (Shoe s : shoes){
                if (s.getId() == shoeId)
                    return s;
            }
            return null;
        }

        public Order getOrder(){
            Order order = null;
            List<Shoe> shoes = getAllShoes();
            try(Connection connection = addConnection();
            PreparedStatement statement = connection.prepareStatement("select * from shoegroup " +
                    "join orders on orders.id = shoegroup.orderID where shoegroup.orderid = ?")) {
                statement.setInt(1,orderID);
                ResultSet rs = statement.executeQuery();
                while(rs.next()){
                    if (order == null)
                        order = new Order(orderID, new Customer(rs.getInt("orders.customerID")), rs.getDate("orders.orderdate"));
                    order.addShoeGroup(new ShoeGroup(rs.getInt("shoegroup.id"),getShoe(shoes,rs.getInt("shoegroup.shoeid")),rs.getInt("shoegroup.Quantity")));
                }


            } catch (SQLException e){
                e.printStackTrace();
            }
            return order;
        }
}
