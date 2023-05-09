package me.xu.spring.beans.factory.config;

/**
 * Description Setter配置（单个）
 * Date 2023/5/8 9:52
 * Version 1.0.1
 *
 * @author Wen
 */
public class PropertyValue {

    private final String name;
    private final String type;
    private final Object value;
    private final boolean isRef;

    public PropertyValue(String type, String name, Object value, boolean isRef) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.isRef = isRef;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isRef() {
        return isRef;
    }
}
