package it.prova.gestionecontribuenti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cartellaesattoriale")
public class CartellaEsattoriale {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="descrizione")
	private String descrizione;
	
	@Column(name="importo")
	private int importo;
	
	@Column(name="stato")
	private Stato stato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contribuente_id", nullable = false)
	private Contribuente contribuente;
	
	public Long getId() {
		return id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public int getImporto() {
		return importo;
	}
	public Stato getStato() {
		return stato;
	}
	public Contribuente getContribuente() {
		return contribuente;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public void setImporto(int importo) {
		this.importo = importo;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	public void setContribuente(Contribuente contribuente) {
		this.contribuente = contribuente;
	}
	
	
	@Override
	public String toString() {
		return "CartellaEsattoriale [id=" + id + ", descrizione=" + descrizione + ", importo=" + importo + ", stato="
				+ stato + "]";
	}
	
	
	
}
