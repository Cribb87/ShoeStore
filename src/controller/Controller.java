package controller;

import model.Customer;
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
    Repository rep;

    public Controller(){
        rep = new Repository();
    }

    public List<Shoe> getAllShoes(){
        return rep.getAllShoes();
    }

    public Boolean loggin(String username, String password){
        return rep.checkPassword(username,password);
    }

}
