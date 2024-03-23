package com.avgkin.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@TableName("order")
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;
    private Long userId;
    private List<Taco> tacos;

    public Order(Long userId, List<Taco> tacos) {
        this.userId = userId;
        this.tacos = tacos;
    }
}
