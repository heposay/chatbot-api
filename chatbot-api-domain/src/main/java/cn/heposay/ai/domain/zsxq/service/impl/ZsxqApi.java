package cn.heposay.ai.domain.zsxq.service.impl;

import cn.heposay.ai.domain.zsxq.domain.aggregates.UnAnsweredQuestionsAggregates;
import cn.heposay.ai.domain.zsxq.domain.req.AnswerReq;
import cn.heposay.ai.domain.zsxq.domain.req.ReqData;
import cn.heposay.ai.domain.zsxq.domain.res.AnswerRes;
import cn.heposay.ai.domain.zsxq.service.IZsxqApi;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author heposay
 * @description 知识星球 API 接口实现类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:15
 */
@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(res, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Error Code is " + response.getStatusLine().getStatusCode());
        }

    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + groupId + "/answer");
        post.addHeader("cookie", cookie);
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSON.toJSONString(answerReq);
        StringEntity stringEntity = new StringEntity(paramJson,
                ContentType.create("interfaces/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果：groupId:{}, topicId:{}, jsonStr:{}", groupId, topicId, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Error Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
