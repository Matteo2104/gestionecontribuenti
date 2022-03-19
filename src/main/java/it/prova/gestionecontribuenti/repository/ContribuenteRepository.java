package it.prova.gestionecontribuenti.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteRepository extends PagingAndSortingRepository<Contribuente, Long>, JpaSpecificationExecutor<Contribuente> {

}
