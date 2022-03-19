package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionecontribuenti.model.Contribuente;

public interface ContribuenteService {

	public Page<Contribuente> findByExampleWithPagination(Contribuente example, Integer pageNo, Integer pageSize,
			String sortBy);

	public List<Contribuente> listAllElements();

	public void aggiungiNuovo(Contribuente contribuente);

	public Contribuente caricaSingoloElemento(Long idContribuente);

	public void rimuovi(Long id);

}
