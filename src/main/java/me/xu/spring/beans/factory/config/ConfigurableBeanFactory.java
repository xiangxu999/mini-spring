package me.xu.spring.beans.factory.config;

import me.xu.spring.beans.factory.BeanFactory;
import me.xu.spring.beans.factory.support.SingletonBeanRegistry;

/**
 * Description Bean和Bean处理器管理接口
 * Date 2023/5/12 14:52
 * Version 1.0.1
 *
 * @author Wen
 */

public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 添加Bean处理器
     * @param beanPostProcessor Bean处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 获取Bean处理器数量
     * @return
     */
    int getBeanPostProcessorCount();

    /**
     * 注册Bean依赖
     * @param beanName Bean id
     * @param dependentBeanName 依赖Bean id
     */
    void registerDependentBean(String beanName, String dependentBeanName);

    /**
     * 获取依赖
     * @param beanName Bean id
     * @return String []
     */
    String[] getDependentBeans(String beanName);

    /**
     * 获取依赖
     * @param beanName Bean id
     * @return String []
     */
    String[] getDependenciesForBean(String beanName);
}
