package me.xu.spring.beans;

import me.xu.spring.exception.BeansException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description Bean工厂具体实现
 * Date 2023/5/5 10:14
 * Version 1.0.1
 *
 * @author Wen
 */
public class SimpleBeanFactory implements BeanFactory {

    /**
     * Bean定义集合
     */
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    /**
     * Bean的id集合
     */
    private List<String> beanNames = new ArrayList<>();

    /**
     * Bean单例集合
     */
    private Map<String, Object> singletons = new HashMap<>();

    public SimpleBeanFactory() {

    }


    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先尝试从单例集合中获取
        Object singleton = singletons.get(beanName);
        // 如果没有，开始实例化Bean
        if (singleton == null) {
            // 序号
            int i = beanNames.indexOf(beanName);
            if (i == -1) {
                throw new BeansException("BeanName错误");
            } else {
                // 获取Bean定义对象
                BeanDefinition beanDefinition = beanDefinitions.get(i);
                try {
                    // 反射创建Bean对象
                    singleton = Class.forName(beanDefinition.getClassName()).newInstance();
                    // 注册Bean实例
                    singletons.put(beanDefinition.getId(), singleton);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return singleton;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.add(beanDefinition);
        beanNames.add(beanDefinition.getId());
    }
}
