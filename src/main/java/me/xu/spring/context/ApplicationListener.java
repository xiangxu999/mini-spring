package me.xu.spring.context;

import java.util.EventListener;

/**
 * Description Todo
 * Date 2023/5/13 10:52
 * Version 1.0.1
 *
 * @author Wen
 */

public class ApplicationListener implements EventListener {
    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}