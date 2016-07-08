package infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyForBenchmark {
    private Object bean;
    public ProxyForBenchmark(Object bean) {
        this.bean = bean;
    }

    public Object createProxy() {
        Object proxedBean
                = Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                handler
        );

        return proxedBean;
    }

    InvocationHandler handler = new InvocationHandler() {
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            Benchmark annotation = bean.getClass()
                    .getMethod(method.getName(), method.getParameterTypes())
                    .getAnnotation(Benchmark.class);


            if((annotation != null) && annotation.active()) {
                long start = System.nanoTime();
                Object result = method.invoke(bean, args);
                long end = System.nanoTime();
                System.out.println("Benchmark for method " + method.getName() + ": " + (end - start));
                return result;
            }

            return method.invoke(bean, args);
        }
    };
}
