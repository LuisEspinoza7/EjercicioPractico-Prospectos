package com.example.promotorproject.service.impl;

import com.example.promotorproject.commons.GenericServiceImpl;
import com.example.promotorproject.dao.api.DocumentoDaoAPI;
import com.example.promotorproject.model.Documento;
import com.example.promotorproject.service.api.DocumentoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class DocumentoServiceImpl extends GenericServiceImpl<Documento,Long> implements DocumentoServiceAPI {
    @Autowired
    private DocumentoDaoAPI documentoDaoAPI;

    public CrudRepository<Documento, Long> getDao() {
        return documentoDaoAPI;
    }


}
