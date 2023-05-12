package me.xu.spring.test.demo03;

import me.xu.spring.beans.factory.annotation.Autowired;

/**
 * Description 父服务方法具体实现类
 * Date 2023/5/12 10:03
 * Version 1.0.1
 *
 * @author Wen
 */
public class FServiceImpl implements FService{

    @Autowired
    private CService CService;

    @Override
    public void F() {
        System.out.println("父服务方法");
    }
    @Override
    public void useC() {
        CService.C();
    }
}
