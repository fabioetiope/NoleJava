package com.comunenapoli.progetto.businessLogic;

import java.sql.Date;
import java.text.ParseException;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.model.CartaDiCredito;
import com.comunenapoli.progetto.model.Patente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.DataUtils;

public class BusinessLogicCarta {
	
	private CartaDiCreditoDao cartaDiCreditoDao = null;
	private EntityManager entityManager = null;
	
	public BusinessLogicCarta(CartaDiCreditoDao cartaDiCreditoDao, EntityManager entityManager) {
		super();
		this.cartaDiCreditoDao = cartaDiCreditoDao;
		this.entityManager = entityManager;
	}
	

	public CartaDiCreditoDao getCartaDiCreditoDao() {
		return cartaDiCreditoDao;
	}


	public void setCartaDiCreditoDao(CartaDiCreditoDao cartaDiCreditoDao) {
		this.cartaDiCreditoDao = cartaDiCreditoDao;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	public void create(CartaDiCredito cartaDiCredito) {
		entityManager.getTransaction().begin();
		try {
			cartaDiCreditoDao.create(cartaDiCredito);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void update(CartaDiCredito cartaDiCredito) {
		entityManager.getTransaction().begin();
		try {
			cartaDiCreditoDao.update(cartaDiCredito);
			entityManager.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	//creiamo patente solo nel caso in cui l'utente non ne ha una associata e solo nel caso in cui non esiste già una con lo stesso numero
	public boolean creaCarta(CartaDiCredito carta) {
		
		if(cartaDiCreditoDao.findCartaByUtente(carta.getUtente()) == null && cartaDiCreditoDao.findCartaByNumeroCarta(carta.getNumeroCarta()) == null) {
			create(carta);
			return true;
		}
		
		return false;
		
	}
	
	public CartaDiCredito getCartaByUtente(Utente utente) {
		return cartaDiCreditoDao.findCartaByUtente(utente);
	}
	
	public boolean isCartaValid(Date dataScadenza) throws Exception {
		boolean isDataValida = DataUtils.dataScadenza(dataScadenza);
		return isDataValida;
	}
	
	
	public void operazioniCarta (Integer idCarta, String dataDiScadenzaString, String numeroCarta, Integer cvv, Utente utente) throws ParseException {
		CartaDiCredito cartaDiCredito = cartaDiCreditoDao.findCartaByIdCarta(idCarta);
		
		boolean isNuovaCarta = cartaDiCredito == null;
		Date dataDiScadenza = DataUtils.convertiDataFromString(dataDiScadenzaString);
		if (isNuovaCarta) {
			cartaDiCredito = new CartaDiCredito(numeroCarta, cvv, dataDiScadenza, utente);
			create(cartaDiCredito);
		}else {
			cartaDiCredito.setDataDiScadenza(dataDiScadenza);
			cartaDiCredito.setCvv(cvv);
			cartaDiCredito.setNumeroCarta(numeroCarta);
			update(cartaDiCredito);
		}
		
	}


	public Integer responsoCarta(Utente utente) throws Exception {
		CartaDiCredito carta = getCartaByUtente(utente);
		boolean isCartaValid = false;
		if (carta==null) {
			return -1;
		}
		else {
			Date dataScadenza = carta.getDataDiScadenza();
			isCartaValid = isCartaValid(dataScadenza);
			if (isCartaValid) {
				return 1;
			}else {
				return 0;
			}
		}
	
	}
	
		

}
