package cn.heposay.ai.domain.chatgpt.service.impl;

import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.common.response.ErrorCode;
import cn.heposay.ai.domain.chatgpt.domain.req.CreateCompletionRequest;
import cn.heposay.ai.domain.chatgpt.domain.res.CreateCompletionResponse;
import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author heposay
 * @description chatgpt 调用接口实现类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 16:44
 */
@Service
public class OpenApi implements IOpenApi {

    private Logger logger = LoggerFactory.getLogger(OpenApi.class);


    @Override
    public CreateCompletionResponse doChatGPT(CreateCompletionRequest request, String openAiKey) throws BusinessException {
        if (StringUtils.isBlank(openAiKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 openAiKey");
        }

        String url = "https://api.openai.com/v1/completions";
        String json = JSONUtil.toJsonStr(request);
        HttpResponse response = HttpRequest.post(url)
                .header("Authorization", "Bearer " + openAiKey)
                .body(json)
                .execute();
        if (response.isOk()) {
            String result = response.body();
            logger.info("doChatGPT调用结果：{}", result);
            return JSONUtil.toBean(result, CreateCompletionResponse.class);
        }else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "doChatGPT调用异常，异常码为:" + response.getStatus());
        }
    }
}
