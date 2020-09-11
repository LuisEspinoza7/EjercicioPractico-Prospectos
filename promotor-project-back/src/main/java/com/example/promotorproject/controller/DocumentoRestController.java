package com.example.promotorproject.controller;

import com.example.promotorproject.model.Documento;
import com.example.promotorproject.model.Prospecto;
import com.example.promotorproject.service.api.DocumentoServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping(value = "/api/documents/v1")
@CrossOrigin("*")
public class DocumentoRestController {
    @Autowired
    private DocumentoServiceAPI documentoServiceAPI;

    @GetMapping(value = "/all")
    public List<Documento> getAll(){
        return documentoServiceAPI.getAll();
    }
    @PostMapping(value ="/save")
    public ResponseEntity<Documento> save(@RequestBody Documento documento){
        Documento doc = documentoServiceAPI.save(documento);
        return new ResponseEntity<Documento>(doc, HttpStatus.OK);
    }







}
