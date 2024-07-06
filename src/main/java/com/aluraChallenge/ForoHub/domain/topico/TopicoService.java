package com.aluraChallenge.ForoHub.domain.topico;


import com.aluraChallenge.ForoHub.domain.topico.validaciones.ValidadorDeTopicos;
import com.aluraChallenge.ForoHub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    List<ValidadorDeTopicos> validadores;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuestaTopico actualizar(DatosActualizarTopico datos) {
        if (!topicoRepository.findById(datos.id()).isPresent()) {
            throw new ValidacionDeIntegridad("Este id no fue encontrado.");
        }

        validadores.forEach(v -> v.validar(datos.datosRegistroTopico()));
        validadores.get(0);

        Topico topico = topicoRepository.findById(datos.id()).get();
        topico.actualizarDatos(datos);

        return new DatosRespuestaTopico(topico.getId(),
                topico.getTitulo(),topico.getMensaje(),topico.getFecha_de_creacion());
    }
}
