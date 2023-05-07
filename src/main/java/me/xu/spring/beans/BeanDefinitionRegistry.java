package me.xu.spring.beans;

/**
 * Description BeanDefinition操作接口
 * Date 2023/5/7 14:58
 * Version 1.0.1
 *
 * @author Wen
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册BeanDefinition
     * @param name 别名
     * @param beanDefinition BeanDefinition
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);

    /**
     * 删除BeanDefinition
     * @param name 别名
     */
    void removeBeanDefinition(String name);

    /**
     * 获取BeanDefinition
     * @param name 别名
     */
    BeanDefinition getBeanDefinition(String name);

    /**
     * 是否包含某个BeanDefinition
     * @param name 别名
     * @return
     */
    boolean containsBeanDefinition(String name);
}
