package me.xu.spring.beans.factory.support;

import me.xu.spring.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import me.xu.spring.exception.BeansException;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 注解处理工厂类
 * Date 2023/5/9 14:11
 * Version 1.0.1
 *
 * @author Wen
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

    private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor beanPostProcessor) {
        beanPostProcessorList.remove(beanPostProcessor);
        beanPostProcessorList.add(beanPostProcessor);
    }
    public int getBeanPostProcessorCount() {
        return beanPostProcessorList.size();
    }
    public List<AutowiredAnnotationBeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessorList;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            beanPostProcessor.setBeanFactory(this);
            result = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            beanPostProcessor.setBeanFactory(this);
            result = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (result == null) {
                return result;
            }
        }
        return result;
    }
}
