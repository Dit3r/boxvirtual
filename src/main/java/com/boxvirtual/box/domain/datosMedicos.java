package com.boxvirtual.box.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "DATOSMEDICOS")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder(value = {"id","idUsuario","enfermedad","alergico","cosas_alergia","id_video","motivo_consulta"})

public class datosMedicos {


    @Id
    @ApiModelProperty(notes = "id de la tabla", required = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_datos_medicos")
    @SequenceGenerator(name = "seq_datos_medicos", sequenceName = "seq_datos_medicos", allocationSize = 1)
    @Column(name = "ID", length = 12,columnDefinition = "id de la tabla")
    private Long id;


    @ApiModelProperty(notes = "id usuario")
    @JsonProperty(value = "idUsuario",required = true)
    @Column(name = "ID_USUARIO", length = 30,columnDefinition = "id usuario")
    private Long idUsuario;

    @ApiModelProperty(notes = "enfermedad ")
    @JsonProperty(value = "enfermedad",required = true)
    @Column(name = "ENFERMEDAD", length = 20,columnDefinition = "enfermedad")
    private String enfermedad;

    @ApiModelProperty(notes = "alergico")
    @JsonProperty(value = "alergico",required = true)
    @Column(name = "ALERGICO", length = 4,columnDefinition = "alergico")
    private String alergico;

    @ApiModelProperty(notes = "cosas alergia")
    @JsonProperty(value = "cosasAlergia",required = true)
    @Column(name = "COSAS_ALERGIA", length = 60,columnDefinition = "cosas alergia")
    private String cosasAlergia;

    @ApiModelProperty(notes = "id video")
    @JsonProperty(value = "idVideo",required = true)
    @Column(name = "ID_VIDEO", length = 30,columnDefinition = "id video")
    private Long idVideo;


    @ApiModelProperty(notes = "motivo consulta")
    @JsonProperty(value = "motivoConsulta",required = true)
    @Column(name = "MOTIVO_CONSULTA", length = 200,columnDefinition = "motivo consulta")
    private String motivoConsulta;


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

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getAlergico() {
        return alergico;
    }

    public void setAlergico(String alergico) {
        this.alergico = alergico;
    }

    public String getCosasAlergia() {
        return cosasAlergia;
    }

    public void setCosasAlergia(String cosasAlergia) {
        this.cosasAlergia = cosasAlergia;
    }

    public Long getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(Long idVideo) {
        this.idVideo = idVideo;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }



}
