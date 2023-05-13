package me.xu.spring.beans.factory;

import me.xu.spring.exception.BeansException;

import java.util.Map;

/**
 * Description Bean定义集合管理接口
 * Date 2023/5/12 14:47
 * Version 1.0.1
 *
 * @author Wen
 */

public interface ListableBeanFactory extends BeanFactory {

    /**
     * 是否包含某个Bean定义
     * @param beanName Bean id
     * @return boolean
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 获取Bean定义的数量
     * @return int
     */
    int getBeanDefinitionCount();

    /**
     * 获取所有Bean定义名称
     * @return String[]
     */
    String[] getBeanDefinitionNames();

    /**
     * 根据类型获取Bean名称
     * @param type 类型
     * @return String[]
     */
    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}
