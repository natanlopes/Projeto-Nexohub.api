package br.com.nexohub.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nexohub.api.domain.paciente.PacienteRepository;
import br.com.nexohub.api.domain.profissional.ProfissionalRepository;
import br.com.nexohub.api.domain.sala.SalaRepository;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private SalaRepository salaRepository;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        // Agora usamos .idProfissional()
        if (!profissionalRepository.existsById(dados.idProfissional())) {
            throw new RuntimeException("Id do profissional informado não existe!");
        }
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new RuntimeException("Id do paciente informado não existe!");
        }
        if (!salaRepository.existsById(dados.idSala())) {
            throw new RuntimeException("Id da sala informado não existe!");
        }

        // Buscamos o profissional genérico
        var profissional = profissionalRepository.getReferenceById(dados.idProfissional());
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var sala = salaRepository.getReferenceById(dados.idSala());

        // Na hora de salvar, a entidade Consulta ainda espera um "medico" (nome do campo no banco).
        // Podemos passar o profissional ali sem problemas, pois Profissional é a classe correta.
        var consulta = new Consulta(null, profissional, paciente, sala, dados.data(), dados.procedimento());

        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }
}