package me.xu.spring.context;

import me.xu.spring.beans.factory.ListableBeanFactory;
import me.xu.spring.beans.factory.config.BeanFactoryPostProcessor;
import me.xu.spring.beans.factory.config.ConfigurableBeanFactory;
import me.xu.spring.beans.factory.config.ConfigurableListableBeanFactory;
import me.xu.spring.core.env.Environment;
import me.xu.spring.core.env.EnvironmentCapable;
import me.xu.spring.exception.BeansException;


/**
 * Description Todo
 * Date 2023/5/13 11:12
 * Version 1.0.1
 *
 * @author Wen
 */
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, ConfigurableBeanFactory, ApplicationEventPublisher {
    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    void refresh() throws BeansException, IllegalStateException;

    void close();

    boolean isActive();
}