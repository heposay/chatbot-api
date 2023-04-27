package cn.heposay.ai.application.answer;

/**
 * @author heposay
 * @description 回答者
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 17:59
 */
public interface Answerer {
    /**
     * 回答
     *
     * @param prompt 提示语
     * @return 回答结果
     */
    String doAnswer(String prompt);
}
