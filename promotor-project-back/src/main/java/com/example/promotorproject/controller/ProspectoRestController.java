package com.example.promotorproject.controller;

import com.example.promotorproject.model.Prospecto;
import com.example.promotorproject.service.api.ProspectoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/prospects/v1")
@CrossOrigin("*")
public class ProspectoRestController {
    @Autowired
    private ProspectoServiceAPI prospectoServiceAPI;


    @GetMapping(value = "/all")
    public List<Prospecto> getAll(){
        return prospectoServiceAPI.getAll();
    }

    @GetMapping(value = "/find/{id}")
    public Prospecto find(@PathVariable Long id){
        return prospectoServiceAPI.get(id);
    }
    @PostMapping(value ="/save")
    public ResponseEntity<Prospecto> save(@RequestBody Prospecto prospecto){
        Prospecto prosp = prospectoServiceAPI.save(prospecto);
        return new ResponseEntity<Prospecto>(prosp, HttpStatus.OK);
    }


}
