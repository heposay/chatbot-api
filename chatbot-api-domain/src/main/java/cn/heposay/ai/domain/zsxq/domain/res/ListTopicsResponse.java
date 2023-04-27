package cn.heposay.ai.domain.zsxq.domain.res;

import lombok.Data;

/**
 * @author heposay
 * @description 获取响应回答问题列表
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 15:02
 */
@Data
public class ListTopicsResponse {

    private boolean succeeded;

    private RespData respData;
}
