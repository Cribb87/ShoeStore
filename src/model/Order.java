package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
        AtomicInteger amount = new AtomicInteger();
        String currency = ": " + shoes.get(0).getShoe().getPrice().getCurrency();
        shoes.forEach(s -> amount.addAndGet(s.getQuantity() * s.getShoe().getPrice().getAmount()));

        return "OrderID: " + id +
                "\nDatum: " + orderDate +
                "\nSkor: " + shoe +
                "\nSumma: " + amount + currency;
    }
}
