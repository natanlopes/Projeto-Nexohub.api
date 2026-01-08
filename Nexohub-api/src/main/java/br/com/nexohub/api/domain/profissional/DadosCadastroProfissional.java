package br.com.nexohub.api.domain.profissional;

import br.com.nexohub.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProfissional(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String registroConselho, // Ex: 123456 (Serve para CRM, CRO, CREFITO...)

        @NotNull
        TipoProfissional tipo, // O pulo do gato: define quem é o profissional

        @NotBlank String especialidade,

        @NotNull @Valid DadosEndereco endereco) {
}