package br.com.nexohub.api.domain.profissional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

    // Método para listar apenas os ativos (exclusão lógica)
    Page<Profissional> findAllByAtivoTrue(Pageable paginacao);

    // Método extra para filtrar por tipo (Ex: Listar só os DENTISTAS)
    Page<Profissional> findAllByAtivoTrueAndTipo(TipoProfissional tipo, Pageable paginacao);
}