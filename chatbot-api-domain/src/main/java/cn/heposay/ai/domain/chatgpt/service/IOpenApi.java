package cn.heposay.ai.domain.chatgpt.service;

import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.domain.chatgpt.domain.req.CreateCompletionRequest;
import cn.heposay.ai.domain.chatgpt.domain.res.CreateCompletionResponse;

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
     * @param request   创建补全请求
     * @param openAiKey 自行申请 https://beta.openai.com/overview
     * @return json数据
     * @throws IOException HTTP远程调用过程中如果出现异常，打印异常日志
     */
    CreateCompletionResponse doChatGPT(CreateCompletionRequest request, String openAiKey) throws BusinessException;
}
