package cn.heposay.ai.common.utils;

import cn.heposay.ai.common.exception.BusinessException;
import cn.heposay.ai.common.response.ErrorCode;

/**
 * @author heposay
 * @description 抛异常工具类
 * @github <a href="http://github.com/heposay"> heposay的Github仓库 </a>
 * @time Created in 2023/4/27 15:17
 */
public class ThrowUtils {
    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param e         运行时异常
     */
    public static void throwIf(boolean condition, RuntimeException e) {
        if (condition) {
            throw e;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param errorCode 自定义异常码
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition 条件
     * @param errorCode 自定义异常码
     * @param message   错误信息
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
