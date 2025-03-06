package com.example.demo.infraestructure.ingredients;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.common.repository.Repository;
import com.example.demo.domain.Ingredient;

@org.springframework.stereotype.Repository
public class IngredientesRepository implements Repository<Ingredient,UUID> {

    private final IngredientsRepositoryJpa repository;
    public IngredientesRepository(final IngredientsRepositoryJpa repository){
        this.repository = repository;        
    }

    @Override
    public JpaRepository<Ingredient, UUID> getData() {
        return repository;
    }
    
}
