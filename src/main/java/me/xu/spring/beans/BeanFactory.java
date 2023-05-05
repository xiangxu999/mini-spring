package me.xu.spring.beans;

import me.xu.spring.exception.BeansException;

/**
 * Description Bean工厂
 * Date 2023/5/5 9:53
 * Version 1.0.1
 *
 * @author Wen
 */
public interface BeanFactory {

    /**
     * 根据Bean名称，获取Bean对象
     *
     * @param beanName Bean名称
     * @return Bean对象
     * @throws BeansException Bean自定义异常
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 注册BeanDefinition
     *
     * @param beanDefinition Bean定义对象
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);
}
