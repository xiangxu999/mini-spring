package me.xu.spring.test.demo01;

import me.xu.spring.context.ClassPathXmlApplicationContext;
import me.xu.spring.exception.BeansException;

/**
 * Description 案例测试01
 * Date 2023/5/5 9:17
 * Version 1.0.1
 *
 * @author Wen
 */
public class Demo {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("demo.xml");
        DemoService demoService = (DemoService)classPathXmlApplicationContext.getBean("DemoService");
        demoService.test();
    }
}
