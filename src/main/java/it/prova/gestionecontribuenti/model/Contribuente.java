package it.prova.gestionecontribuenti.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contribuente")
public class Contribuente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cognome")
	private String cognome;
	
	@Column(name = "datadinascita")
	private Date dataDiNascita;
	
	@Column(name = "codicefiscale")
	private String codiceFiscale;
	
	@Column(name = "indirizzo")
	private String indirizzo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contribuente")
	private Set<CartellaEsattoriale> cartelleEsattoriali = new HashSet<>();
	
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public Date getDataDiNascita() {
		return dataDiNascita;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public Set<CartellaEsattoriale> getCartelleEsattoriali() {
		return cartelleEsattoriali;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public void setCartelleEsattoriali(Set<CartellaEsattoriale> cartelleEsattoriali) {
		this.cartelleEsattoriali = cartelleEsattoriali;
	}
	
	
	@Override
	public String toString() {
		return "Contribuente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", codiceFiscale=" + codiceFiscale + ", indirizzo=" + indirizzo + "]";
	}
	
	
}
