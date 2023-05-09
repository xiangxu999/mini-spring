package me.xu.spring.beans.factory.support;

import me.xu.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description Bean单例管理具体实现
 * Date 2023/5/7 11:31
 * Version 1.0.1
 *
 * @author Wen
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 存放所有Bean的名称
     */
    protected List<String> beanNames = new ArrayList<>();

    /**
     * Bean单例Map
     */
    protected Map<String, Object> singletons = new ConcurrentHashMap<>();

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        synchronized (singletons) {
            singletons.put(beanName, singletonObject);
            beanNames.add(beanName);
        }
    }

    protected void removeSingleton(String beanName) {
        synchronized (singletons) {
            beanNames.remove(beanName);
            singletons.remove(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return (String[]) this.beanNames.toArray();
    }
}
