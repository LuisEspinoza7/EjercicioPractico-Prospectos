package com.example.promotorproject.model;

import javax.persistence.*;

@Entity
public class Documento {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long idprospecto;
    @Column
    private Long iddocumento;
    @Column
    private String nombredoc;
    @Column
    private String documento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdprospecto() {
        return idprospecto;
    }

    public void setIdprospecto(Long idprospecto) {
        this.idprospecto = idprospecto;
    }

    public Long getIddocumento() {
        return iddocumento;
    }

    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    public String getNombredoc() {
        return nombredoc;
    }

    public void setNombredoc(String nombredoc) {
        this.nombredoc = nombredoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
