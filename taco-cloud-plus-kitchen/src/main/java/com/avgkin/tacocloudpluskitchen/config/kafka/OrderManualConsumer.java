package com.avgkin.tacocloudpluskitchen.config.kafka;

import com.avgkin.entity.po.Order;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class OrderManualConsumer {
    @Autowired
    private Consumer<String ,Order> consumer;
    public Order doConsumerOneOrder(){
        Order order = new Order();
        try{
            consumer.subscribe(List.of("order"));
            while(true){
                ConsumerRecords<String,Order> records = consumer.poll(Duration.ofMillis(1000));
                if(records.isEmpty()){
                    System.out.println("no Orders");
                }
                for(ConsumerRecord<String,Order> record:records){
                    System.out.println("processing:"+record.value());
                    order = record.value();
                    consumer.commitSync();
                    break;
                }
                break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }
}
