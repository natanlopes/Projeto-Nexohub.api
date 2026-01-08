package br.com.nexohub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nexohub.api.domain.consulta.AgendaDeConsultas;
import br.com.nexohub.api.domain.consulta.ConsultaRepository;
import br.com.nexohub.api.domain.consulta.DadosAgendamentoConsulta;
import br.com.nexohub.api.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }


    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoConsulta>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        // Busca tudo no banco, converte para DTO e devolve paginado
        var page = repository.findAll(paginacao).map(DadosDetalhamentoConsulta::new);
        return ResponseEntity.ok(page);
    }
}