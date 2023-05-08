package me.xu.spring.test.demo02;

import me.xu.spring.context.ClassPathXmlApplicationContext;
import me.xu.spring.exception.BeansException;


/**
 * Description 案例
 * Date 2023/5/8 10:44
 * Version 1.0.1
 *
 * @author Wen
 */
public class DemoA {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("demo.xml");
        AService aService = (AService)classPathXmlApplicationContext.getBean("AService");
        aService.A();
        BService bService = (BService)classPathXmlApplicationContext.getBean("BService");
        bService.B();
    }
}
