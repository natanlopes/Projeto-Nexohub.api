package br.com.nexohub.api.domain.sala;

public record DadosDetalhamentoSala(Long id, String nome, String dimensao, String equipamentos, String observacao) {

    public DadosDetalhamentoSala(Sala sala) {
        this(sala.getId(), sala.getNome(), sala.getDimensao(), sala.getEquipamentos(), sala.getObservacao());
    }
}
