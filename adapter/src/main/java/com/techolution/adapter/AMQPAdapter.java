package com.techolution.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class AMQPAdapter {

    private final RabbitTemplate rabbitTemplate;
    private final AMQPReceiver receiver;

    @KafkaListener(topics = "test", groupId = "ide")
    public void listen(String message) throws Exception{

        log.info("Kafka Received message={}",message);

        rabbitTemplate.convertAndSend(AMQPConfig.topicExchangeName, "foo.bar.baz", message);
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

    }
}
