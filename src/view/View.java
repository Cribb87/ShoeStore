package view;

import model.Repository;

import java.util.Scanner;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 14:42
 * Project: ShoeStore
 * Package: view
 */
public class View {

    // låta användaren lägga in produkter. ska ej kunna se databasens IDn
    public View(){
        Repository repo = new Repository();
        String choise;
        Scanner scan = new Scanner(System.in);

        System.out.print("What product do you want to buy?");
        choise = scan.nextLine().trim();
    }

    // skriv in användarnan & lösenord

    // skriv ut alla produkter i lager och välj produkt

    // stored procedures, produkten läggs till i beställningen

    // återkoppling om fel uppstod eller om det gått bra att lägga till varan

    // möjlighet att skriva ut alla produkter som lagts i varukorgen

    // betyg och kommentarer ska kunna GES på produkter (VG)

    // betyg och kommentarer ska kunna SES på produkter (VG)
}
