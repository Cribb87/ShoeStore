package model;

/**
 * Created by Emil Johansson
 * Date: 2021-02-20
 * Time: 12:37
 * Project: ShoeStore
 * Package: model
 */
public class Price {
    private int id;
    private int amount;
    private String currency;

    public Price(int id, int amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return  amount + ": " + currency;
    }
}
