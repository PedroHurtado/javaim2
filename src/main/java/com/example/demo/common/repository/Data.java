package com.example.demo.common.repository;


import java.util.Set;

import com.example.demo.common.BaseEntity;

public interface Data<T extends BaseEntity> {
    Set<T> getData();
}
