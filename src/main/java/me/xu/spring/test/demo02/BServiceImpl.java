package me.xu.spring.test.demo02;

/**
 * Description Todo
 * Date 2023/5/8 9:34
 * Version 1.0.1
 *
 * @author Wen
 */
public class BServiceImpl implements BService{

    private String content;

    private int type;

    private AService aService;

    public BServiceImpl(String content, int type) {
        this.content = content;
        this.type = type;
    }

    @Override
    public void B() {
        System.out.println(type + ":" + content);
    }

    @Override
    public void userA() {
        aService.A();
    }

    public void setAService(AServiceImpl aService) {
        this.aService = aService;
    }
}
