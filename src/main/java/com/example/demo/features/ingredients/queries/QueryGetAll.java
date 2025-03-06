package com.example.demo.features.ingredients.queries;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.repository.Query;
import com.example.demo.domain.Ingredient;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
public class QueryGetAll {
    public record  Request(
        String name,
        Integer page,
        Integer size
    ) {
    }
    public record  Response(
        UUID id,
        String name,
        Double cost
    ) {
    }

    @RestController
    public class Controller{
        private final Service service;
        public Controller(final Service service){
            this.service =service;
        }
    
        @GetMapping("/api/v1/ingredients")     
        ResponseEntity<?> handler(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "25") Integer size
        ){
            var request = new Request(name,page,size);
            return ResponseEntity.status(200).body(
                service.handler(request)            
            );
        }


    }


    public interface Service {
        
        List<Response> handler(Request request);
    }
    @Component
    public class ServiceImpl implements Service{
        public final Query<Ingredient> repository;
        public ServiceImpl(final Query<Ingredient> repository){
            this.repository = repository;
        }
        @Override
        public List<Response> handler(Request request) {            
             var result =  repository
                .getData().stream()                
                .filter(i->                    
                        (request.name()==null ||
                        i.getName().contains(request.name()))                    
                )  
                .skip(request.page() * request.size())              
                .limit(request.size())
                .map(i->new Response(i.getId(), i.getName(), i.getCost()))
                .toList();
            return result;
        }
    }
}
