package me.xu.spring.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * Description xml资源加载
 * Date 2023/5/5 10:00
 * Version 1.0.1
 *
 * @author Wen
 */

public class ClassPathXmlResource implements Resource {
    Document document;
    Element rootElement;
    Iterator<Element> elementIterator;
    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader = new SAXReader();
        URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
        // 将配置文件装载进来，生成一个迭代器，可以用于遍历
        try {
            this.document = saxReader.read(xmlPath);
            this.rootElement = document.getRootElement();
            this.elementIterator = this.rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }
    public Object next() {
        return this.elementIterator.next();
    }
}
