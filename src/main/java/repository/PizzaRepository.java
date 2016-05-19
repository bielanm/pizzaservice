package repository;
import domain.Pizza;

import java.util.List;

public interface PizzaRepository {
    Pizza getPizzaByID(Integer id);
    List<Pizza> getPizzas();
}
