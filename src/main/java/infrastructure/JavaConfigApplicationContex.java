package infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class JavaConfigApplicationContex implements ApplicationContex {
    private Config config;
    private Map<String, Object> beans = new HashMap<String, Object>();

    public JavaConfigApplicationContex(Config config) {
        this.config = config;
    }

    public Object getBean(String beanName) throws Exception{
        Object bean = beans.get(beanName);
        if(bean != null)
            return bean;

        BeanBuilder beanBuilder = new BeanBuilder(beanName);
        beanBuilder.createBean();
        beanBuilder.callInitMethod();
        beanBuilder.createProxy();
        bean = beanBuilder.build();

        beans.put(beanName, bean);
        return bean;
    }

    public class BeanBuilder {
        Object bean;
        Class<?> type;

        private BeanBuilder(String beanName) {
            type = config.getImplementation(beanName);
        }

        private void callInitMethod() throws InvocationTargetException, IllegalAccessException {
            try {
                Method method = type.getMethod("init");
                method.invoke(bean);
            } catch (NoSuchMethodException e) {
                return;
            }
        }


        private void createProxy() {
            bean = new ProxyForBenchmark(bean).createProxy();
        }

        private Object build() {
            return bean;
        }

        private void createBean() throws Exception {
            Constructor constructor = type.getConstructors()[0];

            Class[] paramTypes = constructor.getParameterTypes();
            int size = (paramTypes == null) ? 0 : paramTypes.length;

            if (size == 0) {
                bean = type.newInstance();
            } else {
                Object[] params = new Object[size];
                for (int i = 0; i < paramTypes.length; i++) {
                    Object param = getBeanByType(paramTypes[i]);
                    params[i] = param;
                }
                bean = constructor.newInstance(params);
            }
        }

    }

    private Object getBeanByType(Class paramType) throws Exception{
        String beanName = getBeanNameByType(paramType);
        return getBean(beanName);
    }

    private String getBeanNameByType(Class beanType) {
        String typeName = beanType.getSimpleName();
        return typeName.substring(0, 1).toLowerCase() + typeName.substring(1);
    }
}
