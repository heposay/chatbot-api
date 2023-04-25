package cn.heposay.ai.domain.zsxq.service;

import cn.heposay.ai.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

/**
 * @author heposay
 * @description 知识星球的 API 接口
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
public interface IZsxqApi {
    /**
     * 查询知识星球未回答的问题集合接口
     *
     * @param groupId 组ID
     * @param cookie  用户Cookie
     * @return 未回答的 TopicID 列表
     * @throws IOException 如果请求过程出现异常，打印日志
     */
    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    /**
     * 回答问题接口
     *
     * @param groupId  组ID
     * @param cookie   用户Cookie
     * @param topicId  问题的ID
     * @param text     回答的文本
     * @param silenced 是否全体可见 true：全体可见  false:提问者可见
     * @return 是否回答成功， true：回答成功  false：回答失败
     * @throws IOException 如果请求过程出现异常，打印日志
     */
    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
