package com.example.demo.common.repository;


import com.example.demo.common.BaseEntity;
import com.example.demo.common.exceptions.NotFoundException;

public interface Get<T extends BaseEntity, ID> extends Data<T> {
    default T get(ID id){
        return getData().stream()
                .filter(e->e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException());
        
    }
    
}
