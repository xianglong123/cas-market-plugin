package com.cas.annotation;



import com.cas.enums.MarketTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/02/06 10:18 上午
 * @desc 营销能力
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Market {
    /**
     * 是否启用，默认启用
     * @return
     */
     boolean enable() default true;

    /**
     * 营销类型, 必须主动设置，否则不参与活动
     */
    MarketTypeEnum type() default MarketTypeEnum.ERROR;


}
