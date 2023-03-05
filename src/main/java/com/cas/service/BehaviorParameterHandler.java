package com.cas.service;



import com.cas.bo.MarketBaseBO;
import com.cas.enums.MarketTypeEnum;

import java.util.Map;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/2/10 2:09 下午
 * @desc
 */
public interface BehaviorParameterHandler {

    /**
     * 参数回填
     * @param bo
     * @param outBody 返回对象的Body
     * @param inObj 接口入参对象
     */
    Map<String, String> onBehaviorDataBackFill(MarketBaseBO bo, Object outBody, Object inObj);

    /**
     * 返回实际类型
     * 例如：Object.class
     * @return
     */
    MarketTypeEnum getType();

}
