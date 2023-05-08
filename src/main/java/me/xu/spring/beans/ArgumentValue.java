package me.xu.spring.beans;

/**
 * Description 构造函数配置类
 * Date 2023/5/8 10:08
 * Version 1.0.1
 *
 * @author Wen
 */
public class ArgumentValue {
    private Object value;
    private String type;
    private String name;

    public ArgumentValue(String type, Object value) {
        this.value = value;
        this.type = type;
    }

    public ArgumentValue(String type, String name, Object value) {
        this.value = value;
        this.type = type;
        this.name = name;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
