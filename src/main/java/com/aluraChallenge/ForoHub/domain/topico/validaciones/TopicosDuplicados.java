package com.aluraChallenge.ForoHub.domain.topico.validaciones;

import com.aluraChallenge.ForoHub.domain.topico.DatosRegistroTopico;
import com.aluraChallenge.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicosDuplicados implements ValidadorDeTopicos{

    @Autowired
    private TopicoRepository repository;

    public void validar(DatosRegistroTopico datos) {
        var topicoDuplicado = repository.existsByMensajeAndTitulo(datos.mensaje(),datos.titulo());
        if(topicoDuplicado){
            throw new ValidationException("Este topico ya existe.");
        }
    }
}
