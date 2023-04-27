package cn.heposay.ai.domain.chatgpt.domain.vo;

import lombok.Data;

/**
 * @author heposay
 * @description
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 16:43
 */
@Data
public class Usage {
    private Integer completionTokens;

    private Integer promptTokens;

    private Integer totalTokens;
}
