package com.comunenapoli.progetto.businessLogic;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Utente;


public class NoleggioDao implements DaoInterface<Noleggio> {
	private EntityManager manager = null;

	public NoleggioDao()
	{
		this(null);
	}
	
	public NoleggioDao(EntityManager entityManager)
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
	public void create(Noleggio noleggio) {
		manager.persist(noleggio);
	}

	@Override
	public List<Noleggio> retrieve() {
		List<Noleggio> noleggi = manager.createQuery("from Noleggio",Noleggio.class).getResultList();
		return noleggi;
	}

	@Override
	public void update(Noleggio noleggio) {
		manager.persist(noleggio);
	}

	@Override
	public void delete(Noleggio noleggio) {
		manager.remove(noleggio);
	}
	
	public Noleggio findNoleggioByAuto(Auto auto) {
		return manager.createQuery("select n from Noleggio n where n.auto = :x ",Noleggio.class).
				setParameter("x",auto).getResultList().stream().findFirst().orElse(null);
	}
	
	public List<Noleggio> findNoleggiByUtente(Utente utente) {
		return manager.createQuery("select n from Noleggio n where n.utente = :x ",Noleggio.class).
				setParameter("x",utente).getResultList();
	}
	
	public Noleggio findNoleggioByIdNoleggio(Integer idNoleggio) {
		return manager.createQuery("select n from Noleggio n where n.idNoleggio = :x ",Noleggio.class).
				setParameter("x",idNoleggio).getResultList().stream().findFirst().orElse(null);
	}
	
	public Noleggio findNoleggioDisponibileByAuto(Auto auto, Date dataInizio) {
		return manager.createQuery("select n from Noleggio n where n.auto = :x and n.dataFine >= :y ",Noleggio.class).
				setParameter("x",auto).setParameter("y", dataInizio).getResultList().stream().findFirst().orElse(null);
	}
	
	public List<Noleggio> findNoleggiByAuto(Auto auto) {
		return manager.createQuery("select n from Noleggio n where n.auto = :x ",Noleggio.class).
				setParameter("x",auto).getResultList();
	}

	public List<Noleggio> findNoleggiByDataInizio(Date dataChiusura) {
		return manager.createQuery("select n from Noleggio n where n.dataInizio = :x ",Noleggio.class).
				setParameter("x",dataChiusura).getResultList();
	}

	public List<Noleggio> findNoleggiByDataInizioDataFine(Date dataInizioChiusura, Date dataFineChiusura) {
		return manager.createQuery("select n from Noleggio n where n.dataInizio between :x and :y ",Noleggio.class).
				setParameter("x",dataInizioChiusura).setParameter("y",dataFineChiusura).getResultList();
	}
	
	
	
	
	
	
	
}