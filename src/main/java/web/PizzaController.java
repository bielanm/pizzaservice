package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import domain.Pizza;
import org.springframework.web.util.UriComponentsBuilder;
import service.PizzaService;

import javax.xml.ws.Response;
import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello from PizzaController";
    }


    @RequestMapping(value = "pizza", method = RequestMethod.GET)
    public List<Pizza> pizzas(){
        return pizzaService.getPizzas();
    }

    @RequestMapping(value = "pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> pizzaById(@PathVariable("id") Integer id){
        Pizza pizza = pizzaService.getPizzaByID(id);
        if(pizza == null)
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Pizza>(pizza, HttpStatus.FOUND);
    }

    @RequestMapping(value = "pizza/{id}/price", method = RequestMethod.GET)
    public ResponseEntity<Double> pizzaPriceById(@PathVariable("id") Integer id){
        Pizza pizza = pizzaService.getPizzaByID(id);
        if(pizza == null)
            return new ResponseEntity<Double>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Double>(pizza.getPrice(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "pizza", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Integer> add(@RequestBody Pizza pizza, UriComponentsBuilder builder){
        Pizza newPizza = pizza;
//      newPizza = pizzaService.addPizza(pizza);
        newPizza.setId(42);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/pizza/{id}").buildAndExpand(newPizza.getId()).toUri());
        return new ResponseEntity<Integer>(newPizza.getId(), HttpStatus.CREATED);
    }
}
