package cn.heposay.ai.domain.chatgpt.service.impl;

import cn.heposay.ai.domain.chatgpt.domain.aggregates.ChatGptAnswerAggregates;
import cn.heposay.ai.domain.chatgpt.domain.vo.Choices;
import cn.heposay.ai.domain.chatgpt.service.IOpenApi;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author heposay
 * @description chatgpt 调用接口实现类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 16:44
 */
@Service
public class OpenApi implements IOpenApi {


    @Override
    public String doChatGPT(String openAiKey, String question) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Authorization", "Bearer " + openAiKey);
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"" + question + "\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson,
                ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            ChatGptAnswerAggregates chatGptAnswerAggregates = JSON.parseObject(res, ChatGptAnswerAggregates.class);
            StringBuilder answers = new StringBuilder();
            List<Choices> choices = chatGptAnswerAggregates.getChoices();
            for (Choices choice : choices) {
                answers.append(choice.getText());
            }
            return answers.toString();
        } else {
            throw new RuntimeException("ai.openapi.com Error Code is " + response.getStatusLine().getStatusCode());
        }

    }
}
