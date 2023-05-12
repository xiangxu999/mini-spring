package me.xu.spring.beans.factory.config;

/**
 * Description Bean处理器接口
 * Date 2023/5/9 9:50
 * Version 1.0.1
 *
 * @author Wen
 */
public interface BeanPostProcessor {


    /**
     * Bean初始化之前
     *
     * @param bean     Bean
     * @param beanName Bean id
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);


    /**
     * Bean初始化之后
     *
     * @param bean     Bean
     * @param beanName Bean id
     */
    Object postProcessAfterInitialization(Object bean, String beanName);

}
