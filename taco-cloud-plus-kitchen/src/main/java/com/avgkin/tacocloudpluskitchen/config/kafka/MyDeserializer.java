package com.avgkin.tacocloudpluskitchen.config.kafka;

import com.avgkin.entity.po.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;

@Component
public class MyDeserializer implements Deserializer<Order> {
    private final ObjectMapper objectMapper = new JsonMapper();
    private ObjectReader reader;
    @Override
    public Order deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        } else {
            ObjectReader localReader = this.reader;
            localReader = objectMapper.readerFor(Order.class);
            Assert.state(localReader != null, "No headers available and no default type provided");
            try {
                return localReader.readValue(data);
            } catch (IOException var5) {
                throw new SerializationException("Can't deserialize data [" + Arrays.toString(data) + "] from topic [" + topic + "]", var5);
            }
        }
    }
}
