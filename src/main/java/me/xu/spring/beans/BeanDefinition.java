package me.xu.spring.beans;


/**
 * Description Bean定义（最简单的id和class）
 * Date 2023/4/28 14:06
 * Version 1.0.1
 *
 * @author Wen
 */
public class BeanDefinition {

    /**
     * 单例
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * Bean id
     */
    private String id;

    /**
     * Bean类名
     */
    private String className;

    /**
     * Bean实例化类型（单例 | 原型）
     */
    private String scope = SCOPE_SINGLETON;

    /**
     * 是否在加载时候初始化
     */
    private boolean lazyInit = false;

    /**
     * 记录Bean之间依赖关系
     */
    private String[] dependsOn;

    /**
     * 初始方法声明
     */
    private String initMethodName;

    private volatile Object beanClass;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

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

    public boolean hasBeanClass() {
        return (this.beanClass instanceof Class);
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass(){

        return (Class<?>) this.beanClass;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public boolean isLazyInit() {
        return this.lazyInit;
    }

    public void setDependsOn(String... dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String[] getDependsOn() {
        return this.dependsOn;
    }

    //public void setConstructorArgumentValues(ArgumentValues constructorArgumentValues) {
    //    this.constructorArgumentValues =
    //            (constructorArgumentValues != null ? constructorArgumentValues : new ArgumentValues());
    //}
    //
    //public ArgumentValues getConstructorArgumentValues() {
    //    return this.constructorArgumentValues;
    //}
    //
    //public boolean hasConstructorArgumentValues() {
    //    return !this.constructorArgumentValues.isEmpty();
    //}
    //public void setPropertyValues(PropertyValues propertyValues) {
    //    this.propertyValues = (propertyValues != null ? propertyValues : new PropertyValues());
    //}
    //
    //public PropertyValues getPropertyValues() {
    //    return this.propertyValues;
    //}
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getInitMethodName() {
        return this.initMethodName;
    }

}
