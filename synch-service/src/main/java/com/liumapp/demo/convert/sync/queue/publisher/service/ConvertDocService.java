package com.liumapp.demo.convert.sync.queue.publisher.service;

import com.liumapp.demo.convert.sync.queue.pattern.ConvertDocPattern;
import com.liumapp.demo.convert.sync.queue.pattern.TestPattern;
import com.liumapp.demo.convert.sync.queue.publisher.BasicPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

@Service
public class ConvertDocService extends BasicPublisher {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void send (String msg) {
        logger.info("convert doc service ready to send msg : " + msg);
        this.sendMessage("converterConsumer", "process", Integer.toString(10101), msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("convert doc service get confirmed info : " + correlationData);
        if (ack) {
            logger.info("send msg success");
        } else {
            logger.error("send msg failed and the reason is : " + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        logger.info("convert doc failed");
    }
}
