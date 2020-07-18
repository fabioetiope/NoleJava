package com.comunenapoli.progetto.businessLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.Auto;

public class BusinessLogicAuto {
	
	private AutoDao autoDao = null;
	private EntityManager entityManager = null;
	
	public BusinessLogicAuto(AutoDao autoDao, EntityManager entityManager) {
		super();
		this.autoDao = autoDao;
		this.entityManager = entityManager;
	}

	public AutoDao getAutoDao() {
		return autoDao;
	}

	public void setAutoDao(AutoDao autoDao) {
		this.autoDao = autoDao;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void create(Auto auto) {
		entityManager.getTransaction().begin();
		try {
			autoDao.create(auto);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void update(Auto auto) {
		entityManager.getTransaction().begin();
		try {
			autoDao.update(auto);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void deleteAuto(Integer idAuto) {
		entityManager.getTransaction().begin();
		try {
			Auto auto = autoDao.findByIdAuto(idAuto);
			if (auto != null) {
				autoDao.delete(auto);
				entityManager.getTransaction().commit();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public Auto getAutobyIdAuto (Integer idAuto) {
		return autoDao.findByIdAuto(idAuto);
	}
	
	
	public List<Auto> getAutoNonDisponibili (List <Integer> idAutoNonDisponibili){
		List <Auto> autoNonDisponibili = new ArrayList<Auto>();
		if (idAutoNonDisponibili != null && !idAutoNonDisponibili.isEmpty()){

			for (Integer id : idAutoNonDisponibili) {
				Auto auto = autoDao.findByIdAuto(id);
				autoNonDisponibili.add(auto);
			}
		}
			return autoNonDisponibili;
	}
	
	public List<Auto> getAutoDisponibili (List <Integer> idAutoNonDisponibili){
		List <Auto> listaCompletaAuto = autoDao.retrieve();
		
		if (idAutoNonDisponibili != null && !idAutoNonDisponibili.isEmpty() && listaCompletaAuto !=null && !listaCompletaAuto.isEmpty()){
			List <Auto> autoNonDisponibili = getAutoNonDisponibili(idAutoNonDisponibili);

			listaCompletaAuto.removeAll(autoNonDisponibili);
		}
		
		return listaCompletaAuto;
	}
	
	public List<Auto> getListaCompletaAuto (){
		return autoDao.retrieve();
	}
	
	//lista di tutte le auto con filtri, senza controllo sulle date di noleggio
	public List<Auto> getListaAutoConFiltri(String marca, String modello, String tipologia){
		String sql = "";
		HashMap<String,String> parametriAuto = new HashMap<String,String>();
		int count = 0;
		if (marca!=null) {
			if (count == 0) {
				sql = " where ";
			}
			else {
				sql += " and ";
			}
			count++;
			sql += " a.marca = :marca ";
			parametriAuto.put("marca", marca);
		}
		if (modello!=null) {
			if (count == 0) {
				sql = " where ";
			}
			else {
				sql += " and ";
			}
			count++;
			sql += " a.modello = :modello ";
			parametriAuto.put("modello", modello);
		}
		if (tipologia!=null) {
			if (count == 0) {
				sql = " where ";
			}
			else {
				sql += " and ";
			}
			count++;
			sql += " a.tipologia = :tipologia ";
			parametriAuto.put("tipologia", tipologia);
		}
		List<Auto> listaAuto = autoDao.findAutoByFilters(sql, parametriAuto);
		return listaAuto;
	}
	

}
