//package cn.com.zenmaster.message;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.cloud.stream.messaging.Sink;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableBinding(Sink.class)
//@Slf4j
//public class KafkaConsumer {
//
//    @StreamListener(Sink.INPUT)
//    public void receive(Person person) {
//        log.info("person info:{}", JSON.toJSON(person));
//        System.out.println(person.toString());
//    }
//
//}
