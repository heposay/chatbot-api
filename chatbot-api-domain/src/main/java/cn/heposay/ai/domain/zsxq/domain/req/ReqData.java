package cn.heposay.ai.domain.zsxq.domain.req;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author heposay
 * @description 请求对象
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
@Data
public class ReqData {

    private String text;
    private List<String> image_ids;
    private boolean silenced;
}