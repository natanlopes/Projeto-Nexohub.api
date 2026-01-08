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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.nexohub.api.domain.profissional.DadosAtualizacaoProfissional;
import br.com.nexohub.api.domain.profissional.DadosCadastroProfissional;
import br.com.nexohub.api.domain.profissional.DadosDetalhamentoProfissional;
import br.com.nexohub.api.domain.profissional.DadosListagemProfissional;
import br.com.nexohub.api.domain.profissional.Profissional;
import br.com.nexohub.api.domain.profissional.ProfissionalRepository;
import br.com.nexohub.api.domain.profissional.TipoProfissional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("profissionais")
public class ProfissionalController {

    @Autowired
    private ProfissionalRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProfissional dados, UriComponentsBuilder uriBuilder) {
        var profissional = new Profissional(dados);
        repository.save(profissional);

        var uri = uriBuilder.path("/profissionais/{id}").buildAndExpand(profissional.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoProfissional(profissional));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemProfissional>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemProfissional::new);
        return ResponseEntity.ok(page);
    }

    // Filtro exclusivo do Nexo Hub: Listar por Tipo (Ex: Só Dentistas)
    @GetMapping("/busca")
    public ResponseEntity<Page<DadosListagemProfissional>> listarPorTipo(@RequestParam TipoProfissional tipo, Pageable paginacao) {
        var page = repository.findAllByAtivoTrueAndTipo(tipo, paginacao).map(DadosListagemProfissional::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoProfissional dados) {
        var profissional = repository.getReferenceById(dados.id());
        profissional.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoProfissional(profissional));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var profissional = repository.getReferenceById(id);
        profissional.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var profissional = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoProfissional(profissional));
    }
}