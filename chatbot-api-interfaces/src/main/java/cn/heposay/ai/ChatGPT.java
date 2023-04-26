package cn.heposay.ai;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ChatGPT {
    public static void main(String[] args) throws IOException {
        System.out.println(generateText("帮我写一个冒泡排序"));
    }

    private static final String API_KEY = "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";
    private static final String API_URL = "https://api.openai.com/v1/engines/text-davinci-002/completions";

    public static String generateText(String prompt) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost(API_URL);
        post.addHeader("Authorization", "Bearer " + API_KEY);
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("prompt", prompt);
        jsonObject.put("max_tokens", 1024);
        jsonObject.put("temperature", 0.5);
        jsonObject.put("n", 1);
        jsonObject.put("stop", null);


        StringEntity stringEntity = new StringEntity(jsonObject.toJSONString(),
                ContentType.create("application/json", "UTF-8"));

        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            return res;
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
            return "程序调用错误";
        }

    }
}