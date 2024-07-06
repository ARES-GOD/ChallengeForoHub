package com.aluraChallenge.ForoHub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(@NotNull Long id,
                                    DatosRegistroTopico datosRegistroTopico) {
}
