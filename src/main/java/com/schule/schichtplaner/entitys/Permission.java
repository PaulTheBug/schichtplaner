package com.schule.schichtplaner.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Permission {

    @Id
    private String id;
    private String name;


    public Permission(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
