package br.com.nexohub.api.domain.profissional;

import br.com.nexohub.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProfissional(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}