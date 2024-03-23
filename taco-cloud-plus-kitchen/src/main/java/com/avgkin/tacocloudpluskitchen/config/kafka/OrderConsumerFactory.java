package com.avgkin.tacocloudpluskitchen.config.kafka;

import com.avgkin.entity.po.Order;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class OrderConsumerFactory implements ConsumerFactory<String, Order> {
//    @Autowired
//    private KafkaProperties kafkaProperties;

    @Override
    public Consumer<String, Order> createConsumer(String s, String s1, String s2) {
        return createConsumer(s,s1,s2,null);
    }

    @Override
    public Consumer<String, Order> createConsumer(String groupId, String clientIdPrefix, String clientIdSuffix,Properties properties) {
        Properties pro = new Properties();
        pro.put("bootstrap.servers","127.0.0.1:9092");
        pro.put("key.deserializer", JsonDeserializer.class);
        pro.put("value.deserializer", JsonDeserializer.class);
        pro.put("group.id","avgkin");
        if (clientIdPrefix != null && clientIdSuffix != null) {
            pro.put("client.id", clientIdPrefix + clientIdSuffix);
        } else if (clientIdPrefix != null) {
            pro.put("client.id", clientIdPrefix);
        }
        if(properties!=null){
            pro.putAll(properties);
        }
        return new KafkaConsumer<>(pro);
    }

    @Override
    public boolean isAutoCommit() {
        return false;
    }
}
