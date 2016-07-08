package app;

import domain.Customer;
import domain.Order;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.OrderService;

public class SpringPizzaServiceApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext repositoryContext =
                new ClassPathXmlApplicationContext("repositoryContext.xml");

        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);
        context.setParent(repositoryContext);
        context.refresh();

        OrderService orderService = (OrderService)context.getBean("orderService");
        Customer customer = new Customer("Misha");

        Order order;
        order = orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);

        order = orderService.placeNewOrder(customer, 2);
        System.out.println(order);

        context.close();
        repositoryContext.close();
    }
}
