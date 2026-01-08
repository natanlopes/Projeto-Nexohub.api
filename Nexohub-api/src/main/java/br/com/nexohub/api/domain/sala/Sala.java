package br.com.nexohub.api.domain.sala;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "salas")
@Entity(name = "Sala")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String dimensao;
    private String equipamentos;
    private String observacao;
    private Boolean ativo;

    public Sala(DadosCadastroSala dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.dimensao = dados.dimensao();
        this.equipamentos = dados.equipamentos();
        this.observacao = dados.observacao();
    }

    public Sala() {

    }
    public void excluir() {
        this.ativo = false;
    }
    public void atualizarInformacoes(DadosAtualizacaoSala dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.dimensao() != null) {
            this.dimensao = dados.dimensao();
        }
        // AQUI ESTÁ A MÁGICA: Trocar os equipamentos
        if (dados.equipamentos() != null) {
            this.equipamentos = dados.equipamentos();
        }
        if (dados.observacao() != null) {
            this.observacao = dados.observacao();
        }
    }
    // Getters Manuais (Se precisar)
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDimensao() { return dimensao; }
    public String getEquipamentos() { return equipamentos; }
    public String getObservacao() { return observacao; }
    public Boolean getAtivo() { return ativo; }

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public void setEquipamentos(String equipamentos) {
		this.equipamentos = equipamentos;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
