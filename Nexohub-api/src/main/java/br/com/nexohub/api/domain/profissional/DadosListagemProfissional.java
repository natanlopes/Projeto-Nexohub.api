package br.com.nexohub.api.domain.profissional;

public record DadosListagemProfissional(Long id, String nome, String email, String registroConselho, TipoProfissional tipo,String especialidade) {

    public DadosListagemProfissional(Profissional profissional) {
        this(profissional.getId(), profissional.getNome(), profissional.getEmail(), profissional.getRegistroConselho(), profissional.getTipo(), profissional.getEspecialidade());
    }
}