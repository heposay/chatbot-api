package cn.heposay.ai.domain.chatgpt.domain.res;

import cn.heposay.ai.domain.chatgpt.domain.vo.ChoicesItem;
import cn.heposay.ai.domain.chatgpt.domain.vo.Usage;
import lombok.Data;

import java.util.List;

/**
 * @author heposay
 * @description 创建补全响应   <a href="https://platform.openai.com/docs/api-reference/completions/create">参考文档</a>
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 16:40
 */
@Data
public class CreateCompletionResponse {
    private Integer created;

    private Usage usage;

    private String model;

    private String id;

    /**
     * 回答列表
     */
    private List<ChoicesItem> choices;

    private String object;
}
