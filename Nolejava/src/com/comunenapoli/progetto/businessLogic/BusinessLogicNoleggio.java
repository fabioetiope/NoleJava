package com.comunenapoli.progetto.businessLogic;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.CalendarioChiusure;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Utente;

public class BusinessLogicNoleggio {
	
	private NoleggioDao noleggioDao = null;
	private CalendarioChiusureDao calendarioChiusureDao = null;
	private EntityManager entityManager = null;
	
	
	public BusinessLogicNoleggio(NoleggioDao noleggioDao, CalendarioChiusureDao calendarioChiusureDao, EntityManager entityManager) {
		this.noleggioDao = noleggioDao;
		this.calendarioChiusureDao = calendarioChiusureDao;
		this.entityManager = entityManager;
	}


	public NoleggioDao getNoleggioDao() {
		return noleggioDao;
	}


	public void setNoleggioDao(NoleggioDao noleggioDao) {
		this.noleggioDao = noleggioDao;
	}


	public CalendarioChiusureDao getCalendarioChiusure() {
		return calendarioChiusureDao;
	}


	public void setCalendarioChiusure(CalendarioChiusureDao calendarioChiusure) {
		this.calendarioChiusureDao = calendarioChiusure;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void create(Noleggio noleggio) {
		entityManager.getTransaction().begin();
		try {
			noleggioDao.create(noleggio);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void update(Noleggio noleggio) {
		entityManager.getTransaction().begin();
		try {
			noleggioDao.update(noleggio);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void delete(Noleggio noleggio) {
		entityManager.getTransaction().begin();
		try {
			if (noleggio != null) {
				noleggioDao.delete(noleggio);
				entityManager.getTransaction().commit();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	
	public void setDisponibilitaAutoFalse (Auto auto) {
		Noleggio noleggio = noleggioDao.findNoleggioByAuto(auto);
		noleggio.setIsAutoDisponibile(false);
		update(noleggio);
	}
	
	public void setDisponibilitaAutoTrue (Auto auto) {
		Noleggio noleggio = noleggioDao.findNoleggioByAuto(auto);
		noleggio.setIsAutoDisponibile(true);
		update(noleggio);
	}
	
	public boolean setNoleggioByCliente (Noleggio noleggio) {
		Noleggio noleggio2 = noleggioDao.findNoleggioDisponibileByAuto(noleggio.getAuto(), noleggio.getDataInizio());
		
		if (noleggio2 == null) {
			noleggio.setIsAutoDisponibile(false);
			create(noleggio);
			return true;
		}
		
		return false;
		
	}
	
	public boolean cancellaNoleggiByUtente (Utente utente) {
		List <Noleggio> noleggi = noleggioDao.findNoleggiByUtente(utente);
		
		if (noleggi != null) {
			for (Noleggio noleggio : noleggi) {
				delete(noleggio);
			}
			return true;
		}
		
		return false;
	}
	
	public boolean cancellaNoleggioByIdNoleggio (Integer idNoleggio) {
		Noleggio noleggio = noleggioDao.findNoleggioByIdNoleggio(idNoleggio);
		
		if (noleggio != null) {
			delete(noleggio);
			return true;
		}
		
		return false;
	}
	
	
	public void createCalendario(CalendarioChiusure calendarioChiusure) {
		entityManager.getTransaction().begin();
		try {
			calendarioChiusureDao.create(calendarioChiusure);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void updateCalendario(CalendarioChiusure calendarioChiusure) {
		entityManager.getTransaction().begin();
		try {
			calendarioChiusureDao.update(calendarioChiusure);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void deleteCalendario(CalendarioChiusure calendarioChiusure) {
		entityManager.getTransaction().begin();
		try {
			if (calendarioChiusure!=null) {
				calendarioChiusureDao.delete(calendarioChiusure);
				entityManager.getTransaction().commit();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	
	
	public boolean cancellaNoleggiByDataInizio(Date dataChiusura) {
		List<Noleggio> noleggi = noleggioDao.findNoleggiByDataInizio(dataChiusura);
		boolean checkEliminato = false;
		if (noleggi!=null) {
			for (int i=0; i<noleggi.size(); i++) {
				Noleggio noleggio = noleggi.get(i);
				delete(noleggio);
			}
			checkEliminato = true;
		}
		CalendarioChiusure calendarioChiusure = new CalendarioChiusure (dataChiusura, dataChiusura);
	    createCalendario(calendarioChiusure);
		// TODO il caso in cui non c'è noleggio, deve inserirlo con isDisponibile false
		return checkEliminato;
	}
	
	public boolean cancellaNoleggiByDataInizioDataFine(Date dataInizioChiusura, Date dataFineChiusura) {
		List<Noleggio> noleggi = noleggioDao.findNoleggiByDataInizioDataFine(dataInizioChiusura, dataFineChiusura);
		boolean checkEliminato = false;
		if (noleggi!=null) {
			for (int i=0; i<noleggi.size(); i++) {
				Noleggio noleggio = noleggi.get(i);
				delete(noleggio);
			}
			checkEliminato = true;
		}
		CalendarioChiusure calendarioChiusure = new CalendarioChiusure (dataInizioChiusura, dataFineChiusura);
	    createCalendario(calendarioChiusure);
		// TODO il caso in cui non c'è noleggio, deve inserirlo con isDisponibile false
		return checkEliminato;

	}


		
	public boolean cancellaNoleggiByAuto (Auto auto) {
		List<Noleggio> noleggi = noleggioDao.findNoleggiByAuto(auto);
		boolean checkEliminato = false;
		Date dataCorrente = Date.valueOf(LocalDate.now());
		if (noleggi!=null) {
			for (int i=0; i<noleggi.size(); i++) {
				Noleggio noleggio = noleggi.get(i);
				if (dataCorrente.before(noleggio.getDataInizio()))
				delete(noleggio);
			}
			checkEliminato = true;
		}
		
		return checkEliminato;
	}
		
	public List<Integer> getAutoNonDisponibili (Date dataInizioNoleggio, Date dataFineNoleggio){
		List<Noleggio> noleggi = noleggioDao.findNoleggiByDataInizioDataFine(dataInizioNoleggio, dataFineNoleggio);
		List<Integer> idAutoNonDisponibili = new ArrayList<Integer>();
		
		for (Noleggio noleggio : noleggi) {
			idAutoNonDisponibili.add(noleggio.getAuto().getIdAuto());
		}
		
		return idAutoNonDisponibili;

	}
	
	
	
}
