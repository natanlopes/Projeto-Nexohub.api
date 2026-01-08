package br.com.nexohub.api.domain.profissional;

import br.com.nexohub.api.domain.endereco.Endereco;

public record DadosDetalhamentoProfissional(Long id, String nome, String email, String registroConselho, TipoProfissional tipo, Endereco endereco, String especialidade) {

    public DadosDetalhamentoProfissional(Profissional profissional) {
        this(profissional.getId(), profissional.getNome(), profissional.getEmail(), profissional.getRegistroConselho(), profissional.getTipo(), profissional.getEndereco(),profissional.getEspecialidade());
    }
}