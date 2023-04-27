package cn.heposay.ai.application.factory;

import cn.heposay.ai.application.model.TaskListItem;
import cn.heposay.ai.application.monitor.DefaultMonitor;
import cn.heposay.ai.application.monitor.Monitor;
import cn.heposay.ai.application.monitor.ZsxqMonitor;

/**
 * @author heposay
 * @description 监视者简单工厂
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:30
 */
public class SimpleMonitorFactory {
    /**
     * 创建监视者
     *
     * @param monitor      监视者类型
     * @param taskListItem 任务体
     * @return
     */
    public static Monitor createMonitor(String monitor, TaskListItem taskListItem) {
        switch (monitor) {
            case "zsxq":
                return new ZsxqMonitor(taskListItem);
            default:
                return new DefaultMonitor(taskListItem);
        }
    }
}
