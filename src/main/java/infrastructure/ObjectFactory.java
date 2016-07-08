package infrastructure;

public class ObjectFactory implements ServiceLocator{
    private Config config;


    public ObjectFactory(Config config) {
        this.config = config;
    }

    public Object lookUp(String beanName) throws Exception{
        Class<?> clazz = config.getImplementation(beanName);
        if(clazz != null){
            return clazz.newInstance();
        }
        return null;
    }
}
