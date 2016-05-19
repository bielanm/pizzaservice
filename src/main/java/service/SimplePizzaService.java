package service;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PizzaRepository;

import java.util.List;

@Service("pizzaService")
public class SimplePizzaService implements PizzaService{

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> getPizzas() {
        return pizzaRepository.getPizzas();
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        Pizza pizza = null;
        try {
            pizza = pizzaRepository.getPizzaByID(id);
        } catch (RuntimeException e){
            return null;
        }

        return pizza;
    }
}
