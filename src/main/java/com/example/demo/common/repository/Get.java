package com.example.demo.common.repository;


import com.example.demo.common.BaseEntity;
import com.example.demo.common.exceptions.NotFoundException;

public interface Get<T extends BaseEntity, ID> extends Data<T, ID> {
    default T get(ID id){
        return getData()
                .findById(id)
                .orElseThrow(() -> new NotFoundException());
        
    }
    
}
