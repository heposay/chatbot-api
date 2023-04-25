package cn.heposay.ai.application.job;

import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import cn.heposay.ai.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;
import cn.heposay.ai.domain.zsxq.domain.vo.Topics;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * @author heposay
 * @description 任务体
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 20:44
 */
public class ChatbotTask implements Runnable{

    private Logger logger = LoggerFactory.getLogger(ChatbotTask.class);

    private String groupName;
    private String groupId;
    private String cookie;
    private String openAiKey;

    private IZsxqApi zsxqApi;
    private IOpenApi openApi;

    public ChatbotTask(String groupName, String groupId, String cookie, String openAiKey, IZsxqApi zsxqApi, IOpenApi openApi) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.cookie = cookie;
        this.openAiKey = openAiKey;
        this.zsxqApi = zsxqApi;
        this.openApi = openApi;
    }

    @Override
    public void run() {
        try {
            if (new Random().nextBoolean()) {
                logger.info("{} 随机打烊中。。。。", groupName);
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour > 22 || hour < 7) {
                logger.info("{} 现在是打烊时间不工作，AI 下班了！", groupName);
                return;
            }

            //1.检索问题
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if (null == topics || topics.isEmpty()) {
                logger.info("{} 本次未检索到待会答问题", groupName);
                return;
            }

            //2.AI 回答
            Topics topic = topics.get(0);
            String answer = openApi.doChatGPT(openAiKey, topic.getQuestion().getText());

            //3.回答问题
            boolean status = zsxqApi.answer(groupId, cookie, topic.getTopic_id(), answer, false);
            logger.info("{} 编号:{}, 问题:{}, 回答:{}, 状态:{}", groupName, topic.getTopic_id(), topic.getQuestion().getText(), answer, status);
        } catch (IOException e) {
            logger.error("{} 自动回答问题异常", groupName, e);
        }
    }
}
