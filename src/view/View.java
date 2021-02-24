package view;

import controller.Controller;
import model.*;

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
        String choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("Välkommen till SkoShoppen");
        login();
        printAllShoes();
        while (true) {
            chooseAlternative();
            System.out.print("Vill du lägga till fler varor? ja/nej: "); System.out.flush();
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
            System.out.print("Användarnamn: "); System.out.flush();
            String userName = scanner.nextLine();
            System.out.print("Lösenord: ");System.out.flush();
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

    public void chooseAlternative() {
        Scanner scanner = new Scanner(System.in);
        List<Shoe> shoes = controller.getAllShoes();
        int product = 0;
        try {
            product = scanner.nextInt();
            if (shoes.size() - 1 < product || 0 > product) {
                System.out.println("Produkten finns inte!");
            } else
                System.out.println("1. Se reviews \n2. Lägg till sko");
       // } catch (InputMismatchException e) {

                int review = scanner.nextInt();
                if (review == 1) {
                    System.out.println(controller.getReview(product));
                    System.out.println("Vill du lägga till denna sko i varukorgen? \n1. Ja \n2. Nej");
                    if (review == 1)
                        review = 2;
                       // System.out.println(controller.addToCart(shoes.get(product).getId()));
                    else {
                        System.out.println("Skon ej tillagd");
                        chooseAlternative();
                    }
                    // stored procedures, produkten läggs till i beställningen
                } else if (review == 2) {
                    System.out.println(controller.addToCart(shoes.get(product).getId()));
                } else
                    System.out.println("Du måste ange siffran 1 eller 2");
            } catch (InputMismatchException e1) {
                System.out.println("Fel inmatning");
            }
        /*} catch (Exception e) {
            System.out.println("Fel inmatning!");
        }*/
    }

    // möjlighet att skriva ut alla produkter som lagts i varukorgen
    public void printOrder(){
        Scanner scanner = new Scanner(System.in);
        Order order = controller.getLastOrder();

        if (order!= null) {
            System.out.println("Tack för ditt köp");
            System.out.print("Vill du se ordern? ja/nej: ");System.out.flush();
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("ja"))
                System.out.println(order);
        }else System.out.println("Du har inget i din order!");
    }

    // betyg och kommentarer ska kunna GES på produkter (VG)
   /* public void addReview(){
        Scanner scanner = new Scanner(System.in);
        int rateID = scanner.nextInt();
        int shoeID = scanner.nextInt();
        String comment = scanner.nextLine();
        controller.addReview(rateID,shoeID,comment);
        try {

        }
    }*/

    // betyg och kommentarer ska kunna SES på produkter (VG)
    public void readReview (int shoeID){

        System.out.println(controller.getReview(shoeID));

        }


    public static void main(String[] args) throws SQLException {
        View view = new View();
        view.promptUser();
    }
}
