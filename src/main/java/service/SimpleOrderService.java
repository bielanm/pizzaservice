package service;

import domain.*;
import infrastructure.*;
import repository.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("orderService")
public class  SimpleOrderService implements OrderService/*, ApplicationContextAware */{
    private PizzaRepository pizzaRepository;
    private OrderRepository orderRepository;

    public SimpleOrderService() {
    }

    public void setPizzaRepository(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public SimpleOrderService(PizzaRepository pizzaRepository, OrderRepository orderRepository) {
        this.pizzaRepository = pizzaRepository;
        this.orderRepository = orderRepository;
    }

    @Benchmark (active = true)
    public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
        List<Pizza> pizzas = pizzasListfromArrayOfIds(pizzasID);
        Order newOrder = getNewOrder();
//      Order newOrder = new Order(customer, pizzas);
        newOrder.setCustomer(customer);
        newOrder.setListPizza(pizzas);
        orderRepository.saveOrder(newOrder);
        return newOrder;
    }

    private List<Pizza> pizzasListfromArrayOfIds(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizzaByID(id)); // get domain.Pizza from predifined in-memory list
        }
        return pizzas;
    }

    @Lookup
    protected Order getNewOrder(){
        return null;
    }

}
