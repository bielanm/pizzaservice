package repository;

import domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemOrderRepository implements OrderRepository{
    private List<Order> orders = new ArrayList<Order>();

    public Integer saveOrder(Order order) {
        orders.add(order);
        return orders.indexOf(order);
    }
}
