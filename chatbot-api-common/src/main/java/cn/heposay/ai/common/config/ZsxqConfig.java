package cn.heposay.ai.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @author heposay
 * @description 知识星球配置
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 10:11
 */
@Configuration
@ConfigurationProperties(prefix = "chatbot-api.zsxq")
@Data
public class ZsxqConfig {

    /**
     * 登录 cookie
     */
    private String cookie;

    /**
     * 星球 id
     */
    private String groupId;

    /**
     * 星球名称
     */
    private String groupName;

    /**
     * 是否提醒提问者
     */
    private Boolean silenced = true;
}