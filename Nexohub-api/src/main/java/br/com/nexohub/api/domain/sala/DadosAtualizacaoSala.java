package br.com.nexohub.api.domain.sala;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSala(
        @NotNull
        Long id,
        String nome,
        String dimensao,
        String equipamentos, // O campo mais importante para sua regra de negócio!
        String observacao
) {
}