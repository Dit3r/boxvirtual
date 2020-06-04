package com.boxvirtual.box.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "VIDEOUSUARIO")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(value = {"id","idUsuario","fechaHora","estado","vigente"})

public class video {



    @Id
    @ApiModelProperty(notes = "id de la tabla", required = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_video")
    @SequenceGenerator(name = "seq_video", sequenceName = "seq_video", allocationSize = 1)
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

    @ApiModelProperty(notes = "vigencia video")
    @JsonProperty(value = "vigente",required = true)
    @Column(name = "VIGENTE", length = 50,columnDefinition = "vigencia video")
    private Long vigente;




    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ID_VIDEO", referencedColumnName = "ID", insertable = false, updatable = false)
    private List<datosMedicos> datosMedicosList;




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

    public Long getVigente() {
        return vigente;
    }

    public void setVigente(Long vigente) {
        this.vigente = vigente;

    }




    public List<datosMedicos> getDatosMedicosList() {
        return datosMedicosList;
    }

    public void setDatosMedicosList(List<datosMedicos> datosMedicosList) {
        this.datosMedicosList = datosMedicosList;
    }
}
