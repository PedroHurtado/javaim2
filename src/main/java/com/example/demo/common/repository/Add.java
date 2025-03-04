package com.example.demo.common.repository;

import com.example.demo.common.BaseEntity;

public interface Add<T extends BaseEntity> extends Data<T> {
    default void add(T entity){
        getData().add(entity);
    }
    
}
