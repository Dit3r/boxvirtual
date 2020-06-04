package com.boxvirtual.box.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sun.xml.internal.ws.developer.SchemaValidation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "USUARIO")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(value = {"id","usuario","contrasena","email","tipoUsuario","telefono","rut","nombres","appaterno","apmaterno"})

public class usuario {

    @Id
    @ApiModelProperty(notes = "id de la tabla")
    @JsonProperty(value = "id",required = true)
    @Column(name = "ID", length = 12,columnDefinition = "id de la tabla")
    private Long id;

    @ApiModelProperty(notes = "nombre usuario")
    @JsonProperty(value = "usuario",required = true)
    @Column(name = "USUARIO", length = 30,columnDefinition = "nombre de usuario")
    private String usuario;

    @ApiModelProperty(notes = "contrasena usuario ")
    @JsonProperty(value = "contrasena",required = true)
    @Column(name = "CONTRASENA", length = 20,columnDefinition = "contraseña usuario")
    private String contrasena;

    @ApiModelProperty(notes = "email usuario")
    @JsonProperty(value = "email",required = true)
    @Column(name = "EMAIL", length = 50,columnDefinition = "email usuario")
    private String email;

    @ApiModelProperty(notes = "tipo usuario")
    @JsonProperty(value = "tipoUsuario",required = true)
    @Column(name = "TIPOUSUARIO", length = 12,columnDefinition = "tipo usuario")
    private String tipoUsuario;

    @ApiModelProperty(notes = "telefono usuario ")
    @JsonProperty(value = "telefono",required = true)
    @Column(name = "TELEFONO", length = 40,columnDefinition = "telefono usuario")
    private String telefono;

    @ApiModelProperty(notes = "rut usuario ")
    @JsonProperty(value = "rut",required = true)
    @Column(name = "RUT", length = 20,columnDefinition = "rut usuario")
    private String rut;

    @ApiModelProperty(notes = "nombres usuario ")
    @JsonProperty(value = "nombres",required = true)
    @Column(name = "NOMBRES", length = 120,columnDefinition = "nombres usuario")
    private String nombres;

    @ApiModelProperty(notes = "apellido paterno usuario ")
    @JsonProperty(value = "appaterno",required = true)
    @Column(name = "APPATERNO", length = 60,columnDefinition = "apellido paterno usuario")
    private String appaterno;

    @ApiModelProperty(notes = "apellido materno usuario ")
    @JsonProperty(value = "apmaterno",required = true)
    @Column(name = "APMATERNO", length = 60,columnDefinition = "apellido materno usuario")
    private String apmaterno;


    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID", insertable = false, updatable = false)
    private List<video> videoList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }


    public List<video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<video> videoList) {
        this.videoList = videoList;
    }
}
