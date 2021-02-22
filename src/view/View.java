package view;

import model.Rating;
import model.Repository;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 14:42
 * Project: ShoeStore
 * Package: view
 */
public class View {
    
    Repository repo = new Repository();

    // låta användaren lägga in produkter. ska ej kunna se databasens IDn
    public View() throws SQLException {
        Repository repo = new Repository();
        String choise;
        int alternative;
        Scanner scan = new Scanner(System.in);
        alternative = scan.nextInt();

        System.out.println("Välkommen till SkoShoppen");
        System.out.println("Vill du logga in? \n1. Ja \n2. Nej");
        switch (alternative){
            case 1 -> repo.checkPassword("name","password");
            case 2 -> System.out.println("Du måste logga in för att kunna göra en beställning");
        }

        System.out.println("Välj ett alternativ");
        System.out.println("1. Gör en beställning");
        System.out.println("2. Lägg till en vara i en befintlig beställning");
        System.out.println("3. Kolla betyg på en produkt");
        System.out.println("4. Ge betyg på en produkt");
        System.out.println("5. Kolla vilka produkter som finns i lager");
        System.out.println("6. Kolla vilka produkter som ligger i varukorgen");

        switch (alternative) {
            case 1 -> repo.getOrder();
            case 2 -> repo.addToCart(1, 1); // ändra denna
            case 3 -> Rating.class.toString();
            case 4 -> 
            case 5 -> stockBalance();
            case 6 -> printAllProducts();
        }

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

    // skriv ut alla produkter i lager och välj produkt
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

    // återkoppling om fel uppstod eller om det gått bra att lägga till varan (görs i Repository?)

    // möjlighet att skriva ut alla produkter som lagts i varukorgen
    public void printAllProducts(){
        System.out.println(repo.getOrder().toString());
    }

    // betyg och kommentarer ska kunna GES på produkter (VG)

    // betyg och kommentarer ska kunna SES på produkter (VG)
}
