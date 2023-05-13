package me.xu.spring.context;

/**
 * Description 事件发布
 * Date 2023/5/7 14:33
 * Version 1.0.1
 *
 * @author Wen
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);
}
