package cn.heposay.ai.application.ext;

import cn.heposay.ai.application.job.ChatbotTask;
import cn.heposay.ai.common.PropertyUtil;
import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author heposay
 * @description 任务注册服务，支持多组任务配置
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 20:23
 */
@Configuration
@EnableScheduling
public class TaskRegistrarAutoConfig implements EnvironmentAware, SchedulingConfigurer {

    private final Logger logger = LoggerFactory.getLogger(TaskRegistrarAutoConfig.class);

    private Map<String, Map<String, Object>> taskGroupMap = new HashMap<>();

    @Resource
    private IZsxqApi zsxqApi;

    @Resource
    private IOpenApi openApi;

    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "chatbot-api.";
        String launchListStr = environment.getProperty(prefix + "launchList");
        if (StringUtils.isEmpty(launchListStr)) return;
        for (String groupKey : launchListStr.split(",")) {
            Map<String, Object> taskGroupProps = PropertyUtil.handle(environment, prefix + groupKey, Map.class);
            taskGroupMap.put(groupKey, taskGroupProps);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Set<String> taskGroupKeys = taskGroupMap.keySet();
        for (String groupKey : taskGroupKeys) {
            Map<String, Object> taskGroup = taskGroupMap.get(groupKey);
            String groupName = taskGroup.get("groupName").toString();
            String groupId = taskGroup.get("groupId").toString();
            String cookie = taskGroup.get("cookie").toString();
            String openAiKey = taskGroup.get("openAiKey").toString();
            String cronExpression = taskGroup.get("cronExpression").toString();
            //String cronExpression = new String(Base64.getDecoder().decode(cronExpressionBase64), StandardCharsets.UTF_8);
            logger.info("创建任务 groupName：{} groupId：{} cronExpression：{}", groupName, groupId, cronExpression);
            // 添加任务
            taskRegistrar.addCronTask(new ChatbotTask(groupName, groupId, cookie, openAiKey, zsxqApi, openApi), cronExpression);
        }
    }
}
