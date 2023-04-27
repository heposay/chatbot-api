package cn.heposay.ai.domain.chatgpt.domain.vo;

import lombok.Data;

/**
 * @author heposay
 * @description chatgpt 回复答案的选择信息
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 16:42
 */
@Data
public class ChoicesItem {

    private String finishReason;

    private Integer index;

    private String text;

    private Integer logprobs;
}