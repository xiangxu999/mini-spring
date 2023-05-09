package me.xu.spring.beans.factory.support;

/**
 * Description Bean单例管理接口
 * Date 2023/5/7 11:18
 * Version 1.0.1
 *
 * @author Wen
 */
public interface SingletonBeanRegistry {

    /**
     * 注册单例
     * @param beanName Bean id
     * @param singletonObject Bean对象
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 获取单例
     * @param beanName Bean id
     * @return Bean对象
     */
    Object getSingleton(String beanName);

    /**
     * 是否包含某个单例Bean
     * @param beanName Bean id
     * @return
     */
    boolean containsSingleton(String beanName);

    /**
     * 获取所有单例名称
     * @return
     */
    String[] getSingletonNames();
}
