package infrastructure;

public interface ServiceLocator {
    Object lookUp(String beanName) throws Exception;
}
