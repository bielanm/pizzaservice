package repository;
import domain.Order;

public interface OrderRepository {
    Integer saveOrder(Order order);
}
