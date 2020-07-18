package com.comunenapoli.progetto.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CalendarioChiusure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCalendario = null;
	
	Date dataInizioChiusura = null;
	Date dataFineChiusura = null;
	
	@ManyToOne
	@JoinColumn(name="ruoloCalendario")
	private Ruolo ruoloCalendario = null;
	

	public CalendarioChiusure() {
		this(null,null,null);
	}
	
	public CalendarioChiusure(Date dataInizioChiusura, Date dataFineChiusura) {
		this.dataInizioChiusura = dataInizioChiusura;
		this.dataFineChiusura = dataFineChiusura;
	}

	public CalendarioChiusure(Date dataInizioChiusura, Date dataFineChiusura, Ruolo ruoloUtente) {
		super();
		this.dataInizioChiusura = dataInizioChiusura;
		this.dataFineChiusura = dataFineChiusura;
		this.ruoloCalendario = ruoloUtente;
	}

	public Integer getIdCalendario() {
		return idCalendario;
	}

	public void setIdCalendario(Integer idCalendario) {
		this.idCalendario = idCalendario;
	}

	public Date getDataInizioChiusura() {
		return dataInizioChiusura;
	}

	public void setDataInizioChiusura(Date dataInizioChiusura) {
		this.dataInizioChiusura = dataInizioChiusura;
	}

	public Date getDataFineChiusura() {
		return dataFineChiusura;
	}

	public void setDataFineChiusura(Date dataFineChiusura) {
		this.dataFineChiusura = dataFineChiusura;
	}

	public Ruolo getRuoloCalendario() {
		return ruoloCalendario;
	}

	public void setRuoloCalendario(Ruolo ruoloCalendario) {
		this.ruoloCalendario = ruoloCalendario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataFineChiusura == null) ? 0 : dataFineChiusura.hashCode());
		result = prime * result + ((dataInizioChiusura == null) ? 0 : dataInizioChiusura.hashCode());
		result = prime * result + ((idCalendario == null) ? 0 : idCalendario.hashCode());
		result = prime * result + ((ruoloCalendario == null) ? 0 : ruoloCalendario.hashCode());
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
		CalendarioChiusure other = (CalendarioChiusure) obj;
		if (dataFineChiusura == null) {
			if (other.dataFineChiusura != null)
				return false;
		} else if (!dataFineChiusura.equals(other.dataFineChiusura))
			return false;
		if (dataInizioChiusura == null) {
			if (other.dataInizioChiusura != null)
				return false;
		} else if (!dataInizioChiusura.equals(other.dataInizioChiusura))
			return false;
		if (idCalendario == null) {
			if (other.idCalendario != null)
				return false;
		} else if (!idCalendario.equals(other.idCalendario))
			return false;
		if (ruoloCalendario == null) {
			if (other.ruoloCalendario != null)
				return false;
		} else if (!ruoloCalendario.equals(other.ruoloCalendario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CalendarioChiusure [idCalendario=" + idCalendario + ", dataInizioChiusura=" + dataInizioChiusura
				+ ", dataFineChiusura=" + dataFineChiusura + ", ruoloCalendario=" + ruoloCalendario + "]";
	}


	
	

}
