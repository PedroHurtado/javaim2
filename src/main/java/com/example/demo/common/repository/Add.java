package com.example.demo.common.repository;

import com.example.demo.common.BaseEntity;

public interface Add<T extends BaseEntity,ID> extends Data<T,ID> {
    default void add(T entity){
        getData().save(entity);
    }
    
}
