package com.comunenapoli.progetto.utils;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.businessLogic.BusinessLogicRuolo;
import com.comunenapoli.progetto.businessLogic.RuoloDao;
import com.comunenapoli.progetto.model.Ruolo;

public class BusinessLogicRuoloUtils {
	
	public static void generaRuoli(EntityManager entityManager) { 
		RuoloDao ruoloDao = new RuoloDao(entityManager);
	    BusinessLogicRuolo businessLogicRuolo = new BusinessLogicRuolo(ruoloDao,entityManager);
	    Ruolo ruoloAdmin = new Ruolo("admin", 1);
	    Ruolo ruoloStaff = new Ruolo("staff", 2);
	    Ruolo ruoloCliente = new Ruolo("cliente", 3);
	    businessLogicRuolo.createRuolo(ruoloAdmin);
	    businessLogicRuolo.createRuolo(ruoloStaff);
	    businessLogicRuolo.createRuolo(ruoloCliente);
	}
}
