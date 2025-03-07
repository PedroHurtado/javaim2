package com.example.demo.features.ingredients.commands;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.repository.Add;
import com.example.demo.domain.Ingredient;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Configuration
public class CommandCreate {
    private record Request(
            String name,
            Double cost) {
    }

    private record Response(
            UUID id,
            String name,
            Double cost) {
    }

    @RestController
    @CrossOrigin(origins = "*", maxAge = 3600)
    public class Controller {

        private final Service service;

        public Controller(final Service service) {
            this.service = service;
        }

        @PostMapping("/api/v1/ingredients")
        public ResponseEntity<?> handler(@RequestBody Request request) {

            return ResponseEntity
                    .status(201)
                    .body(service.handler(request));
        }
    }

    public interface Service {
        Response handler(Request request);

    }

    @Component
    public class ServiceImpl implements Service {

        private final Add<Ingredient, UUID> repository;
        public ServiceImpl(final Add<Ingredient,UUID> repository){
            this.repository = repository;
        }
        @Override
        public Response handler(Request request) {

            var ingredient = Ingredient.create(
                request.name(), 
                request.cost());
            
            repository.add(ingredient);
                
            var response = new Response(
                    ingredient.getId(),
                    ingredient.getName(),
                    ingredient.getCost());
            return response;
        }
    }
}
