package me.xu.spring.context;

import me.xu.spring.beans.factory.BeanFactory;
import me.xu.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import me.xu.spring.beans.factory.config.AbstractAutowireCapableBeanFactory;
import me.xu.spring.beans.factory.config.BeanFactoryPostProcessor;
import me.xu.spring.beans.factory.config.ConfigurableListableBeanFactory;
import me.xu.spring.beans.factory.support.DefaultListableBeanFactory;
import me.xu.spring.beans.factory.xml.XmlBeanDefinitionReader;
import me.xu.spring.core.ClassPathXmlResource;
import me.xu.spring.core.Resource;
import me.xu.spring.exception.BeansException;


import java.util.ArrayList;
import java.util.List;

/**
 * Description 解析XML构建上下文
 * Date 2023/4/28 14:31
 * Version 1.0.1
 *
 * @author Wen
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext{
    DefaultListableBeanFactory beanFactory;
    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors =
            new ArrayList<BeanFactoryPostProcessor>();

    public ClassPathXmlApplicationContext(String fileName){
        this(fileName, true);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh){
        Resource res = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(bf);
        reader.loadBeanDefinitions(res);

        this.beanFactory = bf;

        if (isRefresh) {
            try {
                refresh();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    @Override
    void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    void finishRefresh() {
        publishEvent(new ContextRefreshEvent("Context Refreshed..."));

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }


    @Override
    public void registerBean(String beanName, Object obj) {

    }
}
