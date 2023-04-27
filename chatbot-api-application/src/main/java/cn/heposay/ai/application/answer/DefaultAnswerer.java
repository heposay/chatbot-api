package cn.heposay.ai.application.answer;

/**
 * @author heposay
 * @description 默认回答者（降级）
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:24
 */
public class DefaultAnswerer implements Answerer{
    @Override
    public String doAnswer(String prompt) {
        return "抱歉，我不理解您的问题：" + prompt;
    }
}
