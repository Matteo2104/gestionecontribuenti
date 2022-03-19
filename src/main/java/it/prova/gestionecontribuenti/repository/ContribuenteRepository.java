package it.prova.gestionecontribuenti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository extends PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {
	List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);
}
