package com.cas.annotation;


import com.cas.enums.MarketPropertyEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2023/2/10 6:42 下午
 * @desc 营销公共属性注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MarketProperty {

    MarketPropertyEnum type();

}
