package com.cas.aspect;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;

import cn.hutool.core.util.StrUtil;
import com.cas.annotation.Market;
import com.cas.annotation.MarketProperty;
import com.cas.bo.MarketBaseBO;
import com.cas.client.MarketPublishClient;
import com.cas.config.MarketRabbitConfig;
import com.cas.enums.MarketPropertyEnum;
import com.cas.enums.MarketTypeEnum;
import com.cas.service.BehaviorParameterHandler;
import com.cas.utils.ThreadPoolUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 22:20 2023-02-06
 * @version: V1.0
 * @review: 创建消息对象，下发消息对象
 */
@Aspect
@Component
@Import({MarketPublishClient.class, MarketRabbitConfig.class, ThreadPoolUtil.class})
@ConditionalOnProperty(prefix = "market", name = "enable", havingValue = "true")
public class MarketAspect implements EnvironmentAware {

    private final BehaviorParameterHandler[] handlers;

    private Environment environment;

    public MarketAspect(ObjectProvider<BehaviorParameterHandler[]> handlers) {
        this.handlers = handlers.getIfAvailable();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Resource
    private MarketPublishClient marketPublishClient;

    @Pointcut("@annotation(com.cas.annotation.Market)")
    private void controllerAspect(){}

    @AfterReturning(value = "controllerAspect()&&@annotation(market)", argNames = "pj,market,response", returning = "response")
    public void afterReturning(JoinPoint pj, Market market, Object response) throws IllegalAccessException {
        if (!market.enable()) {
            // 手动设置关闭，则不做操作
            return;
        }
        // 1、获取行为对象
        Object[] obj = pj.getArgs();
        if (obj == null) {
            // 没有入参，不参加活动
            return;
        }
        MarketBaseBO bo = new MarketBaseBO();
        Object arg = obj[0];

        MarketTypeEnum type = market.type();
        bo.setBehaviorCode(type.getCode());
        // 2.2 注解属性转换
        parameterFill(arg, bo);
        processHandlers(response, bo, type, arg);

        // 3、对象信息传递
        marketPublishClient.pushMessage(bo);
    }

    private void processHandlers(Object response, MarketBaseBO bo, MarketTypeEnum type, Object arg) {
        // 3、内部接口赋值(云平台模块引用实现, 对内)
        if (ObjectUtil.isNull(handlers)) {
            for (BehaviorParameterHandler handler : handlers) {
                MarketTypeEnum dest = handler.getType();
                if (dest.getCode().equals(type.getCode())) {
                    bo.setBehaviorData(handler.onBehaviorDataBackFill(bo, response, arg));
                }
            }
        }
    }

    /**
     * 枚举属性填充 / 模块自定义属性填充
     * @param arg
     * @param bo
     * @throws IllegalAccessException
     */
    private void parameterFill(Object arg, MarketBaseBO bo) throws IllegalAccessException {
        List<Field> fieldList = new ArrayList<>();
        getFieldList(arg.getClass(), fieldList);
        for (Field field : fieldList) {
            MarketProperty mp = field.getAnnotation(MarketProperty.class);
            if (!Objects.isNull(mp)) {
                MarketPropertyEnum anEnum = mp.type();
                field.setAccessible(true);
                Object o = field.get(arg);
                ReflectUtil.setFieldValue(bo, anEnum.getVal(), o);
            }
        }

        // 应用名称回填
        bo.setApplicationName(environment.getProperty("spring.application.name"));
    }


    private void getFieldList(Class<?> clazz, List<Field> fieldList) {
        if (null == clazz) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        fieldList.addAll(Arrays.asList(fields));
        /** 处理父类字段**/
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.equals(Object.class)) {
            return;
        }
        getFieldList(superClass, fieldList);
    }

}
