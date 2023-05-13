package me.xu.spring.beans.factory.support;

import me.xu.spring.beans.factory.BeanFactory;
import me.xu.spring.exception.BeansException;


/**
 * Description 注解处理工厂类
 * Date 2023/5/9 14:11
 * Version 1.0.1
 *
 * @author Wen
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    int AUTOWIRE_NO = 0;
    int AUTOWIRE_BY_NAME = 1;
    int AUTOWIRE_BY_TYPE = 2;

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
