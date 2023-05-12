package me.xu.spring.beans.factory.config;

import me.xu.spring.beans.factory.BeanFactory;
import me.xu.spring.exception.BeansException;

/**
 * Description Bean工厂处理接口
 * Date 2023/5/9 14:40
 * Version 1.0.1
 *
 * @author Wen
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;
}
