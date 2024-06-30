package com.aluraChallenge.ForoHub.domain.topico;

import org.springframework.web.bind.annotation.PutMapping;

import java.security.PublicKey;
import java.time.LocalDateTime;

public record DatosListadoTopicos(String titulo,
                                  String mensaje,
                                  LocalDateTime fecha_de_creacion,
                                  boolean status,
                                  String autor,
                                  String curso) {

    public DatosListadoTopicos(Topico topico){
        this(topico.getTitulo(),topico.getMensaje(),topico.getFecha_de_creacion(),
                topico.getStatus(),topico.getAutor(),topico.getCurso());
    }
}
