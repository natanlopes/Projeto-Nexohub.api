package br.com.nexohub.api.domain.sala;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SalaRepository extends JpaRepository<Sala, Long> {

	  // Método para listar apenas os ativos (exclusão lógica)
    Page<Sala> findAllByAtivoTrue(Pageable paginacao);


}
