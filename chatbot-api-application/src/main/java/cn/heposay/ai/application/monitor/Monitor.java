package cn.heposay.ai.application.monitor;

import cn.heposay.ai.application.answer.Answerer;

/**
 * @author heposay
 * @description 监控者
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 18:00
 */
public interface Monitor {
    /**
     * 触发监控
     *
     * @param answerer
     */
    void onMonitor(Answerer answerer);
}
