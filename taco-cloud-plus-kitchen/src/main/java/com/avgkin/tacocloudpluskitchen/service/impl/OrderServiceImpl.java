package com.avgkin.tacocloudpluskitchen.service.impl;

import com.avgkin.entity.po.Order;
import com.avgkin.tacocloudpluskitchen.config.kafka.OrderManualConsumer;
import com.avgkin.tacocloudpluskitchen.dao.mapper.OrderMapper;
import com.avgkin.tacocloudpluskitchen.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private Consumer<String,Order> consumer;
    @Override
    public boolean doCookTacos() {
        // 手动订阅主题
        consumer.subscribe(Collections.singletonList("order"));
        Order order = null;
        try {
            // 手动拉取数据
            ConsumerRecords<String, Order> records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                for (ConsumerRecord<String, Order> record : records) {
                    System.out.println("Processing: " + record.value());
                    order = record.value();
                    System.out.println(record.offset());
                    // 处理完一条消息后手动提交该消息的偏移量
                    Map<TopicPartition, OffsetAndMetadata> commitInfo = Collections.singletonMap(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1)
                    );
                    // 手动提交偏移量
                    consumer.commitSync(commitInfo);
                    break; // 仅处理一条消息
                }
            } else {
                System.out.println("No orders found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.unsubscribe();
        }
        return true;
    }

    @Override
    public boolean testCook(Order order) {
        System.out.println(order);
        return true;
    }

    @Override
    public Order doCreateOrder(Order order) {
        if(order!=null){
            save(order);
            return order;
        }
        else{
            return null;
        }
    }

}
