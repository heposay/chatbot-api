package cn.heposay.ai.domain.chatgpt.domain.vo;
/**
 * @author heposay
 * @description chatgpt 回复答案的选择信息
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 16:42
 */
public class Choices {

    private String text;

    private int index;

    private String logprobs;

    private String finish_reason;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}