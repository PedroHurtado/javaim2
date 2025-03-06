package com.example.demo.common.repository;

import com.example.demo.common.BaseEntity;

public interface Update<T extends BaseEntity, ID> extends  Get<T,ID> {
    default void update(T t){
        getData().save(t);
    }   
}  
