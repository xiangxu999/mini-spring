package me.xu.spring.test.demo02;

/**
 * Description A具体实现
 * Date 2023/5/8 9:25
 * Version 1.0.1
 *
 * @author Wen
 */
public class AServiceImpl implements AService {

    private String content;

    @Override
    public void A() {
        System.out.println(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
