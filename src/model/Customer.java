package model;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 14:23
 * Project: ShoeStore
 * Package: model
 */
public class Customer {
    private int id;
    private String name;
    private String password;

    public Customer(int id) {
        this.id = id;
    }

    public Customer(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }


}
