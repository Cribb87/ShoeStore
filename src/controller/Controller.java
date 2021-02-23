package controller;

import model.Order;
import model.Repository;
import model.Shoe;

import java.util.List;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 14:42
 * Project: ShoeStore
 * Package: controller
 */
public class Controller {
    Repository repository;

    public Controller(){
        repository = new Repository();
    }

    public List<Shoe> getAllShoes(){
        return repository.getAllShoes();
    }

    public Boolean login(String username, String password){
        return repository.checkPassword(username,password);
    }

    public String addToCart(int shoeId){
        return repository.addToCart(shoeId);
    }

    public Order getLastOrder(){
        return repository.getOrder();
    }

    public String addReview(int rateID, int shoeID, String comment){
        return repository.createReview(rateID,shoeID,comment);
    }

    public String getReview(int shoeID){
        return repository.readReviews(shoeID);
    }
}
