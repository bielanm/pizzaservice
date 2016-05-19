package service;


import domain.Pizza;
import java.util.List;

public interface PizzaService {

    Pizza getPizzaByID(Integer id);
    List<Pizza> getPizzas();
}
