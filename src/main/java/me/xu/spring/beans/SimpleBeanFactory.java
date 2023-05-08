package me.xu.spring.beans;

import me.xu.spring.exception.BeansException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description Bean工厂具体实现
 * Date 2023/5/5 10:14
 * Version 1.0.1
 *
 * @author Wen
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitions = new ConcurrentHashMap<>(256);

    private List<String> beanDefinitionNames = new ArrayList<>();

    /**
     * Bean半成品
     */
    private final Map<String, Object> earlySingletonObjects = new HashMap<String, Object>(16);

    public SimpleBeanFactory() {

    }

    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先尝试直接单例获取
        Object singleton = getSingleton(beanName);
        // 如果没有，开始实例化Bean
        if (singleton == null) {
            // 尝试从Bean半成品中获取
            singleton = earlySingletonObjects.get(beanName);
            if (singleton == null) {
                // 如果半成品也没有，创建Bean实例并注册
                // 获取Bean定义
                BeanDefinition beanDefinition = beanDefinitions.get(beanName);
                try {
                    // 反射实例化Bean
                    singleton = createBean(beanDefinition);
                    // 注册单例Bean
                    registerSingleton(beanName, singleton);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return singleton;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        // 类名
        Class<?> clz = null;
        // 创建半成品Bean
        Object obj = doCreateBean(beanDefinition);
        // 放入半成品Bean集合中
        earlySingletonObjects.put(beanDefinition.getId(), obj);
        try {
            // 反射获取类名
            clz = Class.forName(beanDefinition.getClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 处理配置参数（属性注入）
        handleProperty(beanDefinition, clz, obj);
        return obj;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) {
        Class clz = null;
        Object obj = null;
        Constructor constructor = null;

        try {
            clz = Class.forName(beanDefinition.getClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 处理构造器参数
        ArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
        if (!argumentValues.isEmpty()) {
            // 参数类型
            Class<?>[] paramTypes = new Class<?>[argumentValues.getArgumentCount()];
            // 参数值
            Object[] paramValues = new Object[argumentValues.getArgumentCount()];
            // 遍历构造器参数集合
            for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                if ("String".equals(argumentValue.getType()) || "java.lang.String".equals(argumentValue.getType())) {
                    paramTypes[i] = String.class;
                    paramValues[i] = argumentValue.getValue();
                } else if ("Integer".equals(argumentValue.getType()) || "java.lang.Integer".equals(argumentValue.getType())) {
                    paramTypes[i] = Integer.class;
                    paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                } else if ("int".equals(argumentValue.getType())) {
                    paramTypes[i] = int.class;
                    paramValues[i] = Integer.valueOf((String) argumentValue.getValue()).intValue();
                } else {
                    paramTypes[i] = String.class;
                    paramValues[i] = argumentValue.getValue();
                }
            }
            try {
                // 获取构造函数
                constructor = clz.getConstructor(paramTypes);
                // 利用构造函数反射实例化Bean
                obj = constructor.newInstance(paramValues);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalArgumentException | SecurityException e) {
                e.printStackTrace();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                obj = clz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    private void handleProperty(BeanDefinition beanDefinition, Class<?> clz, Object obj) {
        // 处理属性
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            // 遍历属性集合
            for (int i = 0; i < propertyValues.size(); i++) {
                PropertyValue propertyValue = propertyValues.getPropertyValueList().get(i);
                String pName = propertyValue.getName();
                String pType = propertyValue.getType();
                Object pValue = propertyValue.getValue();
                boolean isRef = propertyValue.isRef();

                Class<?>[] paramTypes = new Class<?>[1];
                Object[] paramValues = new Object[1];

                // 如果是基本类型
                if (!isRef) {
                    // 参数类型处理
                    if ("String".equals(pType) || "java.lang.String".equals(pType)) {
                        paramTypes[0] = String.class;
                    } else if ("Integer".equals(pType) || "java.lang.Integer".equals(pType)) {
                        paramTypes[0] = Integer.class;
                    } else if ("int".equals(pType)) {
                        paramTypes[0] = int.class;
                    } else {
                        paramTypes[0] = String.class;
                    }
                    // 参数具体数值
                    paramValues[0] = pValue;
                } else {
                    // 如果是引用类型
                    try {
                        paramTypes[0] = Class.forName(pType);
                        // 再次调用getBean方法
                        paramValues[0] = getBean((String)pValue);
                    } catch (ClassNotFoundException | BeansException e) {
                        throw new RuntimeException(e);
                    }
                }

                // 方法名字
                String methodName = "set" + pName.substring(0, 1).toUpperCase() + pName.substring(1);

                Method method = null;
                try {
                    // 获取到方法
                    method = clz.getMethod(methodName, paramTypes);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
                try {
                    // 反射调用方法
                    method.invoke(obj, paramValues);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public Boolean containsBean(String name) {
        return containsSingleton(name);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        registerSingleton(beanName, obj);
    }

    @Override
    public boolean isSingleton(String name) {
        return beanDefinitions.get(name).isSingleton();
    }

    @Override
    public boolean isPrototype(String name) {
        return beanDefinitions.get(name).isPrototype();
    }

    @Override
    public Class getType(String name) {
        return beanDefinitions.get(name).getClass();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitions.put(name, beanDefinition);
        beanDefinitionNames.add(name);
        if (!beanDefinition.isLazyInit()) {
            try {
                getBean(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        beanDefinitions.remove(name);
        beanDefinitionNames.remove(name);
        removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitions.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return beanDefinitions.containsKey(name);
    }
}
