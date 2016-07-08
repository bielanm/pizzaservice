package web;

import domain.Order;
import domain.Pizza;
import service.OrderService;
import service.PizzaService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PizzaServlet extends HttpServlet {

    private ConfigurableApplicationContext repositoryContext;
    private ConfigurableApplicationContext context;

    @Override
    public void init() throws ServletException {
        repositoryContext =
                new ClassPathXmlApplicationContext("repositoryContext.xml");

        context =
                new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);
        context.setParent(repositoryContext);
        context.refresh();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
/* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PizzaServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PizzaServlet at: " + request.getParameter("pizzas") + "</h1>");

            PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
            List<Pizza> pizzas = pizzaService.getPizzas();

            for(Pizza pizza: pizzas){
                out.println(pizza);
                out.println("<br>");
            }

            OrderService orderService = (OrderService) context.getBean("orderService");
            String param = request.getParameter("pizzas");
            if(param != null){
                Integer[] pizzasID = convertStringtoIntArray(param);
                Order order = orderService.placeNewOrder(null, pizzasID);
                out.println("Pizzas in order: " + order.getListPizza());
                out.println("<br>");
                out.println("Total price: " + order.getTotalPrice());
            }

            out.println("</body>");
            out.println("</html>");

        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy() {
        context.close();
        repositoryContext.close();
    }

    public Integer[] convertStringtoIntArray(String arr){
        String[] items = arr.replaceAll("\\[", "").replaceAll("\\]", "").split(",");

        Integer[] results = new Integer[items.length];

        for (int i = 0; i < items.length; i++) {
            try {
                results[i] = Integer.parseInt(items[i]);
            } catch (NumberFormatException nfe) {

            };
        }
        return results;
    }
}
