package com.aluraChallenge.ForoHub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //Usar id para las comparaciones
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_de_creacion;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha_de_creacion = LocalDateTime.now();
        this.status = true;
        this.autor = datosRegistroTopico.autor();
        this.curso = datosRegistroTopico.curso();
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.datosRegistroTopico().titulo() != null) {
            this.titulo = datosActualizarTopico.datosRegistroTopico().titulo();
        }
        if (datosActualizarTopico.datosRegistroTopico().mensaje() != null) {
            this.mensaje = datosActualizarTopico.datosRegistroTopico().mensaje();
        }
    }
}

