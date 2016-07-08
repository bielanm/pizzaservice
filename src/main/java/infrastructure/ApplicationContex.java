package infrastructure;


public interface ApplicationContex {
    Object getBean(String beanName) throws Exception;
}
