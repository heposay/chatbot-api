package cn.heposay.ai.interfaces;

import cn.heposay.ai.common.config.ModelConstant;
import cn.heposay.ai.common.config.OpenAiConfig;
import cn.heposay.ai.common.config.ZsxqConfig;
import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.common.utils.SpringContextUtils;
import cn.heposay.ai.domain.chatgpt.domain.req.CreateCompletionRequest;
import cn.heposay.ai.domain.chatgpt.domain.res.CreateCompletionResponse;
import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import cn.heposay.ai.domain.zsxq.domain.req.AnswerRequest;
import cn.heposay.ai.domain.zsxq.domain.req.ListTopicsRequest;
import cn.heposay.ai.domain.zsxq.domain.req.ReqData;
import cn.heposay.ai.domain.zsxq.domain.res.AnswerResponse;
import cn.heposay.ai.domain.zsxq.domain.res.ListTopicsResponse;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;

/**
 * @author heposay
 * @description  API 接口测试类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootRunTest {
    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenApi openApi;

    private static final String OPENAI_API_KEY = "你的 OPENAI_API_KEY";

    private static final String COOKIE = "你的 COOKIE";

    @Test
    public void test_list_topics() {
        ListTopicsRequest request = new ListTopicsRequest();
        request.setGroupId("28885518425541");
        request.setScope("all");
        request.setCount(20);
        ListTopicsResponse response = zsxqApi.queryUnAnsweredQuestionsTopicId(request, COOKIE);
        Assertions.assertNotNull(response);
    }

    @Test
    public void test_answer() {
        AnswerRequest answerRequest = new AnswerRequest();
        answerRequest.setTopicId("问题id");
        ReqData reqData = new ReqData();
        reqData.setText("我的回答");
        reqData.setSilenced(true);
        reqData.setImage_ids(CollectionUtil.newArrayList());
        answerRequest.setReq_data(reqData);
        AnswerResponse response = zsxqApi.answer(answerRequest, COOKIE);
        Assertions.assertNotNull(response);
    }

    @Test
    public void test_chatgpt() throws BusinessException {
        CreateCompletionRequest request = new CreateCompletionRequest();
        request.setPrompt("帮我写一个堆排序算法，用Java代码实现");
        request.setModel(ModelConstant.TEXT_DAVINCI_003);
        request.setMax_tokens(1024);
        request.setTemperature(0);
        CreateCompletionResponse response = openApi.doChatGPT(request, OPENAI_API_KEY);
        Assertions.assertNotNull(response);
    }
}
