package cn.com.zenmaster.message;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
@Slf4j
public class KafkaProducer {

    @Autowired
    @Qualifier(Source.OUTPUT)
    private MessageChannel messageChannel;

    public void sendMessage(Person person) {
        log.info("发送消息:{}", JSON.toJSON(person));
        messageChannel.send(MessageBuilder.withPayload(person).build());
    }

}
