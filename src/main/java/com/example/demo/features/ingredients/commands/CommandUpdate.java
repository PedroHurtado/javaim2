package com.example.demo.features.ingredients.commands;

import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Ingredient;
import com.example.demo.common.repository.Update;

@Configuration
public class CommandUpdate {
    private record Request(String name,Double cost){

    }
    @RestController
    public class Controller{
        private final Service service;
        public Controller(final Service service){
            this.service = service;
        }
        @PutMapping("api/v1/ingredients/{id}")        
        ResponseEntity<?> handler(@PathVariable UUID id,@RequestBody Request request){
            service.handler(id,request);
            return ResponseEntity.status(204).body(
                null
            );
        }
    }

    public interface  Service {
        void handler(UUID id,   Request request);
    }
    @Component
    public class ServiceImpl implements Service{

        private final Update<Ingredient,UUID> repository;
        public ServiceImpl(final Update<Ingredient,UUID> repository){
            this.repository =repository;
        }
        @Override
        public void handler(UUID id,Request request) {
            var ingredient = repository.get(id);
            ingredient.update(request.name(), request.cost());
            repository.update(ingredient);
        }

    }
}
