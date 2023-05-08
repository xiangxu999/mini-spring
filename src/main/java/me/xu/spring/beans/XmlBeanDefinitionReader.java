package me.xu.spring.beans;

import me.xu.spring.core.Resource;
import org.dom4j.Element;

import java.util.List;

/**
 * Description xml读取后解析为BeanDefinition
 * Date 2023/5/5 10:08
 * Version 1.0.1
 *
 * @author Wen
 */
public class XmlBeanDefinitionReader {

    SimpleBeanFactory simpleBeanFactory;

    /**
     * 构造函数
     */
    public XmlBeanDefinitionReader(SimpleBeanFactory simpleBeanFactory) {
        this.simpleBeanFactory = simpleBeanFactory;
    }

    /**
     * 遍历资源，将资源中的bean对象进行注册
     * @param resource 外部资源
     */
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            Element element = (Element) resource.next();
            String beanID = element.attributeValue("id");
            String beanClassName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
            // 解析动作开始

            // 处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            for (Element e : propertyElements) {
                // 获取属性
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                // 添加到属性集合中
                propertyValues.addPropertyValue(new PropertyValue(type, name, value));
            }
            beanDefinition.setPropertyValues(propertyValues);

            // 处理构造器参数
            List<Element> constrElements = element.elements("constructor-arg");
            ArgumentValues argumentValues = new ArgumentValues();
            for (Element e : constrElements) {
                // 获取属性
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                // 添加到构造器参数集合中
                argumentValues.addArgumentValue(new ArgumentValue(type, name, value));
            }
            beanDefinition.setConstructorArgumentValues(argumentValues);

            simpleBeanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }

}
