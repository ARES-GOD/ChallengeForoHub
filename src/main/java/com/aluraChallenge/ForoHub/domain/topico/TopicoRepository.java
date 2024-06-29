package com.aluraChallenge.ForoHub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Boolean existsByMensajeAndTitulo(String mensaje, String titulo);
}
