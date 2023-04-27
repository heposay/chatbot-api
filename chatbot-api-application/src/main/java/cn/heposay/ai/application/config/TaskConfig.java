package cn.heposay.ai.application.config;

import cn.heposay.ai.application.job.JobMediator;
import cn.heposay.ai.application.model.TaskListItem;
import cn.hutool.core.util.BooleanUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;

/**
 * @author heposay
 * @description 任务配置
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 19:12
 */
@Configuration
@ConfigurationProperties(prefix = "chatbot-api.task")
@Data
@Slf4j
public class TaskConfig implements SchedulingConfigurer {

    /**
     * 并发配置
     */
    private ConcurrentConfig concurrentConfig = new ConcurrentConfig();

    /**
     * 任务列表
     */
    private List<TaskListItem> list;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (BooleanUtil.isTrue(concurrentConfig.getEnable())) {
            // 如果开启并发，默认并发度为任务数，即全并发，可通过配置更改
            int size = Optional.ofNullable(concurrentConfig.getSize()).orElse(list.size());
            taskRegistrar.setScheduler(Executors.newScheduledThreadPool(size));
        }
        log.info("--- 任务注册开始 ---");
        for (int i = 0; i < list.size(); i++) {
            TaskListItem taskListItem = list.get(0);
            if (StringUtils.isBlank(taskListItem.getName())) {
                taskListItem.setName("task-" + (i + 1));
            }
            taskRegistrar.addCronTask(new JobMediator(taskListItem), taskListItem.getCron());
            log.info("任务注册成功 {}", taskListItem);
        }
        log.info("--- 任务注册结束 ---");
    }

    @Data
    public static class ConcurrentConfig {
        private Boolean enable = false;
        private Integer size;
    }
}
