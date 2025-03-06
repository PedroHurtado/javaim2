package com.example.demo.common.repository;

import com.example.demo.common.BaseEntity;

public interface Repository<T extends BaseEntity, ID> 
        extends Query<T,ID>, Update<T,ID>, Remove<T,ID>, Add<T,ID> { 
}