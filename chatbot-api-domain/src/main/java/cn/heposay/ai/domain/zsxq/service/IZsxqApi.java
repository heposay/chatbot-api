package cn.heposay.ai.domain.zsxq.service;

import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.domain.zsxq.domain.req.AnswerRequest;
import cn.heposay.ai.domain.zsxq.domain.req.ListTopicsRequest;
import cn.heposay.ai.domain.zsxq.domain.res.AnswerResponse;
import cn.heposay.ai.domain.zsxq.domain.res.ListTopicsResponse;

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
     * @param request 获取列表请求
     * @param cookie  用户Cookie
     * @return 未回答的 TopicID 列表
     * @throws IOException 如果请求过程出现异常，打印日志
     */
    ListTopicsResponse queryUnAnsweredQuestionsTopicId(ListTopicsRequest request, String cookie) throws BusinessException;

    /**
     * 回答问题接口
     *
     * @param request 回答问题请求
     * @param cookie  用户Cookie
     * @return 是否回答成功， true：回答成功  false：回答失败
     * @throws IOException 如果请求过程出现异常，打印日志
     */
    AnswerResponse answer(AnswerRequest request,String cookie) throws BusinessException;
}
