package cn.heposay.ai.interfaces;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author heposay
 * @description 知识星球回答问题、获取问题的单元测试
 * @github http://github.com/heposay/
 * @time Created in 2023-04-25 13:34:20
 */
public class AppTest {

    private static final String OPEN_AI_KEY = "xxxx";
    private static final String COOKIE = "UM_distinctid=186a1b7b06611c-03507ddfe121e9-1d525635-1aeaa0-186a1b7b067740; zsxq_access_token=037C20AA-B1A1-CA8D-3BCD-230A2A9B50A9_25AFFA98B3660AC6; sensorsdata2015jssdkcross={\\\"distinct_id\\\":\\\"15158212415442\\\",\\\"first_id\\\":\\\"18691e9387a3cc-052d94d2fb0371-1d525635-1764000-18691e9387bdf6\\\",\\\"props\\\":{\\\"$latest_traffic_source_type\\\":\\\"引荐流量\\\",\\\"$latest_search_keyword\\\":\\\"未取到值\\\",\\\"$latest_referrer\\\":\\\"https://bugstack.cn/\\\"},\\\"identities\\\":\\\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2OTFlOTM4N2EzY2MtMDUyZDk0ZDJmYjAzNzEtMWQ1MjU2MzUtMTc2NDAwMC0xODY5MWU5Mzg3YmRmNiIsIiRpZGVudGl0eV9sb2dpbl9pZCI6IjE1MTU4MjEyNDE1NDQyIn0=\\\",\\\"history_login_id\\\":{\\\"name\\\":\\\"$identity_login_id\\\",\\\"value\\\":\\\"15158212415442\\\"},\\\"$device_id\\\":\\\"18691e9387a3cc-052d94d2fb0371-1d525635-1764000-18691e9387bdf6\\\"}; zsxqsessionid=8e56eb544ab2592f20760dcd5b29ecca; abtest_env=product";


    @Test
    public void query_zsxq_answers_test() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie", COOKIE);
        get.addHeader("Content-Type", "application/json;charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer_question_test() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/412884248251548/answer");
        post.addHeader("cookie", COOKIE);
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson,
                ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_openapi() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Authorization", "Bearer "+ OPEN_AI_KEY);
        post.addHeader("Content-Type", "application/json;charset=UTF-8");

        String paramJson = "{\n" +
                "        \"model\": \"text-davinci-003\",\n" +
                "                \"prompt\": \"帮我写一个冒泡排序算法\",\n" +
                "                \"max_tokens\": 1024,\n" +
                "                \"temperature\": 0\n" +
                "    }";

        StringEntity stringEntity = new StringEntity(paramJson,
                ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

}