package cn.heposay.ai.domain.chatgpt.service;

import cn.heposay.ai.domain.chatgpt.domain.aggregates.ChatGptAnswerAggregates;

import java.io.IOException;

/**
 * @author heposay
 * @description chatgpt 调用接口
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 16:43
 */
public interface IOpenApi {
    /**
     * 调用 Chatgpt 接口
     *
     * @param openAiKey 自行申请 https://beta.openai.com/overview
     * @param question 问题信息
     * @return json数据
     * @throws IOException HTTP远程调用过程中如果出现异常，打印异常日志
     */
    String doChatGPT(String openAiKey, String question) throws IOException;
}
