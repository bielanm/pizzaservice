package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class Order {

    private final Integer ID;
    private List<Pizza> listPizza;
    private Customer customer;

    private static int number = 0;


    public Order() {
        ID = number;
        number++;
    }


    public double getTotalPrice() {
        double price = 0;
        for(Pizza pizza : listPizza){
            price += pizza.getPrice();
        }
        return price;
    }

    public Order(Customer customer, List<Pizza> listPizza) {
        ID = number;
        this.customer = customer;
        this.listPizza = listPizza;

        number++;
    }

    public int getID() {
        return ID;
    }


    public List<Pizza> getListPizza() {
        return listPizza;
    }

    public void setListPizza(List<Pizza> listPizza) {
        this.listPizza = listPizza;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public String toString() {
        return "domain.Order{" +
                "ID=" + ID +
                ", listPizza=" + listPizza +
                ", customer=" + customer +
                '}' + '\n';
    }
}
