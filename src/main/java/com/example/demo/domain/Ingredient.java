package com.example.demo.domain;

import java.util.UUID;

import com.example.demo.common.BaseEntity;

import jakarta.persistence.Entity;

@Entity
public class Ingredient extends BaseEntity{

    private String name;
    private Double cost;
    protected Ingredient(){
        super(UUID.randomUUID());
    }
    protected Ingredient(final UUID id ,String name, Double cost){ 
        super(id);
        this.name = name;
        this.cost = cost;
        
    }
    public void update(String name, Double cost){
        this.name = name;
        this.cost = cost;
    }
    public String getName() {
        return name;
    }
    public Double getCost(){
        return cost;
    }
    public static Ingredient create(String name, Double cost){
        return new Ingredient(UUID.randomUUID(), name, cost);
    }
    
    
}
