package com.cas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @author xiang_long
 * @version 1.0
 * @date 2021/12/13 9:43 上午
 * @desc 涉及队列
 */
public class MarketRabbitConfig {

    /**
     * 营销消息推送
     */
    public static final String MARKET_MESSAGE_PUSH_QUEUE = "mirror.market.push";
    public static final String MARKET_MESSAGE_PUSH_EXCHANGE = "mirror.market.push-exchange";
    public static final String MARKET_MESSAGE_PUSH_ROUTING_KEY = "mirror.market.push-routingKey";

    @Bean("marketQueue")
    public Queue marketQueue(){
        return new Queue(MARKET_MESSAGE_PUSH_QUEUE);
    }

    @Bean("marketExchange")
    public DirectExchange marketExchange(){
        return new DirectExchange(MARKET_MESSAGE_PUSH_EXCHANGE);
    }

    @Bean
    public Binding marketBinding(@Qualifier("marketQueue") Queue queue,
                                 @Qualifier("marketExchange") DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(MARKET_MESSAGE_PUSH_ROUTING_KEY);
    }


}
