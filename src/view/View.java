package view;

import controller.Controller;
import model.Order;
import model.Shoe;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
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
    private Controller controller;


    // låta användaren lägga in produkter. ska ej kunna se databasens IDn
    public View() {
        controller = new Controller();
    }

    public void promptUser(){
        int alternative;
        String choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("Välkommen till SkoShoppen");
        login();
        printAllShoes();
        while (true) {
            addToCart();
            System.out.print("Vill du lägga till fler varor? ja/nej: ");
            choice = scan.nextLine().trim();
            if (choice.equalsIgnoreCase("nej") || !choice.equalsIgnoreCase("ja"))
                break;
        }
        printOrder();


    }

    // skriv in användarnamn & lösenord
    public void login(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Användarnamn: ");
            String userName = scanner.nextLine();
            System.out.print("Lösenord: ");
            String password = scanner.nextLine();
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


    // stored procedures, produkten läggs till i beställningen
    public void addToCart(){
        Scanner scanner = new Scanner(System.in);
        List<Shoe> shoes = controller.getAllShoes();
        System.out.println("Välj en produkt du vill lägga till i varukorgen.");
        try {
            int listIndex = scanner.nextInt() - 1;
            if (shoes.size() - 1 < listIndex || 0 > listIndex) {
                System.out.println("Produkten finns inte!");
            } else
                System.out.println(controller.addToCart(shoes.get(listIndex).getId()));
        }catch (InputMismatchException e){
            System.out.println("Fel inmatning!");
        }
    }

    // möjlighet att skriva ut alla produkter som lagts i varukorgen
    public void printOrder(){
        Scanner scanner = new Scanner(System.in);
        Order order = controller.getLastOrder();

        if (order!= null) {
            System.out.println("Tack för ditt köp");
            System.out.print("Vill du se ordern? ja/nej: ");
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("ja"))
                System.out.println(order);
        }else System.out.println("Du har inget i din order!");
    }

    // betyg och kommentarer ska kunna GES på produkter (VG)

    // betyg och kommentarer ska kunna SES på produkter (VG)



    public static void main(String[] args) throws SQLException {
        View view = new View();
        view.promptUser();
    }
}
