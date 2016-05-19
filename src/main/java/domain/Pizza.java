package domain;

public class Pizza {
    private Integer id;
    private String name;
    private Double price;
    private PizzaType type;

    public enum PizzaType {
        MEAT,
        TOMATO,
        PINEAPPLE
    }

    public Pizza() {
    }

    public Pizza(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pizza(Integer id, String name, Double price, PizzaType type) {
        this(id, name);
        this.price = price;
        this.type = type;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\ndomain.Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
