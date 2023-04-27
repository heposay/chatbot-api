package cn.heposay.ai.domain.zsxq.domain.res;

import cn.heposay.ai.domain.zsxq.domain.vo.Topics;
import lombok.Data;

import java.util.List;
/**
 * @author heposay
 * @description 响应对象
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/25 15:08
 */
@Data
public class RespData {
    private List<Topics> topics;
}