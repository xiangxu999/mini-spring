package me.xu.spring.beans;

/**
 * Description Bean定义（最简单的id和class）
 * Date 2023/4/28 14:06
 * Version 1.0.1
 *
 * @author Wen
 */
public class BeanDefinition {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Bean id
     */
    private String id;

    /**
     * Bean类名
     */
    private String className;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

}
