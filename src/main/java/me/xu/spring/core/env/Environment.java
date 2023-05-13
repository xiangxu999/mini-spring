package me.xu.spring.core.env;

/**
 * Description 环境管理
 * Date 2023/5/13 10:11
 * Version 1.0.1
 *
 * @author Wen
 */

public interface Environment extends PropertyResolver {
    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);
}
