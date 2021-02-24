package model;

/**
 * Created by Emil Johansson & Christoffer Grännby
 * Date: 2021-02-20
 * Time: 21:20
 * Project: ShoeStore
 * Package: model
 */
public class StockBalance {
    private int id;
    private Shoe shoe;
    private int quantity;

    public StockBalance(int id, Shoe shoe, int quantity) {
        this.id = id;
        this.shoe = shoe;
        this.quantity = quantity;
    }

    public void addAmountToStock(int amount){
        quantity +=amount;
    }

    public void removeOneFromStock(){
        quantity -=1;
    }

    @Override
    public String toString(){
        return shoe + " Antal: " + quantity;
    }
}
