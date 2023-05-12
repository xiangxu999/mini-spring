package me.xu.spring.test.demo03;

import me.xu.spring.context.ClassPathXmlApplicationContext;
import me.xu.spring.exception.BeansException;
import me.xu.spring.test.demo02.BService;

/**
 * Description 案例测试
 * Date 2023/5/12 9:59
 * Version 1.0.1
 *
 * @author Wen
 */
public class Demo {
    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("demo.xml");
        FService fService = (FService)classPathXmlApplicationContext.getBean("FService");
        fService.F();
        fService.useC();

    }
}
