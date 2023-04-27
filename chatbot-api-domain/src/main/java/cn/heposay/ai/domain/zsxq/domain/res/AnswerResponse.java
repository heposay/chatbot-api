package cn.heposay.ai.domain.zsxq.domain.res;

import lombok.Data;

/**
 * @author heposay
 * @description 请求回答接口结果
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
@Data
public class AnswerResponse {

    private boolean succeeded;

    private RespData respData;

}