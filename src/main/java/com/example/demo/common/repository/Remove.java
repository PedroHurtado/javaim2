package com.example.demo.common.repository;

import com.example.demo.common.BaseEntity;

public interface Remove <T extends BaseEntity, ID> extends Get<T,ID> {
    default void remove(T t){
        getData().delete(t);
    }
    
}
