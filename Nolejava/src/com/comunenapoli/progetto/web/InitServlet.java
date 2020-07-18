package com.comunenapoli.progetto.web;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.comunenapoli.progetto.businessLogic.AutoDao;
import com.comunenapoli.progetto.businessLogic.BusinessLogicAuto;
import com.comunenapoli.progetto.businessLogic.BusinessLogicCarta;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.businessLogic.BusinessLogicPatente;
import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.businessLogic.CalendarioChiusureDao;
import com.comunenapoli.progetto.businessLogic.CartaDiCreditoDao;
import com.comunenapoli.progetto.businessLogic.NoleggioDao;
import com.comunenapoli.progetto.businessLogic.PatenteDao;
import com.comunenapoli.progetto.businessLogic.UtenteDao;
import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.utils.BusinessLogicRuoloUtils;
import com.comunenapoli.progetto.utils.BusinessLogicUtenteUtils;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.EntityManagerUtils;

@WebServlet(value="/iS", loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		
		EntityManager entityManager = EntityManagerUtils.apriConnessione();
		
		UtenteDao utenteDao = new UtenteDao(entityManager);
	    BusinessLogicUtente businessLogicUtente = new BusinessLogicUtente(entityManager,utenteDao);
	    
	    PatenteDao patenteDao = new PatenteDao(entityManager);
	    BusinessLogicPatente businessLogicPatente = new BusinessLogicPatente(patenteDao,entityManager);
	    
	    AutoDao autoDao = new AutoDao(entityManager);
	    BusinessLogicAuto businessLogicAuto = new BusinessLogicAuto (autoDao, entityManager);
	    
	    CartaDiCreditoDao cartaDiCreditoDao = new CartaDiCreditoDao(entityManager);
	    BusinessLogicCarta businessLogicCarta = new BusinessLogicCarta (cartaDiCreditoDao, entityManager);
	    
	    NoleggioDao noleggioDao = new NoleggioDao(entityManager);
	    CalendarioChiusureDao calendarioChiusureDao = new CalendarioChiusureDao (entityManager);
	    BusinessLogicNoleggio businessLogicNoleggio = new BusinessLogicNoleggio (noleggioDao, calendarioChiusureDao, entityManager);
	    
	    
		getServletContext().setAttribute(Costanti.CHIAVE_SERVLET, entityManager);		
		getServletContext().setAttribute(Costanti.CHIAVE_UTENTE_DAO, utenteDao);
		getServletContext().setAttribute(Costanti.BUSINESS_LOGIC_UTENTE, businessLogicUtente);
		//getServletContext().setAttribute(Costanti.BUSINESS_LOGIC_RUOLO, businessLogicRuolo);
		getServletContext().setAttribute(Costanti.BUSINESS_LOGIC_AUTO, businessLogicAuto);
		getServletContext().setAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO, businessLogicNoleggio);
		getServletContext().setAttribute(Costanti.BUSINESS_LOGIC_PATENTE, businessLogicPatente);
		getServletContext().setAttribute(Costanti.BUSINESS_LOGIC_CARTA, businessLogicCarta);

		
		BusinessLogicRuoloUtils.generaRuoli(entityManager);
		BusinessLogicUtenteUtils.creaAdmin(entityManager);
		
		///Popolo tabella auto
		Auto auto = new Auto ("berlina", "bmw", "M3", "manuale", 3500.0, "nero", 5, "DB854RT", "benzina", 350.0, "gijrog");
		Auto auto2 = new Auto ("berlina", "mercedes", "ClasseC", "automatico", 3600.0, "bianco", 5, "VH854RT", "diesel", 330.0, "gijrog");
		Auto auto3 = new Auto ("utilitaria", "fiat", "panda", "manuale", 1200.0, "rosso", 5, "Dh432", "benzina", 100.0, "gijrog");

		businessLogicAuto.create(auto);
		businessLogicAuto.create(auto2);
		businessLogicAuto.create(auto3);

		
	}
	
	
	public void destroy(){	
		EntityManager entityManager = (EntityManager) getServletContext().getAttribute(Costanti.CHIAVE_SERVLET);	
		EntityManagerUtils.chiudiConnessione(entityManager);
	}
	
 
}