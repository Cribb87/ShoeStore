package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        return "OrderID: " + id +
                "\nDatum: " + orderDate +
                "\nSkor: " + shoes;
    }
}
