package cn.heposay.ai.domain.chatgpt.domain.aggregates;

import cn.heposay.ai.domain.chatgpt.domain.vo.Choices;

import java.util.List;

/**
 * @author heposay
 * @description chatgpt 回复答案的聚合信息
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 16:40
 */
public class ChatGptAnswerAggregates {
    private String id;

    private String object;

    private int created;

    private String model;

    private List<Choices> choices;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getObject() {
        return this.object;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getCreated() {
        return this.created;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public List<Choices> getChoices() {
        return this.choices;
    }
}
