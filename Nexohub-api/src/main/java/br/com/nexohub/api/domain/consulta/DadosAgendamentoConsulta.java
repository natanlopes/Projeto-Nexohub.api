package br.com.nexohub.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
        @NotNull
        Long idProfissional,

        @NotNull
        Long idPaciente,

        @NotNull
        Long idSala, // O novo campo crucial!

        @NotNull
        @Future // Só permite datas no futuro
        LocalDateTime data,

        String procedimento // Opcional (Ex: "Canal", "Check-up")
) {
}
