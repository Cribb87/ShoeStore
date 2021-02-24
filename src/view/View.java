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

    public void promptUser() {
        String choice;
        Scanner scan = new Scanner(System.in);

        System.out.println("Välkommen till SkoShoppen");
        login();
        printAllShoes();
        while (true) {
            addToCart();
            System.out.print("Vill du lägga till fler varor? ja/nej: ");
            System.out.flush();
            choice = scan.nextLine().trim();
            if (choice.equalsIgnoreCase("nej") || !choice.equalsIgnoreCase("ja"))
                break;
        }
        if (printOrder())
            createReviews();
    }

    // skriv in användarnamn & lösenord
    public void login() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Användarnamn: ");
            System.out.flush();
            String userName = scanner.nextLine();
            System.out.print("Lösenord: ");
            System.out.flush();
            String password = scanner.nextLine();
            if (controller.login(userName, password)) {
                System.out.println("Du är nu inloggad");
                break;
            } else System.out.println("Användarnamn och lösenord matchar inte! Försök igen");
        }
    }

    // skriv ut alla produkter i lager och välj produkt
    public void printAllShoes() {
        AtomicInteger counter = new AtomicInteger(1);
        controller.getAllShoes().forEach(e -> System.out.println(counter.getAndIncrement() + ": " + e));
    }

    // stored procedures, produkten läggs till i beställningen
    public void addToCart() {
        Scanner scanner = new Scanner(System.in);
        List<Shoe> shoes = controller.getAllShoes();
        System.out.println("Välj en produkt du vill lägga till i varukorgen.");
        try {
            int listIndex = scanner.nextInt() - 1;
            if (shoes.size() - 1 < listIndex || 0 > listIndex) {
                System.out.println("Produkten finns inte!");
            } else
                System.out.println(controller.addToCart(shoes.get(listIndex).getId()));
        } catch (InputMismatchException e) {
            System.out.println("Fel inmatning!");
        }
    }

    // möjlighet att skriva ut alla produkter som lagts i varukorgen
    public boolean printOrder() {
        Scanner scanner = new Scanner(System.in);
        Order order = controller.getLastOrder();

        if (order != null) {
            System.out.println("Tack för ditt köp");
            System.out.print("Vill du se ordern? ja/nej: ");
            System.out.flush();
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("ja"))
                System.out.println(order);
            return true;
        } else System.out.println("Du har inget i din order!");
        return false;
    }

    // betyg och kommentarer ska kunna GES på produkter (VG)
    public void createReviews() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        int shoe = -1;
        int rate = 0;
        String comment = "";
        Order o = controller.getLastOrder();
        System.out.print("Vill du betygsätta en sko? ja/nej: ");
        System.out.flush();
        choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("ja")) {
            while (!o.getShoes().isEmpty()) {
                while (true) {
                    shoe = pickShoeForReview(o);
                    if (shoe >=0 && shoe <= o.getShoes().size()-1)
                        break;
                }
                while (rate > 4 || rate < 1) {
                    rate = pickRate();
                }

                System.out.print("Kommentar: ");
                System.out.flush();
                comment = scanner.nextLine();

                if (comment == null)
                    comment = "";
                int shoeID = o.getShoes().get(shoe).getShoe().getId();
                System.out.println(controller.addReview(rate, shoeID, comment));


                o.getShoes().remove(shoe);
                if (!o.getShoes().isEmpty()) {
                    System.out.println("Vill du betygsätta fler skor? ja/nej");
                    choice = scanner.nextLine();
                    if (!choice.equalsIgnoreCase("ja"))
                        break;
                }

            }

        }
    }

    public int pickRate() {
        Scanner scanner = new Scanner(System.in);
        int rate = 0;
        System.out.print("Välj ett betyg 1 - 4: ");
        System.out.flush();
        try {
            rate = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Fel inmatning.");
        }
        return rate;
    }

    public int pickShoeForReview(Order order) {
        Scanner scanner = new Scanner(System.in);
        int shoe = -1;
        System.out.println("Välj en sko!");
        order.printForReview();
        try {
            shoe = scanner.nextInt() -1;
        } catch (InputMismatchException e) {
            System.out.println("Fel inmatning.");
        }
        return shoe;
    }

    public static void main(String[] args) throws SQLException {
        View view = new View();
        view.promptUser();
    }
}
