package com.comunenapoli.progetto.businessLogic;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.Ruolo;

public class BusinessLogicRuolo {
	
	private RuoloDao ruoloDao = null;
	private EntityManager entityManager = null;
	
	public BusinessLogicRuolo(RuoloDao ruoloDao, EntityManager entityManager) {
		this.ruoloDao = ruoloDao;
		this.entityManager = entityManager;
	}

	public RuoloDao getRuoloDao() {
		return ruoloDao;
	}

	public void setRuoloDao(RuoloDao ruoloDao) {
		this.ruoloDao = ruoloDao;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public void createRuolo(Ruolo ruolo) {
		entityManager.getTransaction().begin();
		try {
			ruoloDao.create(ruolo);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	
	
	

}
