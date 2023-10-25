package org.example.repository2;

import org.example.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface Repository2<ENTITY extends BaseModel<ID>, ID> {

    int save(ENTITY entity);

    // optional bilan qaytishi kerak chunki entity topilmasligi mumkin
    Optional<ENTITY> findById(ID id);

    List<ENTITY> getAll();

    int update(ENTITY entity, ID id);

    // optional bilan qaytishi kerak chunki entity topilmasligi mumkin
    Optional<ENTITY> getByName(String name);

    boolean existsByName(String name);

   int deleteById(ID id);
}
