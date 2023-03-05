package com.cas.service;


import com.cas.enums.MarketTypeEnum;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/2/10 2:09 下午
 * @desc
 */
public abstract class AbstractBehaviorParameter implements BehaviorParameterHandler {

    protected MarketTypeEnum type;

    @Override
    public MarketTypeEnum getType() {
        return type;
    }

}
