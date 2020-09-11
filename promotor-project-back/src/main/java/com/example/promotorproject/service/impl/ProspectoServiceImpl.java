package com.example.promotorproject.service.impl;

import com.example.promotorproject.commons.GenericServiceImpl;
import com.example.promotorproject.dao.api.ProspectoDaoAPI;
import com.example.promotorproject.model.Prospecto;
import com.example.promotorproject.service.api.ProspectoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ProspectoServiceImpl extends GenericServiceImpl<Prospecto,Long> implements ProspectoServiceAPI {
    @Autowired
    private ProspectoDaoAPI prospectoDaoAPI;

    public CrudRepository<Prospecto,Long> getDao(){
        return prospectoDaoAPI;
    }


}
