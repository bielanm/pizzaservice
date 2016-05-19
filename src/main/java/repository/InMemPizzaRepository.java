package repository;


import domain.*;
import java.util.ArrayList;
import java.util.List;

public class InMemPizzaRepository implements PizzaRepository {
    private List<Pizza> pizzas = new ArrayList<Pizza>();

    @Override
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Pizza getPizzaByID(Integer id) {
        for(Pizza pizza: pizzas){
            if(pizza.getId().equals(id)){
                return pizza;
            }
        }
        return null;
    }
}
