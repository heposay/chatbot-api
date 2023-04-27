package cn.heposay.ai.application.monitor;

import cn.heposay.ai.application.answer.Answerer;
import cn.heposay.ai.application.model.TaskListItem;
import lombok.extern.slf4j.Slf4j;

/**
 * @author heposay
 * @description 默认监控者
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:03
 */
@Slf4j
public class DefaultMonitor extends AbstractMonitor {

    public DefaultMonitor(TaskListItem taskListItem) {
        super(taskListItem);
    }

    @Override
    public void onMonitor(Answerer answerer) {
        String mockMessage = "我是一个新的消息";
        log.info(answerer.doAnswer(mockMessage));
    }
}
