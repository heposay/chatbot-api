package cn.heposay.ai.application.job;

import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import cn.heposay.ai.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;
import cn.heposay.ai.domain.zsxq.domain.vo.Topics;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author heposay
 * @description 定时轮询问题并利用Chatgpt回答问题
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 17:10
 */
@EnableScheduling
@Configuration
public class ChatGPTSchedule {
    private final Logger logger = LoggerFactory.getLogger(ChatGPTSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;

    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenApi openApi;


    @Scheduled(cron = "0/5 * * * * ?")
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("随机打烊中。。。。");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("现在是打烊时间不工作，AI 下班了！");
                return;
            }

            //1.检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("本次未检索到待会答问题");
                return;
            }

            //2.AI 回答
            Topics topic = topics.get(0);
            String answer = openApi.doChatGPT(topic.getQuestion().getText());

            //3.回答问题
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("编号:{}, 问题:{}, 回答:{}, 状态:{}", topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (IOException e) {
           logger.error("自动回答问题异常", e);
        }
    }
}
