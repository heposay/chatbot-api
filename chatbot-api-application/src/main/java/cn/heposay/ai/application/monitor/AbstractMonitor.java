package cn.heposay.ai.application.monitor;

import cn.heposay.ai.application.answer.Answerer;
import cn.heposay.ai.application.model.TaskListItem;

/**
 * @author heposay
 * @description 监控者抽象类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:01
 */
public abstract class AbstractMonitor implements Monitor {
    /**
     * 任务提
     */
    protected TaskListItem taskListItem;

    public AbstractMonitor(TaskListItem taskListItem) {
        this.taskListItem = taskListItem;
    }

    /**
     * 触发监控
     *
     * @param answerer 回答者
     */
    public abstract void onMonitor(Answerer answerer);
}
