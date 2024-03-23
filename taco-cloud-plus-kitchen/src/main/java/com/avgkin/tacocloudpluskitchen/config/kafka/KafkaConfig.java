package com.avgkin.tacocloudpluskitchen.config.kafka;

import com.avgkin.entity.po.Order;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import static com.alibaba.druid.sql.ast.SQLPartitionValue.Operator.List;

@Configuration
public class KafkaConfig {
    @Autowired
    private OrderConsumerFactory orderConsumerFactory;
    @Bean(name = "manualImmediateListenerContainerFactory")
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Order>> manualImmediateListenerContainerFactory(
            ConsumerFactory<String, Order> orderConsumerFactory){
        ConcurrentKafkaListenerContainerFactory<String,Order> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory);
        factory.getContainerProperties().setPollTimeout(1500);
        factory.setBatchListener(true);
        //配置手动提交offset
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        return factory;
    }
    @Bean("mConsumer")
    public Consumer<String,Order> consumer(){
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"avgkin");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG,"consumer-two");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"false");
        properties.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        KafkaConsumer<String,Order> consumer = new KafkaConsumer<String, Order>(properties);
        consumer.subscribe(Collections.singleton(("order")));
        return consumer;
    }
}
