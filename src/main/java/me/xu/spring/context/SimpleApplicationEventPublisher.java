package me.xu.spring.context;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 事件发布具体实现
 * Date 2023/5/13 10:55
 * Version 1.0.1
 *
 * @author Wen
 */

public class SimpleApplicationEventPublisher implements ApplicationEventPublisher {
    List<ApplicationListener> listeners = new ArrayList<>();

    @Override
    public void publishEvent(ApplicationEvent event) {
        for (ApplicationListener listener : listeners) {
            listener.onApplicationEvent(event);
        }
    }

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }
}
