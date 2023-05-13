package me.xu.spring.beans.factory.config;

import me.xu.spring.beans.factory.ListableBeanFactory;
import me.xu.spring.beans.factory.support.AutowireCapableBeanFactory;

/**
 * Description 整合接口
 * Date 2023/5/12 15:07
 * Version 1.0.1
 *
 * @author Wen
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

}
