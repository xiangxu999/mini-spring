package me.xu.spring.beans.factory.annotation;

import me.xu.spring.beans.factory.BeanFactory;
import me.xu.spring.beans.factory.config.BeanPostProcessor;
import me.xu.spring.exception.BeansException;

import java.lang.reflect.Field;

/**
 * Description Bean处理器接口具体实现
 * Date 2023/5/9 13:45
 * Version 1.0.1
 *
 * @author Wen
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        Class<?> clazz = bean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                boolean isAutowired = field.isAnnotationPresent(me.xu.spring.beans.factory.annotation.Autowired.class);
                if (isAutowired) {
                    String fieldName = field.getName();
                    try {
                        Object autowiredObj = this.getBeanFactory().getBean(fieldName);
                        field.setAccessible(true);
                        field.set(bean, autowiredObj);
                    } catch (BeansException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public BeanFactory  getBeanFactory() {
        return beanFactory;
    }

}
