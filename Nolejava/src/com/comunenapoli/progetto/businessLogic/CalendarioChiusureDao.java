package com.comunenapoli.progetto.businessLogic;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.CalendarioChiusure;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Patente;

public class CalendarioChiusureDao implements DaoInterface<CalendarioChiusure> {
	
	private EntityManager manager = null;
	
	public CalendarioChiusureDao()
	{
		this(null);
	}
	
	public CalendarioChiusureDao(EntityManager entityManager)
	{
		setManager(entityManager);
	}

	public EntityManager getManager() {
		return manager;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void create(CalendarioChiusure calendarioChiusure) {
		manager.persist(calendarioChiusure);
	}

	@Override
	public List<CalendarioChiusure> retrieve() {
		List<CalendarioChiusure> listaCalendari = manager.createQuery("from CalendarioChiusure",CalendarioChiusure.class).getResultList();
		return listaCalendari;
	}

	@Override
	public void update(CalendarioChiusure calendarioChiusure) {
		manager.persist(calendarioChiusure);
	}

	@Override
	public void delete(CalendarioChiusure calendarioChiusure) {
		manager.remove(calendarioChiusure);
	}
	
	public CalendarioChiusure findByDataInizioDataFine(Date dataInizio, Date dataFine) {
		return manager.createQuery("select c from CalendarioChiusure c where c.dataInizioChiusura = :x and c.dataFineChiusura = :y ",CalendarioChiusure.class).
				setParameter("x",dataInizio).setParameter("y", dataFine).getResultList().stream().findFirst().orElse(null);
		
		
	}
	
	public boolean findByDataInizioUtente(Date dataInizioUtente) {
		CalendarioChiusure calendarioChiusure = manager.createQuery("select c from CalendarioChiusure c where c.dataInizioChiusura < :x or c.dataFineChiusura > :x ",CalendarioChiusure.class).
				setParameter("x",dataInizioUtente).getResultList().stream().findFirst().orElse(null);
		
		if (calendarioChiusure != null) {
			return false;
		}
		
		return true;
	}
	

}
