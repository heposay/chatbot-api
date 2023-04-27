package cn.heposay.ai.domain.chatgpt.domain.req;

import lombok.Data;

/**
 * @author heposay
 * @description 创建补全请求    <a href="https://platform.openai.com/docs/api-reference/completions/create">参考文档</a>
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 16:40
 */
@Data
public class CreateCompletionRequest {
    /**
     * 模型
     */
    private String model;

    /**
     * 提示词
     */
    private String prompt;

    private Integer max_tokens;

    private Integer temperature;

    private Integer top_p;

    private Integer n;

    private Boolean stream;

    private Integer logprobs;

    private String stop;
}
