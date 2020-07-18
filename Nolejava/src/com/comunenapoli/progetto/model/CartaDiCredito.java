package com.comunenapoli.progetto.model;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CartaDiCredito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCartaDiCredito = null;
	
	@Column(unique = true)
	private String numeroCarta = null;
	
	private Integer cvv = null;
	
	private Date dataDiScadenza = null;
	
	@OneToOne
	private Utente utente = null;
	
	public CartaDiCredito() {
		this(null, null, null,null);
	}

	public CartaDiCredito(String numeroCarta, Integer cvv, Date dataDiScadenza, Utente utente) {
		this.numeroCarta = numeroCarta;
		this.cvv = cvv;
		this.dataDiScadenza = dataDiScadenza;
		this.utente = utente;
	}

	public Integer getIdCartaDiCredito() {
		return idCartaDiCredito;
	}

	public void setIdCartaDiCredito(Integer idCartaDiCredito) {
		this.idCartaDiCredito = idCartaDiCredito;
	}

	public String getNumeroCarta() {
		return numeroCarta;
	}

	public void setNumeroCarta(String numeroCarta) {
		this.numeroCarta = numeroCarta;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public Date getDataDiScadenza() {
		return dataDiScadenza;
	}

	public void setDataDiScadenza(Date dataDiScadenza) {
		this.dataDiScadenza = dataDiScadenza;
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
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		result = prime * result + ((dataDiScadenza == null) ? 0 : dataDiScadenza.hashCode());
		result = prime * result + ((idCartaDiCredito == null) ? 0 : idCartaDiCredito.hashCode());
		result = prime * result + ((numeroCarta == null) ? 0 : numeroCarta.hashCode());
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
		CartaDiCredito other = (CartaDiCredito) obj;
		if (cvv == null) {
			if (other.cvv != null)
				return false;
		} else if (!cvv.equals(other.cvv))
			return false;
		if (dataDiScadenza == null) {
			if (other.dataDiScadenza != null)
				return false;
		} else if (!dataDiScadenza.equals(other.dataDiScadenza))
			return false;
		if (idCartaDiCredito == null) {
			if (other.idCartaDiCredito != null)
				return false;
		} else if (!idCartaDiCredito.equals(other.idCartaDiCredito))
			return false;
		if (numeroCarta == null) {
			if (other.numeroCarta != null)
				return false;
		} else if (!numeroCarta.equals(other.numeroCarta))
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
		return "CartaDiCredito [idCartaDiCredito=" + idCartaDiCredito + ", numeroCarta=" + numeroCarta + ", cvv=" + cvv
				+ ", dataDiScadenza=" + dataDiScadenza + ", utente=" + utente + "]";
	}
	
	
	
	

}
