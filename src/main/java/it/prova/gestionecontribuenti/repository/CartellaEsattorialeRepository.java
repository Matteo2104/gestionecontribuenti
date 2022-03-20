package it.prova.gestionecontribuenti.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeRepository extends PagingAndSortingRepository<CartellaEsattoriale, Long>, JpaSpecificationExecutor<CartellaEsattoriale> {
	@Query("select ce from CartellaEsattoriale ce join fetch ce.contribuente where ce.id = ?1")
	public CartellaEsattoriale findByIdEager(Long id);
}
