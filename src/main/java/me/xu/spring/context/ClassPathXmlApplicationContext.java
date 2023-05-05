package me.xu.spring.context;

import me.xu.spring.bean.BeanDefinition;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

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
public class ClassPathXmlApplicationContext {

    /**
     * Bean集合
     */
    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    /**
     * Bean的单例
     */
    private Map<String, Object> singletons = new HashMap<>();

    public ClassPathXmlApplicationContext(String fileName) {
        this.readXml(fileName);
        this.instanceBeans();
    }

    /**
     * 解析XML，获取Bean实例，放入Bean集合中
     */
    private void readXml(String fileName) {
        SAXReader saxReader = new SAXReader();
        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = saxReader.read(xmlPath);
            Element rootElement = document.getRootElement();
            // 对配置文件中每一个Bean进行处理
            for (Element element : (List<Element>) rootElement.elements()) {
                String beanID = element.attributeValue("id");
                String beanClassName = element.attributeValue("class");
                BeanDefinition beanDefinition = new BeanDefinition(beanID, beanClassName);
                beanDefinitions.add(beanDefinition);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void instanceBeans() {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            try {
                // 反射创建Bean实例放入单例中
                singletons.put(beanDefinition.getId(), Class.forName(beanDefinition.getClassName()).newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Object getBean(String beanName) {
        return singletons.get(beanName);
    }

}
