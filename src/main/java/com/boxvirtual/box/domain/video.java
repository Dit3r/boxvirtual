package com.boxvirtual.box.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Data
@Table(name = "VIDEOUSUARIO")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(value = {"id","idUsuario","fechaHora","estado"})

public class video {

    @Id
    @ApiModelProperty(notes = "id de la tabla")
    @JsonProperty(value = "id",required = true)
    @Column(name = "ID", length = 12,columnDefinition = "id de la tabla")
    private Long id;

    @ApiModelProperty(notes = "id usuario")
    @JsonProperty(value = "idUsuario",required = true)
    @Column(name = "ID_USUARIO", length = 30,columnDefinition = "id usuario")
    private Long idUsuario;

    @ApiModelProperty(notes = "fecha y hora ")
    @JsonProperty(value = "fechaHora",required = true)
    @Column(name = "FECHA_HORA", length = 20,columnDefinition = "fecha y hora")
    private Date fechaHora;

    @ApiModelProperty(notes = "estado video")
    @JsonProperty(value = "estado",required = true)
    @Column(name = "ESTADO", length = 50,columnDefinition = "estado video")
    private Long estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
