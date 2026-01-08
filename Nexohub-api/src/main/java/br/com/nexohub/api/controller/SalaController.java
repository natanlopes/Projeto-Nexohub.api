package br.com.nexohub.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.nexohub.api.domain.sala.DadosAtualizacaoSala;
import br.com.nexohub.api.domain.sala.DadosCadastroSala;
import br.com.nexohub.api.domain.sala.DadosDetalhamentoSala;
import br.com.nexohub.api.domain.sala.DadosListagemSala;
import br.com.nexohub.api.domain.sala.Sala;
import br.com.nexohub.api.domain.sala.SalaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("salas")
public class SalaController {

    @Autowired
    private SalaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroSala dados, UriComponentsBuilder uriBuilder) {
        var sala = new Sala(dados);
        repository.save(sala);

        var uri = uriBuilder.path("/salas/{id}").buildAndExpand(sala.getId()).toUri();
        // Retorna o DTO de detalhamento (Boa prática!)
        return ResponseEntity.created(uri).body(new DadosDetalhamentoSala(sala));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemSala>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        // Agora usamos o seu método novo para filtrar só as ativas!
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemSala::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoSala dados) {
        var sala = repository.getReferenceById(dados.id());
        sala.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoSala(sala));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var sala = repository.getReferenceById(id);
        sala.excluir(); // Lembra que criamos esse método na Entity?

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var sala = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoSala(sala));
    }
}