package com.cas.client;

import com.cas.bo.MarketBaseBO;
import com.cas.utils.ByteArrayObjectUtil;
import com.cas.utils.ThreadPoolUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import static com.cas.config.MarketRabbitConfig.MARKET_MESSAGE_PUSH_EXCHANGE;
import static com.cas.config.MarketRabbitConfig.MARKET_MESSAGE_PUSH_ROUTING_KEY;


public class MarketPublishClient {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ThreadPoolUtil threadPoolUtil;

    public void pushMessage(MarketBaseBO bo) {
        // UAT投递太慢了，影响请求返回，改用线程池投递
        threadPoolUtil.execute(() -> rabbitTemplate.convertSendAndReceive(MARKET_MESSAGE_PUSH_EXCHANGE, MARKET_MESSAGE_PUSH_ROUTING_KEY, ByteArrayObjectUtil.serialize(bo)));
    }


}