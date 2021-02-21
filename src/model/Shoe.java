package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<ShoeColor> colors = new ArrayList<>();
    private List<ShoeCategory> categories = new ArrayList<>();


    public Shoe(int id, Price price, Brand brand, Size size) {
        this.id = id;
        this.price = price;
        this.brand = brand;
        this.size = size;

    }

    public int getId() {
        return id;
    }

    public void addColor(ShoeColor color){
        colors.add(color);
    }

    public void addCategory(ShoeCategory category){
        categories.add(category);
    }


    @Override
    public String toString() {
        String category = categories.stream().map(String::valueOf).collect(Collectors.joining("/"));
        String color = colors.stream().map(String::valueOf).collect(Collectors.joining("/"));
        return brand + " Kategori: " + category
                +" Storlek: " + size +  " Färg: " + color
                + " Pris: " + price  ;
    }
}
