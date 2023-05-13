package me.xu.spring.context;

import me.xu.spring.beans.factory.BeanFactory;
import me.xu.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import me.xu.spring.beans.factory.config.AbstractAutowireCapableBeanFactory;
import me.xu.spring.beans.factory.config.BeanFactoryPostProcessor;
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
public class ClassPathXmlApplicationContext implements BeanFactory {

    AbstractAutowireCapableBeanFactory beanFactory;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<BeanFactoryPostProcessor>();

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, false);
    }

    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        // 获取资源
        Resource resource = new ClassPathXmlResource(fileName);
        // 解析资源
        //SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        // 创建处理注解的工厂对象
        AbstractAutowireCapableBeanFactory beanFactory = new AbstractAutowireCapableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
        if (!isRefresh) {
            try {
                refresh();
            } catch (BeansException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void refresh() throws BeansException, IllegalStateException {
        // 注册处理器
        registerBeanPostProcessors(this.beanFactory);

        // 初始化Bean容器
        onRefresh();
    }

    private void onRefresh() {
        this.beanFactory.refresh();
    }

    public List getBeanFactoryPostProcessors() { return this.beanFactoryPostProcessors; }
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) { this.beanFactoryPostProcessors.add(postProcessor); }

    private void registerBeanPostProcessors(AbstractAutowireCapableBeanFactory beanFactory) {
        beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    @Override
    public Boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        beanFactory.registerBean(beanName, obj);
    }

    @Override
    public boolean isSingleton(String name) {
        return false;
    }

    @Override
    public boolean isPrototype(String name) {
        return false;
    }

    @Override
    public Class getType(String name) {
        return null;
    }
}
