package me.xu.spring.context;

import me.xu.spring.beans.BeanFactory;
import me.xu.spring.beans.SimpleBeanFactory;
import me.xu.spring.beans.XmlBeanDefinitionReader;
import me.xu.spring.core.ClassPathXmlResource;
import me.xu.spring.core.Resource;
import me.xu.spring.exception.BeansException;

/**
 * Description 解析XML构建上下文
 * Date 2023/4/28 14:31
 * Version 1.0.1
 *
 * @author Wen
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    SimpleBeanFactory simpleBeanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        // 获取资源
        Resource resource = new ClassPathXmlResource(fileName);
        // 解析资源
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.simpleBeanFactory = beanFactory;
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return simpleBeanFactory.getBean(beanName);
    }

    @Override
    public Boolean containsBean(String name) {
        return simpleBeanFactory.containsBean(name);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        simpleBeanFactory.registerBean(beanName, obj);
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
