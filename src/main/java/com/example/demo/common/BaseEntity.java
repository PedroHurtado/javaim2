package com.example.demo.common;

import java.util.UUID;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseEntity {
    @Id
    private final UUID id;
    
    protected BaseEntity(final UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {        
        if (obj instanceof BaseEntity e) {
            return id.equals(e.id);   //true or false
        }
        return false;        
    }
    @Override
    public int hashCode() {        
        return id.hashCode();
    }
    
}
