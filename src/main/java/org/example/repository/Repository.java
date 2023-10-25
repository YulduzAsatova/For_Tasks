package org.example.repository;

import org.example.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface Repository<ENTITY extends BaseModel<ID>, ID> {
    int save(String entity);

    ENTITY findById(ID id);

    List<ENTITY> getAll();

    ENTITY update(ID id);

    Optional<ENTITY> getByName(String name);

    boolean existsByName(String name);

    void deleteById(ID id);
}
