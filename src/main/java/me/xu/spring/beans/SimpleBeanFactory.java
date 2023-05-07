package me.xu.spring.beans;

import me.xu.spring.exception.BeansException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description Bean工厂具体实现
 * Date 2023/5/5 10:14
 * Version 1.0.1
 *
 * @author Wen
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    public SimpleBeanFactory() {

    }

    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.put(beanDefinition.getId(), beanDefinition);
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先尝试直接单例获取
        Object singleton = getSingleton(beanName);
        // 如果没有，开始实例化Bean
        if (singleton == null) {
            // 获取Bean定义
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new BeansException("错误Bean id");
            }
            try {
                // 反射实例化Bean
                singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                // 注册单例Bean
                registerSingleton(beanName, singleton);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return singleton;
    }

    @Override
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        registerSingleton(beanName, obj);
    }
}
