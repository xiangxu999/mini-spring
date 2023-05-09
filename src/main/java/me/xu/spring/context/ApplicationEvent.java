package me.xu.spring.context;

import java.util.EventObject;

/**
 * Description 事件监听
 * Date 2023/5/7 14:28
 * Version 1.0.1
 *
 * @author Wen
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public ApplicationEvent(Object arg0) {
        super(arg0);
    }

}
