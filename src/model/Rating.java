package model;

/**
 * Created by Emil Johansson & Christoffer Grännby
 * Date: 2021-02-20
 * Time: 14:31
 * Project: ShoeStore
 * Package: model
 */
public class Rating {
    private int id;
    private String rate;

    public Rating(int id, String rate) {
        this.id = id;
        this.rate = rate;
    }

    @Override
    public String toString(){
        return rate;
    }
}
