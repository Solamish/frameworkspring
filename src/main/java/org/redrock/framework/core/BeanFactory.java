package org.redrock.framework.core;

import org.redrock.framework.annotation.Autowired;
import org.redrock.framework.annotation.Component;
import org.redrock.framework.demo.MainController;
import org.redrock.framework.util.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 对象工厂
 */
public class BeanFactory {


    /**
     * 单例模式的单例对象
     */
    private static BeanFactory beenFactory;

    /**
     * 类加载器
     */
    private ClassLoader classLoader;

    /**
     * 组件集合 即所有含@Controller注解和@Component注解的类
     */
    private Set<Class<?>> componentSet;

    /**
     * 用来给controller类和对象提供映射的集合
     */
    private Map<Class<?>, Object> controllerMap;

    /**
     * 用来给component类和对象提供映射的集合
     */
    private Map<Class<?>, Object> componentMap;

    /**
     * 获得单例
     * @return
     */
    public static BeanFactory getInstance() {
        if (beenFactory == null) {
            synchronized (BeanFactory.class) {
                if (beenFactory == null) {
                    beenFactory = new BeanFactory();
                }
            }
        }
        return beenFactory;
    }

    public Map<Class<?>, Object> getControllerMap() {
        return controllerMap;
    }

    private BeanFactory() {
        componentMap = new HashMap<>();
        controllerMap = new HashMap<>();
        componentSet = new HashSet<>();
        init();
    }

    /**
     * 初始化 创建所有类的对象
    */
    private void init() {
        Set<Class<?>> classSet = classLoader.getClassSet();
        //先加载类的集合，创建映射
        for (Class<?> clazz : classSet) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> an =annotation.annotationType();
                if (an.equals(Component.class) || an.isAnnotationPresent(Component.class)) {
                    Object obj = ReflectUtil.newInstance(clazz);
                    componentMap.put(clazz, obj);
                    componentSet.add(clazz);
                    if (!an.equals(Component.class)) {
                        controllerMap.put(clazz, obj);
                    }
                }
            }
        }
        //再对每个component里的成员参数赋值
        for (Class<?> clazz : componentSet) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                if (autowired != null) {
                    Object target = componentMap.get(field.getType());
                    if (target != null) {
                        Object obj = componentMap.get(clazz);
                        ReflectUtil.setFieldValue(field, obj, target);
                    } else {
                        throw new RuntimeException("this component '" + field.getName() + "' is undefined");
                    }
                }
            }
        }
    }

    public <T> T getBean(Class<T> clazz) {
        T t = (T) componentMap.get(clazz);
        if (t != null) {
            return t;
        }
        throw new RuntimeException("been not found");
    }

    public static void main(String[] args) {
        System.out.println(MainController.class.getAnnotation(Component.class));
    }




}
