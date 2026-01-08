package br.com.nexohub.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long id, Long idProfissional, Long idPaciente, Long idSala, LocalDateTime data, String procedimento) {
    public DadosDetalhamentoConsulta(Consulta consulta) {

        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getSala().getId(), consulta.getData(), consulta.getProcedimento());
    }
}