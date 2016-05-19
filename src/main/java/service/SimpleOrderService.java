package service;

import domain.*;
import repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service("orderService")
public class  SimpleOrderService implements OrderService{
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

    public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
        List<Pizza> pizzas = pizzasListfromArrayOfIds(pizzasID);
        Order newOrder = getNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setListPizza(pizzas);
        orderRepository.saveOrder(newOrder);
        return newOrder;
    }

    private List<Pizza> pizzasListfromArrayOfIds(Integer[] pizzasID) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(Integer id : pizzasID){
            pizzas.add(pizzaRepository.getPizzaByID(id));
        }
        return pizzas;
    }

    @Lookup
    protected Order getNewOrder(){
        return null;
    }

}
