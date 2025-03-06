package com.example.demo.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.common.BaseEntity;

public interface Query<T extends BaseEntity, ID> extends Data<T,ID>  {
    default JpaRepository<T,ID> query(){
        return getData();
    }
}
