package br.com.nexohub.api.domain.sala;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroSala(
        @NotBlank
        String nome,        // Ex: "Sala de Cirurgia 01"
        String dimensao,    // Ex: "5x5m"
        String equipamentos,// Ex: "Maca, Monitor Cardíaco, Bisturi Elétrico"
        String observacao   // Ex: "Ar condicionado precisa de reparo"
) {
}
