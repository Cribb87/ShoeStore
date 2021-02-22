package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 14:18
 * Project: ShoeStore
 * Package: model
 */
public class Order {
    private int id;
    private Customer customer;
    private Date orderDate;
    private List<ShoeGroup> shoes = new ArrayList<>();

    public Order(int id, Customer customer, Date orderDate) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public void addShoeGroup(ShoeGroup shoeGroup){
        shoes.add(shoeGroup);
    }

    @Override
    public String toString() {
        String shoe = shoes.stream().map(String::valueOf).collect(Collectors.joining("\n"));
        int amount =0;
        String currency = ": " + shoes.get(0).getShoe().getPrice().getCurrency();
        for (ShoeGroup s: shoes){
            amount += s.getQuantity() * s.getShoe().getPrice().getAmount();
        }

        return "OrderID: " + id +
                "\nDatum: " + orderDate +
                "\nSkor: " + shoe +
                "\nSumma: " + amount + currency;
    }
}
