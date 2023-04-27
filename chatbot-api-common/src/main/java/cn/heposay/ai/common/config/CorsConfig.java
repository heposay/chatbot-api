package cn.heposay.ai.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author heposay
 * @description 全局跨域配置
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 10:11
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //覆盖所有的请求
        registry.addMapping("/api/**")
                //不允许发送Cookie
                .allowCredentials(false).maxAge(3600)
                // 放行哪些域名（必须用 patterns，否则 * 会和 allowCredentials 冲突）
                .allowedOriginPatterns("*")
                //允许那些类型方法
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                //允许那些请求头
                .allowedHeaders("*")
                //暴露那些请求头
                .exposedHeaders("*");
    }
}
