package cn.heposay.ai.application.monitor;

import cn.heposay.ai.application.answer.Answerer;
import cn.heposay.ai.application.model.TaskListItem;
import cn.heposay.ai.common.config.ZsxqConfig;
import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.common.response.ErrorCode;
import cn.heposay.ai.common.utils.SpringContextUtils;
import cn.heposay.ai.domain.chatgpt.service.impl.OpenApi;
import cn.heposay.ai.domain.zsxq.domain.req.AnswerRequest;
import cn.heposay.ai.domain.zsxq.domain.req.ListTopicsRequest;
import cn.heposay.ai.domain.zsxq.domain.req.ReqData;
import cn.heposay.ai.domain.zsxq.domain.res.AnswerResponse;
import cn.heposay.ai.domain.zsxq.domain.res.ListTopicsResponse;
import cn.heposay.ai.domain.zsxq.domain.vo.Topics;
import cn.heposay.ai.domain.zsxq.service.impl.ZsxqApi;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heposay
 * @description 知识星球监控者
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:05
 */
@Slf4j
public class ZsxqMonitor extends AbstractMonitor {

    private final ZsxqApi zsxqApi = SpringContextUtils.getBean(ZsxqApi.class);

    private final ZsxqConfig zsxqConfig = SpringContextUtils.getBean(ZsxqConfig.class);

    public ZsxqMonitor(TaskListItem taskListItem) {
        super(taskListItem);
    }

    @Override
    public void onMonitor(Answerer answerer) {
        String taskName = taskListItem.getName();
        log.info("任务 {} 监控开始", taskName);
        String cookie = zsxqConfig.getCookie();
        // 1. 获取未回答的问题列表
        ListTopicsRequest listTopicsRequest = new ListTopicsRequest();
        listTopicsRequest.setGroupId(zsxqConfig.getGroupId());
        listTopicsRequest.setScope("unanswered_questions");
        listTopicsRequest.setCount(20);
        ListTopicsResponse listTopicsResponse = zsxqApi.queryUnAnsweredQuestionsTopicId(listTopicsRequest, cookie);
        List<Topics> topics = listTopicsResponse.getRespData().getTopics();
        if (CollectionUtil.isEmpty(topics)) {
            log.info("暂无新的提问！");
            return;
        }
        for (Topics topic : topics) {
            String question = topic.getQuestion().getText();
            log.info("{} 收到新提问 \n 问题：{}", taskName, question);
            //2.获取回答
            String answer = answerer.doAnswer(question);
            //3.回复
            AnswerRequest answerRequest = new AnswerRequest();
            answerRequest.setTopicId(topic.getTopic_id());
            ReqData reqData = new ReqData();
            reqData.setText(answer);
            reqData.setSilenced(zsxqConfig.getSilenced());
            reqData.setImage_ids(new ArrayList<>());
            answerRequest.setReq_data(reqData);
            AnswerResponse answerResponse = zsxqApi.answer(answerRequest, cookie);
            if (answerResponse.isSucceeded()) {
                log.info("{} 回答成功 \n 问题：{} \n 回答：{}", taskName, question, answer);
            } else {
                log.error("{} 回答失败 \n 问题：{}", taskName, question);
            }
            // 随机缓冲一段时间
            try {
                Thread.sleep(1000 + RandomUtil.randomInt(0, 2000));
            } catch (InterruptedException e) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, e.getMessage());
            }
        }
    }
}
