package domain;

public class Customer {
    private String name;
    private final Integer ID;
    private static int number = 0;

    public Customer(String name) {
        this.name = name;
        this.ID = number;
        number++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
