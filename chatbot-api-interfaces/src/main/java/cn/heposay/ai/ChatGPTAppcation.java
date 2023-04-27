package cn.heposay.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author heposay
 * @description 主启动类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:46
 */
@EnableScheduling
@SpringBootApplication
public class ChatGPTAppcation {
    public static void main(String[] args) {
        SpringApplication.run(ChatGPTAppcation.class, args);
    }
}
