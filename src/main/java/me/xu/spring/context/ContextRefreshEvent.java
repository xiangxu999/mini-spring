package me.xu.spring.context;

/**
 * Description Todo
 * Date 2023/5/13 10:53
 * Version 1.0.1
 *
 * @author Wen
 */

public class ContextRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 1L;

    public ContextRefreshEvent(Object arg0) {
        super(arg0);
    }

    public String toString() {
        return this.msg;
    }
}
