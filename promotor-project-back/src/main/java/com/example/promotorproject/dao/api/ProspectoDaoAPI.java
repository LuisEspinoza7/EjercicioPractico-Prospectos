package com.example.promotorproject.dao.api;

import com.example.promotorproject.model.Prospecto;
import org.springframework.data.repository.CrudRepository;

public interface ProspectoDaoAPI extends CrudRepository<Prospecto,Long> {
}
