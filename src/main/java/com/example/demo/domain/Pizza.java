package com.example.demo.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.demo.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Pizza extends BaseEntity {
    private static final Double PROFIT = 1.20D;    
    private String name;
    private String description;
    private String url;
    @ManyToMany
    private  Set<Ingredient> ingredients = new HashSet<>();
    protected Pizza(){
        super(UUID.randomUUID());
    }
    protected Pizza(
            final UUID id,
            String name,
            String description,
            String url,
            Set<Ingredient> ingredients) {
        super(id);
        this.name = name;
        this.description = description;
        this.url = url;
        this.ingredients = ingredients;
    }
    
    public String getDescription() {
        return description;
    }
    public Set<Ingredient> getIngredients() {
        return new HashSet<>(ingredients);
    }
    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }  
    public double getPrice() {

        /*double Price =0.0;
        for (Ingredient ingredient : ingredients) {
            Price += ingredient.getCost();
        }
        return Price * PROFIT;*/

        /*return ingredients.stream()
                .map(Ingredient::getCost)   
                .reduce(0.0, Double::sum) * PROFIT; */

        /*return ingredients.stream()
                .map(i->i.getCost())   
                .reduce(0.0, (a,v)->a+v) * PROFIT; */

        return ingredients.stream()
                .mapToDouble(Ingredient::getCost)   //map                             
                .sum() * PROFIT;
    }
    void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);       
    }
    void removeIngredient(Ingredient ingredient) {
        ingredients.remove(ingredient);
    }
    void Update(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;      
    }
    public static Pizza create(
            String name,
            String description,
            String url,
            Set<Ingredient> ingredients) {
        return new Pizza(UUID.randomUUID(), name, description, url, ingredients);
    }

}
