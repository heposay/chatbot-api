package cn.heposay.ai.domain.zsxq.domain.req;

import lombok.Data;

/**
 * @author heposay
 * @description 获取列表请求
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 15:03
 */
@Data
public class ListTopicsRequest {
    /**
     * 星球 id
     */
    private String groupId;

    /**
     * 主题范围
     */
    private String scope;

    /**
     * 单页数量
     */
    private Integer count;
}
