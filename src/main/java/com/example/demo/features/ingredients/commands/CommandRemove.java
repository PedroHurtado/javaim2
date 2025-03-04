package com.example.demo.features.ingredients.commands;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.repository.Remove;
import com.example.demo.domain.Ingredient;

@Configurable
public class CommandRemove {

    @RestController
    public class Controller{
        private final Service service;
        public Controller(final Service service){
            this.service = service;
        }
        @DeleteMapping("api/v1/ingredients/{id}")
        ResponseEntity<?> handler(@PathVariable UUID id){
            service.handler(id);
            return ResponseEntity.status(204).body(null);
        }
    }
    public interface  Service {
        void handler(UUID id);        
    }
    @Component
    public class ServiceImpl implements Service{

        private final Remove<Ingredient,UUID> repository;
        public ServiceImpl(final Remove<Ingredient,UUID> repository){
            this.repository = repository;
        }
        @Override
        public void handler(UUID id) {            
            var ingredient = repository.get(id);
            repository.remove(ingredient);
        }

    }
}
