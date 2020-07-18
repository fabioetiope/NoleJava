package com.comunenapoli.progetto.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.EntityManager;

import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.businessLogic.BusinessLogicRuolo;
import com.comunenapoli.progetto.businessLogic.RuoloDao;
import com.comunenapoli.progetto.businessLogic.UtenteDao;
import com.comunenapoli.progetto.model.Ruolo;
import com.comunenapoli.progetto.model.Utente;


public class Main {
	public static void main(String[] args) {
		EntityManager entityManager = EntityManagerUtils.apriConnessione();
		UtenteDao utenteDao = new UtenteDao(entityManager);
	    BusinessLogicUtente businessLogic = new BusinessLogicUtente(entityManager,utenteDao);
	    
	    BusinessLogicRuoloUtils.generaRuoli(entityManager);
	    
	    String username = "prova@gmail.com";
	    String password = "1dssjd";
	    String nome = "Francesco";
	    String cognome = "Boh";
	    LocalDate localDate = LocalDate.of(1993,05,19);
	    Date dataNascita = DataUtils.convertiFromLocalDate(localDate);
	    Ruolo ruolo = null;
		
	    Utente utente = new Utente(username,password,nome,cognome,dataNascita,ruolo);
	    businessLogic.create(utente);
	    
	    
	    Utente utenteLogin = businessLogic.login(username+"a", password);
	    if (utenteLogin!=null) {
	    	System.out.println("Sono utente registrato");
	    }
	    
	    boolean a = businessLogic.isNuovoUtente(utente);
	    System.out.println(a);
	    
	    boolean isVerificato = businessLogic.isVerificato(1);
	    System.out.println(isVerificato);
	    
	    boolean verificaUtente = businessLogic.verificaUtente(utente, true);
	    System.out.println(verificaUtente);
	    
	    isVerificato = businessLogic.isVerificato(1);
	    System.out.println(isVerificato);
	    	    
	    
	   businessLogic.updateRuolo(utente, 2);
	   
	   //businessLogic.deleteUtente(1);
	   

	    
	    
	}
}
