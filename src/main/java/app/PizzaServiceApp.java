package app;

import domain.Customer;
import domain.Order;
import infrastructure.ApplicationContex;
import infrastructure.Config;
import infrastructure.JavaConfig;
import infrastructure.JavaConfigApplicationContex;
import service.OrderService;

public class PizzaServiceApp {
    public static void main(String[] args) throws Exception {

        Config config = new JavaConfig();
        ApplicationContex contex = new JavaConfigApplicationContex(config);

        OrderService orderService = (OrderService)contex.getBean("orderService");

        Customer customer = new Customer("Misha");
        Order order;

        order = orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);
        order = orderService.placeNewOrder(customer, 1);
        System.out.println(order);
    }
}
