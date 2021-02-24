package model;

/**
 * Created by Emil Johansson & Christoffer Grännby
 * Date: 2021-02-20
 * Time: 21:29
 * Project: ShoeStore
 * Package: model
 */
public class ShoeGroup {
    private int id;
    private Shoe shoe;
    private int quantity;

    public ShoeGroup(int id, Shoe shoe, int quantity) {
        this.id = id;
        this.shoe = shoe;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  shoe + " Antal: " + quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public String getShoeString() {
        return String.valueOf(shoe);
    }
}
