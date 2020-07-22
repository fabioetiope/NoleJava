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
	
	public List<Auto> getListaCompletaAuto(){
		List<Auto> listaAuto = autoDao.retrieve();
		return listaAuto;	
	}
	
	//lista di tutte le auto con filtri, senza controllo sulle date di noleggio
	public List<Auto> getListaAutoConFiltri(String marca, String modello, String tipologia){
		
//		marca = marca.toLowerCase();
//		modello = modello.toLowerCase();
//		tipologia = tipologia.toLowerCase();
		
		String sql = "";
		HashMap<String,String> parametriAuto = new HashMap<String,String>();
		int count = 0;
		if (marca!=null && !marca.isEmpty()) {
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
		if (modello!=null && !modello.isEmpty()) {
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
		if (tipologia!=null && !tipologia.isEmpty()) {
			if (count == 0) {
				sql = " where ";
			}
			else {
				sql += " and ";
			}
			count++;
			sql += " a.tipologiaAuto = :tipologiaAuto ";
			parametriAuto.put("tipologiaAuto", tipologia);
		}
		List<Auto> listaAuto = autoDao.findAutoByFilters(sql, parametriAuto);			
		return listaAuto;
	}
	

	public void updateAuto(Auto auto, Auto autoCorrente) {
		String marca = auto.getMarca();
		String modello = auto.getModello();
		String tipoCarburante = auto.getTipoCarburante();
		String tipologiaAuto = auto.getTipologiaAuto();
		String cambio = auto.getCambio();
		String colore = auto.getColore();
		String targa = auto.getTarga();
		String urlImg = auto.getUrlImg();
		Double cilindrata = auto.getCilindrata();
		Integer numeroPosti = auto.getNumeroPosti();
		Double prezzoPerGiorno = auto.getPrezzoPerGiorno();
		String marcaCorrente = autoCorrente.getMarca();
		String modelloCorrente = autoCorrente.getModello();
		String tipoCarburanteCorrente = autoCorrente.getTipoCarburante();
		String tipologiaAutoCorrente = autoCorrente.getTipologiaAuto();
		String cambioCorrente = autoCorrente.getCambio();
		String coloreCorrente = autoCorrente.getColore();
		String targaCorrente = autoCorrente.getTarga();
		String urlImgCorrente = autoCorrente.getUrlImg();
		Double cilindrataCorrente = autoCorrente.getCilindrata();
		Integer numeroPostiCorrente = autoCorrente.getNumeroPosti();
		Double prezzoPerGiornoCorrente = autoCorrente.getPrezzoPerGiorno();

		if (marca!=marcaCorrente) {
			auto.setMarca(marcaCorrente);
		}
		if (modello!=modelloCorrente) {
			auto.setModello(modelloCorrente);
		}
		if (cambio!=cambioCorrente) {
			auto.setCambio(cambioCorrente);
		}
		if (cilindrata!=cilindrataCorrente) {
			auto.setCilindrata(cilindrataCorrente);
		}
		if (colore!=coloreCorrente) {
			auto.setColore(coloreCorrente);
		}
		if (numeroPosti!=numeroPostiCorrente) {
			auto.setNumeroPosti(numeroPostiCorrente);
		}
		if (prezzoPerGiorno!=prezzoPerGiornoCorrente) {
			auto.setPrezzoPerGiorno(prezzoPerGiornoCorrente);
		}
		if (tipoCarburante!=tipoCarburanteCorrente) {
			auto.setTipoCarburante(tipoCarburanteCorrente);

		}
		if (tipologiaAuto!=tipologiaAutoCorrente) {
			auto.setTipologiaAuto(tipologiaAutoCorrente);
		}
		if (targa!=targaCorrente) {
			auto.setTarga(targaCorrente);
		}
		if (urlImg!=urlImgCorrente) {
			auto.setUrlImg(urlImgCorrente);		
		}

		update(auto);
	}
	
	public List<Auto> getAutoByMarca (String marca){
		return autoDao.findByMarca(marca);
	}


}
