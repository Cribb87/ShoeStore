package model;

/**
 * Created by Emil Johansson & Christoffer Grännby
 * Date: 2021-02-20
 * Time: 14:29
 * Project: ShoeStore
 * Package: model
 */
public class County {
    private int id;
    private String name;

    public County(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
