package me.xu.spring.beans.factory;

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
     * 是否包含某个Bean
     * @param name Bean id
     */
    Boolean containsBean(String name);

    /**
     * 注册单例Bean
     * @param beanName Bean id
     * @param obj Bean对象
     */
    void registerBean(String beanName, Object obj);

    /**
     * 判断是否为单例
     * @param name 别名
     * @return
     */
    boolean isSingleton(String name);

    /**
     * 判断是否为原型
     * @param name 别名
     * @return
     */
    boolean isPrototype(String name);

    Class getType(String name);
}
