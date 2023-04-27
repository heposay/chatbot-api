package cn.heposay.ai.controller;

import cn.heposay.ai.common.response.BaseResponse;
import cn.heposay.ai.common.utils.ResultUtils;
import cn.heposay.ai.domain.chatgpt.domain.req.CreateCompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heposay
 * @description 开放接口
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 17:36
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @PostMapping("/chatgpt")
    public BaseResponse<String> chatGpt(@RequestBody CreateCompletionRequest request) {
        return ResultUtils.success("访问chatbot-api接口成功！");
    }
}
