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
        if (printOrder())
            createReviews();
    }

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

    public void printAllShoes() {
        AtomicInteger counter = new AtomicInteger(1);
        controller.getAllShoes().forEach(e -> System.out.println(counter.getAndIncrement() + ": " + e));
    }

    public void chooseAlternative() {
        Scanner scanner = new Scanner(System.in);
        List<Shoe> shoes = controller.getAllShoes();
        int product = 0;
        int review = 0;
            while(true) {
                System.out.print("Välj en sko: ");
                System.out.flush();
                product = scanInt()-1;
                if (shoes.size() - 1 < product || 0 > product) {
                    System.out.println("Produkten finns inte!");
                }
                else
                    break;
            }
            while (true) {
                System.out.println("1. Se recensioner \n2. Lägg till sko");
                review = scanInt();
                if (review == 1 || review == 2)
                    break;
            }
                if (review == 1) {
                    System.out.println(controller.getReview(shoes.get(product).getId()));
                    System.out.print("Vill du lägga till denna sko i varukorgen? ja/nej: "); System.out.flush();
                    String addShoe = scanner.nextLine().trim();
                    if (addShoe.equalsIgnoreCase("ja")) {
                        System.out.println(controller.addToCart(shoes.get(product).getId()));
                    }
                    else {
                        System.out.println("Skon ej tillagd");
                    }
                } else {
                    System.out.println(controller.addToCart(shoes.get(product).getId()));
                }
    }

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
                    o.printForReview();
                    System.out.print("Välj en sko: ");
                    System.out.flush();
                    shoe = scanInt()-1;
                    if (shoe >=0 && shoe <= o.getShoes().size()-1)
                        break;
                }
                while (rate > 4 || rate < 1) {
                    System.out.print("Välj ett betyg 1 - 4: ");
                    System.out.flush();
                    rate = scanInt();
                }

                System.out.print("Kommentar: ");
                System.out.flush();
                comment = scanner.nextLine();

                if (comment == null)
                    comment = "";
                int shoeID = o.getShoes().get(shoe).getShoe().getId();
                System.out.println(controller.addReview(rate, shoeID, comment));

                rate = 0;
                o.getShoes().remove(shoe);
                if (!o.getShoes().isEmpty()) {
                    System.out.print("Vill du betygsätta fler skor? ja/nej: ");
                    System.out.flush();
                    choice = scanner.nextLine();
                    if (!choice.equalsIgnoreCase("ja"))
                        break;
                }
            }
        }
    }

    public int scanInt() {
        Scanner scanner = new Scanner(System.in);
        int inter = -1;
        try {
            inter = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Fel inmatning.");
        }
        return inter;
    }

    public static void main(String[] args) {
        View view = new View();
        view.promptUser();
    }
}
