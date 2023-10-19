package org.example.repository;

import org.example.model.BaseModel;

import java.util.List;

public interface Repository <ENTITY extends BaseModel<ID>,ID>{
    int save ();
    ENTITY findById (ID id);
    List<ENTITY> getAll();
    ENTITY update (ID id);
    void deleteById (ID id);
}
