package com.example.demo.infraestructure.ingredients;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.demo.common.repository.Repository;
import com.example.demo.domain.Ingredient;

@Component
public class IngredientsRepository implements Repository<Ingredient,UUID> {

    private static final Set<Ingredient> data = new HashSet<>();
    @Override
    public Set<Ingredient> getData() {
        return data;
    }
    
}
