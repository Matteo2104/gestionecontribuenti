package it.prova.gestionecontribuenti.dto;


import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionecontribuenti.model.CartellaEsattoriale;
import it.prova.gestionecontribuenti.model.Stato;

public class CartellaEsattorialeDTO {
	
	private Long id;
	
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	
	@NotNull(message = "{importo.notnull}")
	@Min(1)
	private Integer importo;
	
	@NotNull(message = "{stato.notnull}")
	private Stato stato;
	
	@NotNull(message = "{contribuente.notnull}")
	private ContribuenteDTO contribuente;
	
	public CartellaEsattorialeDTO() {}
	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, Stato stato, ContribuenteDTO contribuente) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}
	public CartellaEsattorialeDTO(Long id, String descrizione, Integer importo, Stato stato) {
		this.id = id;
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
	}
	public CartellaEsattorialeDTO(String descrizione, Integer importo, Stato stato, ContribuenteDTO contribuente) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.stato = stato;
		this.contribuente = contribuente;
	}

	public Long getId() {
		return id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Integer getImporto() {
		return importo;
	}

	public Stato getStato() {
		return stato;
	}

	public ContribuenteDTO getContribuente() {
		return contribuente;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setImporto(Integer importo) {
		this.importo = importo;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public void setContribuente(ContribuenteDTO contribuente) {
		this.contribuente = contribuente;
	}
	
	
	public CartellaEsattoriale buildCartellaEsattorialeModel() {
		//System.out.println(this);
		//return new CartellaEsattoriale(this.id, this.descrizione, this.importo, this.stato, this.contribuente.buildContribuenteModel());
		CartellaEsattoriale cartella = new CartellaEsattoriale();
		
		cartella.setDescrizione(this.descrizione);
		cartella.setImporto(this.importo);
		cartella.setStato(this.stato);
		cartella.setContribuente(this.contribuente.buildContribuenteModel());
		
		return cartella;
	}

	public static CartellaEsattorialeDTO buildCartellaEsattorialeDTOFromModel(CartellaEsattoriale cartellaEsattorialeModel, boolean includeContribuente) {
		CartellaEsattorialeDTO result = new CartellaEsattorialeDTO(cartellaEsattorialeModel.getId(), cartellaEsattorialeModel.getDescrizione(), cartellaEsattorialeModel.getImporto(),
				cartellaEsattorialeModel.getStato());

		if (includeContribuente)
			result.setContribuente(ContribuenteDTO.buildContribuenteDTOFromModel(cartellaEsattorialeModel.getContribuente()));

		return result;
	}

	public static List<CartellaEsattorialeDTO> createCartellaEsattorialeDTOListFromModelList(List<CartellaEsattoriale> modelListInput, boolean includeContribuente) {
		return modelListInput.stream().map(cartellaEsattorialeEntity -> {
			return CartellaEsattorialeDTO.buildCartellaEsattorialeDTOFromModel(cartellaEsattorialeEntity, includeContribuente);
		}).collect(Collectors.toList());
	}
	@Override
	public String toString() {
		return "CartellaEsattorialeDTO [id=" + id + ", descrizione=" + descrizione + ", importo=" + importo + ", stato="
				+ stato + ", contribuente=" + contribuente + "]";
	}
	
	

	
	
	
}
