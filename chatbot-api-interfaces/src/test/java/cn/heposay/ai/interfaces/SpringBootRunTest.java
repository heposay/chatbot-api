package cn.heposay.ai.interfaces;

import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import cn.heposay.ai.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;
import cn.heposay.ai.domain.zsxq.domain.vo.Topics;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author heposay
 * @description 知识星球 API 接口测试类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenApi openApi;

    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果:{}", JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String topicId = topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("主题的topicId:{}, 回答内容:{}", topicId, text);

            //回答问题, 这个text后期就是Chatgpt回复的内容
            zsxqApi.answer(groupId, cookie, topicId, text, false);
        }
    }

    @Test
    public void test_chatgpt() throws IOException {
        String jsonStr = openApi.doChatGPT("帮我写一个冒泡排序");
        logger.info("测试结果：{}", jsonStr);

    }
}
