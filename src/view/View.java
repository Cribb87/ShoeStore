package view;

import controller.Controller;
import model.Shoe;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 14:42
 * Project: ShoeStore
 * Package: view
 */
public class View {
    public static void main(String[] args) throws SQLException {
        new View();
    }
    
    Controller controller;
    Scanner scan;

    // låta användaren lägga in produkter. ska ej kunna se databasens IDn
    public View() throws SQLException {
        controller = new Controller();
        scan = new Scanner(System.in);


//        String choise;
        int alternative;
//
        System.out.println("Välkommen till SkoShoppen");
//        login();
        System.out.println("Välj en produkt du vill lägga till i varukorgen.");
        printAllShoes();
        while (true) {
            alternative = scan.nextInt();
            if(addToCart(alternative))
                break;
        }


//
//
//        System.out.println("Välj ett alternativ");
//        System.out.println("1. Gör en beställning");
//        System.out.println("2. Lägg till en vara i en befintlig beställning");
//        System.out.println("3. Kolla betyg på en produkt");
//        System.out.println("4. Ge betyg på en produkt");
//        System.out.println("5. Kolla vilka produkter som finns i lager");
//        System.out.println("6. Kolla vilka produkter som ligger i varukorgen");
//        alternative = scan.nextInt();
//
//        switch (alternative) {
//            case 1 -> repo.getOrder();
//            case 2 -> repo.addToCart( 1); // ändra denna
//            case 3 -> Rating.class.toString();
//            case 4 ->
//            case 5 -> stockBalance();
//            case 6 -> printAllProducts();
//        }

      /*  while(true) {
            System.out.print("Vilken sko vill du lägga till? (Skriv Q för att avsluta)");
            choise = scan.nextLine().trim();
            if (choise.equalsIgnoreCase("Q")){
                System.exit(0);
            }
                System.out.println(repo.getAllShoes());
        }*/
    }

    // skriv in användarnamn & lösenord
    private void login(){
        while (true) {
            System.out.print("Användarnamn: ");
            String userName = scan.nextLine();
            System.out.print("Lösenord: ");
            String password = scan.nextLine();
            if (controller.login(userName, password)) {
                System.out.println("Du är nu inloggad");
                break;
            } else System.out.println("Användarnamn och lösenord matchar inte! Försök igen");
        }
    }

    // skriv ut alla produkter i lager och välj produkt
    public void printAllShoes(){
        AtomicInteger counter = new AtomicInteger(1);
        controller.getAllShoes().forEach(e -> System.out.println(counter.getAndIncrement() +": "+ e));
    }


    public int stockBalance() throws SQLException {
        Properties properties = new Properties();
        try(Connection connection = DriverManager.getConnection(properties.getProperty("connectionString"),
                properties.getProperty("name"),
                properties.getProperty("password"));
            PreparedStatement statement = connection.prepareStatement("select * from stockbalance" +
                    "join shoe on shoe.id = stockbalance.shoeID");
            ResultSet rs = statement.executeQuery()){
                while(rs.next()){
                    int quantity = rs.getInt(stockBalance());
                }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return stockBalance();
    }

    // stored procedures, produkten läggs till i beställningen
    public Boolean addToCart(int shoe){
        int listIndex = shoe - 1;
        List<Shoe> shoes = controller.getAllShoes();
        if (shoes.size()-1 < listIndex) {
            System.out.println("Produkten finns inte\nFörsök igen.");
            return false;
        }
        System.out.println(controller.addToCart(shoes.get(listIndex).getId()));
        return true;
    }

    // återkoppling om fel uppstod eller om det gått bra att lägga till varan (görs i Repository?)

    // möjlighet att skriva ut alla produkter som lagts i varukorgen
    public void printAllProducts(){
        //System.out.println(repo.getOrder().toString());
    }

    // betyg och kommentarer ska kunna GES på produkter (VG)

    // betyg och kommentarer ska kunna SES på produkter (VG)
}
