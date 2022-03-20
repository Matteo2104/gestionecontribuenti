package it.prova.gestionecontribuenti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository extends PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {
	public List<Contribuente> findByCognomeIgnoreCaseContainingOrNomeIgnoreCaseContainingOrderByNomeAsc(String cognome,
			String nome);
	
	@Query("select c from Contribuente c join fetch c.cartelleEsattoriali where c.id = ?1")
	public Contribuente caricaSingoloElementoEager(Long id);
}
