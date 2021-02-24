package model;

/**
 * Created by Emil Johansson & Christoffer Grännby
 * Date: 2021-02-20
 * Time: 14:32
 * Project: ShoeStore
 * Package: model
 */
public class Reviews {
    private int id;
    private String comment;
    private Shoe shoe;
    private Rating rating;

    public Reviews(int id, String comment, Shoe shoe, Rating rating) {
        this.id = id;
        this.comment = comment;
        this.shoe = shoe;
        this.rating = rating;
    }
}
