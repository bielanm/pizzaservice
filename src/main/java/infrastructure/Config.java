package infrastructure;

public interface Config{
    Class<?> getImplementation(String beanName);
}
