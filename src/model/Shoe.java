package model;

import java.util.List;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 12:33
 * Project: ShoeStore
 * Package: model
 */
public class Shoe {
    private int id;
    private Price price;
    private Brand brand;
    private Size size;
    private List<ShoeColor> colors;
    private List<ShoeCategory> categories;
    private int stockBalance;


    public Shoe(int id, Price price, Brand brand, Size size,int stockBalance) {
        this.id = id;
        this.price = price;
        this.brand = brand;
        this.size = size;
        this.stockBalance = stockBalance;
    }

    public void addColor(ShoeColor color){
        colors.add(color);
    }

    public void addCategory(ShoeCategory category){
        categories.add(category);
    }

    public void addAmountToStock(int amount){
        stockBalance +=amount;
    }
    public void removeOneFromStock(){
        stockBalance -=1;
    }

}
