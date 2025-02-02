package com.example.promotorproject.commons;

import com.example.promotorproject.model.Documento;

import java.io.Serializable;
import java.util.List;

public interface GenericServiceAPI<T, ID extends Serializable> {
    T save(T Entity);

    void delete(ID id);

    T get(ID id);

    List<T> getAll();

}
