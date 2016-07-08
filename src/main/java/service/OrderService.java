package service;

import domain.Customer;
import domain.Order;

/**
 * Created by misha on 28.03.16.
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, Integer ... pizzasID);
}
