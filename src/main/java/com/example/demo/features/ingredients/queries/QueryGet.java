package com.example.demo.features.ingredients.queries;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.repository.Get;
import com.example.demo.domain.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;


@Configuration
public class QueryGet {
    
    public record Response(
        UUID id,
        String name,
        Double cost
    ) {
    }

    @RestController
    public class Controller{
        private final Service service;
        public Controller(Service service){
            this.service = service;
        }
        @GetMapping("/api/v1/ingredients/{id}")     
        ResponseEntity<?> handler(@PathVariable UUID id){

            return ResponseEntity
                .status(200)
                .body(service.hanlder(id));
        }
    }

    public interface Service{
        Response hanlder(UUID id);
    }

    @Component
    public class ServiceImpl implements Service{

        private final Get<Ingredient,UUID> repository;
        public ServiceImpl(final Get<Ingredient,UUID> repository){
            this.repository = repository;
        }
        @Override
        public Response hanlder(UUID id) {
            var ingredient = repository.get(id);

            return new Response(
                ingredient.getId(), 
                ingredient.getName(), 
                ingredient.getCost());
        }

    }
}
