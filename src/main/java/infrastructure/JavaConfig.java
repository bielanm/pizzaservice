package infrastructure;

import repository.InMemOrderRepository;
import repository.InMemPizzaRepository;
import service.SimpleOrderService;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config{
    private Map<String, Class<?>> beans = new HashMap<String, Class<?>>();
    {
        beans.put("pizzaRepository", InMemPizzaRepository.class);
        beans.put("orderRepository", InMemOrderRepository.class);
        beans.put("orderService", SimpleOrderService.class);
    }

    public Class<?> getImplementation(String beanName) {
        return beans.get(beanName);
    }
}
