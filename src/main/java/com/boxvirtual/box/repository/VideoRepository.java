package com.boxvirtual.box.repository;



import com.boxvirtual.box.domain.video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<video, Long> {



    @Query(value = "SELECT * FROM paciente.videousuario\n" +
            "WHERE id_usuario = :idUsuario\n"+
            "order by fecha_hora desc limit 1",
            nativeQuery = true)
    video findByIdUsuarioUltimo(@Param("idUsuario") Long idUsuario);



}
