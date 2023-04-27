package cn.heposay.ai.application.answer;

import cn.heposay.ai.common.config.OpenAiConfig;
import cn.heposay.ai.common.utils.SpringContextUtils;
import cn.heposay.ai.domain.chatgpt.domain.req.CreateCompletionRequest;
import cn.heposay.ai.domain.chatgpt.domain.res.CreateCompletionResponse;
import cn.heposay.ai.domain.chatgpt.domain.vo.ChoicesItem;
import cn.heposay.ai.domain.chatgpt.service.impl.OpenApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heposay
 * @description OpenAI 回答
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:24
 */
@Slf4j
public class OpenAiAnswerer implements Answerer {
    /**
     * OpenAI 接口
     */
    private final OpenApi openApi = SpringContextUtils.getBean(OpenApi.class);
    /**
     * OpenAI 配置类
     */
    private final OpenAiConfig openAiConfig = SpringContextUtils.getBean(OpenAiConfig.class);

    @Override
    public String doAnswer(String prompt) {
        CreateCompletionRequest request = new CreateCompletionRequest();
        request.setPrompt(prompt);
        request.setModel(openAiConfig.getModel());
        request.setTemperature(0);
        request.setMax_tokens(1024);
        CreateCompletionResponse response = openApi.doChatGPT(request, openAiConfig.getApiKey());
        List<ChoicesItem> choices = response.getChoices();
        String answer = choices.stream().map(ChoicesItem::getText).collect(Collectors.joining());
        log.info("OpenAiAnswerer 回答成功 \n 答案：{}", answer);
        return answer;
    }
}
