package model;

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
    private List<Shoe> shoes;

    public Order(int id, Customer customer, Date orderDate) {
        this.id = id;
        this.customer = customer;
        this.orderDate = orderDate;
    }

    public void addShoe(Shoe shoe){
        shoes.add(shoe);
    }
}
