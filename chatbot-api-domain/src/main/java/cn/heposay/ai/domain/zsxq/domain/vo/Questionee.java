package cn.heposay.ai.domain.zsxq.domain.vo;

import lombok.Data;

/**
 * @author heposay
 * @description 知识星球提问者信息
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
@Data
public class Questionee {

    private String user_id;

    private String name;

    private String avatar_url;

    private String description;

    private String location;

}