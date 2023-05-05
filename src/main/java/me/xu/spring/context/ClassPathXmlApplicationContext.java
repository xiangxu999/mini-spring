package me.xu.spring.context;

import me.xu.spring.beans.BeanDefinition;
import me.xu.spring.beans.BeanFactory;
import me.xu.spring.beans.SimpleBeanFactory;
import me.xu.spring.beans.XmlBeanDefinitionReader;
import me.xu.spring.core.ClassPathXmlResource;
import me.xu.spring.core.Resource;
import me.xu.spring.exception.BeansException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.parsing.BeanEntry;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description 解析XML构建上下文
 * Date 2023/4/28 14:31
 * Version 1.0.1
 *
 * @author Wen
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    BeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        // 获取资源
        Resource resource = new ClassPathXmlResource(fileName);
        // 解析资源
        BeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return beanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanDefinition);
    }
}
