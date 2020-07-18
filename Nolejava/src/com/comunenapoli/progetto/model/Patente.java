package com.comunenapoli.progetto.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Patente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPatente = null;
	
	@Column(unique = true)
	private String numeroPatente = null;
	private Date dataScadenza = null;
	
	@OneToOne
	private Utente utente = null;
	
	public Patente() {
		this(null, null, null);
	}

	public Patente(String numeroPatente, Date dataScadenza, Utente utente) {
		this.numeroPatente = numeroPatente;
		this.dataScadenza = dataScadenza;
		this.utente = utente;
	}

	public Integer getIdPatente() {
		return idPatente;
	}

	public void setIdPatente(Integer idPatente) {
		this.idPatente = idPatente;
	}

	public String getNumeroPatente() {
		return numeroPatente;
	}

	public void setNumeroPatente(String numeroPatente) {
		this.numeroPatente = numeroPatente;
	}


	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}


	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataScadenza == null) ? 0 : dataScadenza.hashCode());
		result = prime * result + ((idPatente == null) ? 0 : idPatente.hashCode());
		result = prime * result + ((numeroPatente == null) ? 0 : numeroPatente.hashCode());
		result = prime * result + ((utente == null) ? 0 : utente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patente other = (Patente) obj;
		if (dataScadenza == null) {
			if (other.dataScadenza != null)
				return false;
		} else if (!dataScadenza.equals(other.dataScadenza))
			return false;
		if (idPatente == null) {
			if (other.idPatente != null)
				return false;
		} else if (!idPatente.equals(other.idPatente))
			return false;
		if (numeroPatente == null) {
			if (other.numeroPatente != null)
				return false;
		} else if (!numeroPatente.equals(other.numeroPatente))
			return false;
		if (utente == null) {
			if (other.utente != null)
				return false;
		} else if (!utente.equals(other.utente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patente [idPatente=" + idPatente + ", numeroPatente=" + numeroPatente + ", dataScadenza=" + dataScadenza
				+ ", utente=" + utente + "]";
	}

	
	
	
	
	

}
