package it.prova.gestionecontribuenti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;

public interface CartellaEsattorialeService {

	public List<CartellaEsattoriale> listAllElements();

	public void aggiungiNuovo(CartellaEsattoriale cartellaEsattoriale);

	public CartellaEsattoriale caricaSingoloElemento(Long idCartellaEsattoriale);

	public void rimuovi(Long id);

	public void aggiorna(CartellaEsattoriale cartellaEsattoriale);

	public Page<CartellaEsattoriale> findByExampleWithPagination(CartellaEsattoriale example, Integer pageNo, Integer pageSize,
			String sortBy);

}
