package com.avgkin.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Taco {
    private String name;
    private List<Ingredient> ingredients;
    public Taco(String name){
        this.name = name;
    }
}
