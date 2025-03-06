package com.example.demo.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.common.BaseEntity;

public interface Data<T extends BaseEntity,ID>  {
    JpaRepository<T,ID> getData();
}
