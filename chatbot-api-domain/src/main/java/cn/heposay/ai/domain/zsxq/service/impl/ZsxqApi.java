package cn.heposay.ai.domain.zsxq.service.impl;

import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.common.response.ErrorCode;
import cn.heposay.ai.domain.zsxq.domain.req.AnswerRequest;
import cn.heposay.ai.domain.zsxq.domain.req.ListTopicsRequest;
import cn.heposay.ai.domain.zsxq.domain.res.AnswerResponse;
import cn.heposay.ai.domain.zsxq.domain.res.ListTopicsResponse;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author heposay
 * @description 知识星球 API 接口实现类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:15
 */
@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/111.0";

    @Override
    public ListTopicsResponse queryUnAnsweredQuestionsTopicId(ListTopicsRequest request, String cookie) throws BusinessException {
        //入参校验
        String groupId = request.getGroupId();
        if (StringUtils.isEmpty(groupId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 groupId");
        }
        if (StringUtils.isEmpty(cookie)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 cookie");
        }
        //拼接请求参数
        String url = String.format("https://api.zsxq.com/v2/groups/%s/topics", groupId);
        Map<String, Object> paramMap = BeanUtil.beanToMap(request);
        String query = URLUtil.buildQuery(paramMap, StandardCharsets.UTF_8);
        //请求接口
        HttpResponse response = HttpRequest.get(url)
                .header("cookie", cookie)
                .header("user-agent", USER_AGENT)
                .body(query)
                .execute();
        if (response.isOk()) {
            String result = response.body();
            logger.info("queryUnAnsweredQuestionsTopicId请求结果:{}", result);
            return JSONUtil.toBean(result, ListTopicsResponse.class);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "queryUnAnsweredQuestionsTopicId请求失败,错误码是:" + response.getStatus());
        }

    }

    @Override
    public AnswerResponse answer(AnswerRequest request, String cookie) throws BusinessException {
        //入参校验
        String topicId = request.getTopicId();
        if (StringUtils.isEmpty(topicId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 topicId");
        }
        if (StringUtils.isEmpty(cookie)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "未传 cookie");
        }
        //拼接请求参数
        String url = String.format("https://api.zsxq.com/v2/topics/%s/answer", topicId);
        String paramJson = JSONUtil.toJsonStr(request);
        HttpResponse response = HttpRequest.post(url)
                .header("cookie", cookie)
                .header("Content-Type", "application/json;charset=UTF-8")
                .body(paramJson)
                .execute();
        if (response.isOk()) {
            String result = response.body();
            logger.info("answer请求结果:{}", result);
            return JSONUtil.toBean(result, AnswerResponse.class);
        } else {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "answer请求失败,错误码是:" + response.getStatus());
        }
    }
}
