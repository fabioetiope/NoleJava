package com.comunenapoli.progetto.businessLogic;

import java.sql.Date;
import java.text.ParseException;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.Patente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.DataUtils;


public class BusinessLogicPatente {
	
	private PatenteDao patenteDao = null;
	private EntityManager entityManager = null;
	
	public BusinessLogicPatente(PatenteDao patenteDao, EntityManager entityManager) {
		super();
		this.patenteDao = patenteDao;
		this.entityManager = entityManager;
	}
	

	public PatenteDao getPatenteDao() {
		return patenteDao;
	}


	public void setPatenteDao(PatenteDao patenteDao) {
		this.patenteDao = patenteDao;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public void create(Patente patente) {
		entityManager.getTransaction().begin();
		try {
			patenteDao.create(patente);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void update(Patente patente) {
		entityManager.getTransaction().begin();
		try {
			patenteDao.update(patente);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	//creiamo patente solo nel caso in cui l'utente non ne ha una associata e solo nel caso in cui non esiste già una con lo stesso numero
	public boolean creaPatente(Patente patente) {
		
		if(patenteDao.findPatenteByUtente(patente.getUtente()) == null && patenteDao.findPatenteByNumeroPatente(patente.getNumeroPatente()) == null) {
			create(patente);
			return true;
		}
		
		return false;
		
	}
	
	public Patente getPatenteByUtente(Utente utente) {
		return patenteDao.findPatenteByUtente(utente);
	}
	
	public boolean isPatenteValid(Date dataScadenza) throws Exception {
		boolean isDataValida = DataUtils.dataScadenza(dataScadenza);
		return isDataValida;
	}
	
	public void operazioniPatente (String dataScadenzaString, String numeroPatente, Utente utente) throws ParseException {
		
		Patente patente = patenteDao.findPatenteByNumeroPatente(numeroPatente);
		boolean isNuovaPatente = patente == null;	
		Date dataScadenza = DataUtils.convertiDataFromString(dataScadenzaString);
			
		if (isNuovaPatente) {
			patente = new Patente(numeroPatente, dataScadenza, utente);
			create(patente);
		}else {
			patente.setDataScadenza(dataScadenza);
			update(patente);
		}
		
		
	}


	public Integer responsoPatente(Utente utente) throws Exception {
		Patente patente = patenteDao.findPatenteByUtente(utente);
		boolean isPatenteValida = false;
		if (patente==null) {
			return -1;
		}
		else {
			Date dataScadenza = patente.getDataScadenza();
			isPatenteValida = isPatenteValid(dataScadenza);
			if (isPatenteValida) {
				return 1;
			}else {
				return 0;
			}
			
		}
	}
	

}
