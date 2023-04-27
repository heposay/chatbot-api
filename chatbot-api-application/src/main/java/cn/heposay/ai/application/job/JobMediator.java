package cn.heposay.ai.application.job;

import cn.heposay.ai.application.answer.Answerer;
import cn.heposay.ai.application.factory.SimpleAnswererFactory;
import cn.heposay.ai.application.factory.SimpleMonitorFactory;
import cn.heposay.ai.application.model.TaskListItem;
import cn.heposay.ai.application.monitor.Monitor;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * @author heposay
 * @description 任务中介（负责协调监控者和回答者，把参数传给他们）
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 20:44
 */
@Slf4j
public class JobMediator implements Runnable{

    private final TaskListItem taskListItem;

    public JobMediator(TaskListItem taskListItem) {
        this.taskListItem = taskListItem;
    }

    @Override
    public void run() {
        if (new Random().nextBoolean()) {
            log.info("{} 随机打烊中。。。。", taskListItem.getName());
            return;
        }

        GregorianCalendar calendar = new GregorianCalendar();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 22 || hour < 7) {
            log.info("{} 现在是打烊时间不工作，AI 下班了！", taskListItem.getName());
            return;
        }
        //根据配置选择 monitor 和 answerer
        Answerer answerer = SimpleAnswererFactory.createAnswerer(taskListItem.getAnswerer());
        Monitor monitor = SimpleMonitorFactory.createMonitor(taskListItem.getMonitor(), taskListItem);
        monitor.onMonitor(answerer);
    }
}
