package com.aluraChallenge.ForoHub.controller;

import com.aluraChallenge.ForoHub.domain.topico.*;
import com.aluraChallenge.ForoHub.domain.topico.validaciones.ValidadorDeTopicos;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    List<ValidadorDeTopicos> validadores;

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo topico en la base de datos")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder) {

        validadores.forEach(v -> v.validar(datosRegistroTopico));

        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFecha_de_creacion()
                );

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);

    }

    @GetMapping
    @Operation(summary = "Obtiene el listado de topicos")
    public ResponseEntity<Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
    }

    // GET por id
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosListadoTopicos> retornaDatosTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosListadoTopicos(
                topico.getTitulo(),topico.getMensaje(),topico.getFecha_de_creacion(),
                topico.getStatus(),topico.getAutor(),topico.getCurso()
        );
        return ResponseEntity.ok(datosTopico);
    }

    // PUT por id
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualiza los datos de un topico existente")
    public ResponseEntity actualizarTopico(@PathVariable Long id,
                                           @RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        DatosActualizarTopico datosActualizarTopico = new DatosActualizarTopico(id,datosRegistroTopico);
        var response = service.actualizar(datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

}
