package br.com.nexohub.api.domain.sala;

public record DadosListagemSala(Long id, String nome, String dimensao, String equipamentos) {

    public DadosListagemSala(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getDimensao(), sala.getEquipamentos());
    }
}
