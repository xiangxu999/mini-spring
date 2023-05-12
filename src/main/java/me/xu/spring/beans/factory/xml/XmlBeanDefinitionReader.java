package me.xu.spring.beans.factory.xml;

import me.xu.spring.beans.factory.config.*;
import me.xu.spring.beans.factory.support.AutowireCapableBeanFactory;
import me.xu.spring.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Description xml读取后解析为BeanDefinition
 * Date 2023/5/5 10:08
 * Version 1.0.1
 *
 * @author Wen
 */
public class XmlBeanDefinitionReader {

    AutowireCapableBeanFactory beanFactory;

    /**
     * 构造函数
     */
    public XmlBeanDefinitionReader(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
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

            // 处理构造器参数
            List<Element> constrElements = element.elements("constructor-arg");
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            for (Element e : constrElements) {
                // 获取属性
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                // 添加到构造器参数集合中
                constructorArgumentValues.addArgumentValue(new ConstructorArgumentValue(type, name, value));
            }
            beanDefinition.setConstructorArgumentValues(constructorArgumentValues);

            // 处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues propertyValues = new PropertyValues();
            // Bean的依赖集合
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                // 获取属性
                String name = e.attributeValue("name");
                String type = e.attributeValue("type");
                String value = e.attributeValue("value");
                // 获取依赖
                String ref = e.attributeValue("ref");
                // 真实value
                String pV = "";
                boolean isRef = false;
                if (value != null && !"".equals(value)) {
                    pV = value;
                } else if (ref != null && !"".equals(ref)) {
                    isRef = true;
                    pV = ref;
                    refs.add(ref);
                }
                // 添加到属性集合中
                propertyValues.addPropertyValue(new PropertyValue(type, name, pV, isRef));
            }
            // 设置配置属性集合
            beanDefinition.setPropertyValues(propertyValues);
            String[] refArray = refs.toArray(new String[0]);
            // 设置依赖集合
            beanDefinition.setDependsOn(refArray);

            beanFactory.registerBeanDefinition(beanID, beanDefinition);
        }
    }

}
