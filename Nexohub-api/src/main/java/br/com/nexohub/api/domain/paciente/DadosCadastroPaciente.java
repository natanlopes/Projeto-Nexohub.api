package br.com.nexohub.api.domain.paciente;

import br.com.nexohub.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(

        @NotBlank // O nome é obrigatório!
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}") // Validação básica de formato de CPF
        String cpf,

        @NotNull // O objeto endereço não pode ser nulo
        @Valid   // Valida também os campos DENTRO do endereço (cep, rua, etc)
        DadosEndereco endereco

) {
}
