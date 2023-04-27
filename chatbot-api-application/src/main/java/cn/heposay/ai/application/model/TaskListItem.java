package cn.heposay.ai.application.model;

import lombok.Data;

/**
 * @author heposay
 * @description 单个任务配置
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 17:39
 */
@Data
public class TaskListItem {
    /**
     * 任务名
     */
    private String name = "";

    /**
     * 任务执行周期
     */
    private String cron = "0/30 * * * * ?";

    /**
     * 回答者
     */
    private String answerer = "openai";

    /**
     * 监控者
     */
    private String monitor = "zsxq";
}
