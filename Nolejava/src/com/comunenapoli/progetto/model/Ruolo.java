package com.comunenapoli.progetto.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ruolo {
	@Id
	private Integer idRuolo = null;
	private String nomeRuolo = null;
	
	@OneToMany(mappedBy = "ruoloUtente")
	private Set<Utente> utenti = null;
	
	@OneToMany(mappedBy = "ruoloCalendario")
	private Set<CalendarioChiusure> calendariChiusure = null;
	
	public Ruolo()
	{
		this(null,null);
	}
	
	public Ruolo(String nomeRuolo, Integer id)
	{
		utenti = new HashSet<Utente>();
		setNomeRuolo(nomeRuolo);
		setIdRuolo(id);
	}

	public Integer getIdRuolo() {
		return idRuolo;
	}

	public void setIdRuolo(Integer id) {
		this.idRuolo = id;
	}

	public String getNomeRuolo() {
		return nomeRuolo;
	}

	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idRuolo == null) ? 0 : idRuolo.hashCode());
		result = prime * result + ((nomeRuolo == null) ? 0 : nomeRuolo.hashCode());
		result = prime * result + ((utenti == null) ? 0 : utenti.hashCode());
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
		Ruolo other = (Ruolo) obj;
		if (idRuolo == null) {
			if (other.idRuolo != null)
				return false;
		} else if (!idRuolo.equals(other.idRuolo))
			return false;
		if (nomeRuolo == null) {
			if (other.nomeRuolo != null)
				return false;
		} else if (!nomeRuolo.equals(other.nomeRuolo))
			return false;
		if (utenti == null) {
			if (other.utenti != null)
				return false;
		} else if (!utenti.equals(other.utenti))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ruolo [idRuolo=" + idRuolo + ", nomeRuolo=" + nomeRuolo + ", utenti=" + utenti + "]";
	}
	

}