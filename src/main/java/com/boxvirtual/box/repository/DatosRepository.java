package com.boxvirtual.box.repository;



import com.boxvirtual.box.domain.datosMedicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DatosRepository extends JpaRepository<datosMedicos, Long> {



    @Query(value = "SELECT * FROM paciente.datosmedicos\n" +
            "WHERE id_usuario = :idUsuario\n"+
            "order by id desc limit 1",
            nativeQuery = true)
    datosMedicos findByIdUsuarioDatosUltimo(@Param("idUsuario") Long idUsuario);


    @Query(value = "SELECT * FROM paciente.datosmedicos\n" +
            "WHERE id_usuario = :idUsuario\n"+
            "order by id desc ",
            nativeQuery = true)
    List<datosMedicos>  findByIdUsuarioDatos(@Param("idUsuario") Long idUsuario);


}
