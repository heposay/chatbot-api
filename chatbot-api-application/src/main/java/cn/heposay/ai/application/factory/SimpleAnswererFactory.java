package cn.heposay.ai.application.factory;

import cn.heposay.ai.application.answer.Answerer;
import cn.heposay.ai.application.answer.DefaultAnswerer;
import cn.heposay.ai.application.answer.OpenAiAnswerer;

/**
 * @author heposay
 * @description 回答者简单工厂
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:33
 */
public class SimpleAnswererFactory {

    /**
     * 创建回答者
     *
     * @param answerer 回答者类型
     * @return
     */
    public static Answerer createAnswerer(String answerer) {
        switch (answerer) {
            case "openai":
                return new OpenAiAnswerer();
            default:
                return new DefaultAnswerer();
        }
    }
}
