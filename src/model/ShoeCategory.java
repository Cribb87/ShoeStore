package model;

/**
 * Created by Emil Johansson & Christoffer Grännby
 * Date: 2021-02-20
 * Time: 13:51
 * Project: ShoeStore
 * Package: model
 */
public class ShoeCategory {
    private int id;
    private String name;

    public ShoeCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString(){
        return name;
    }
}
