package me.xu.spring.beans;

import me.xu.spring.core.Resource;
import org.dom4j.Element;

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
            simpleBeanFactory.registerBeanDefinition(beanDefinition);
        }
    }

}
