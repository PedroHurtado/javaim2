package com.example.demo.infraestructure.ingredients;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Ingredient;

public interface IngredientsRepositoryJpa extends JpaRepository<Ingredient,UUID>{
    
}
