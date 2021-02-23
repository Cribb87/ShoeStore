package model;

import java.util.Date;

/**
 * Created by Emil Johansson
 * Date: 2021-02-23
 * Time: 19:47
 * Project: ShoeStore
 * Package: model
 */
public class OutOfBalance {
    private int id;
    private Shoe shoe;
    private Date date;

    public OutOfBalance(int id, Shoe shoe, Date date) {
        this.id = id;
        this.shoe = shoe;
        this.date = date;
    }
}
