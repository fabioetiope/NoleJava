package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Ruolo;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.DataUtils;


@WebServlet("/registrazione")
public class RegistrazioneServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascitaString = request.getParameter("dataNascita");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String staff = request.getParameter("staff");
		Integer effettuaRegistrazione = effettuaRegistrazione(nome,cognome,dataNascitaString,email,password,staff);
		System.out.println("utente registrato");
		if (effettuaRegistrazione == Costanti.REGISTRAZIONE_VALIDA) {
			//TODO registrazione avvenuta con successo, attendi conferma
		}
		else if (effettuaRegistrazione == Costanti.REGISTRAZIONE_FALLITA_ETA) {
			//TODO registrazione non avvenuta, sei minorenne, rimanda in homepage
		}
		else if (effettuaRegistrazione == Costanti.REGISTRAZIONE_FALLITA_UTENTE_GIA_ESISTENTE) {
			//TODO registrazione non avvenuta, utente esistente, rimanda al login
		}
		else {
			//TODO errore generico, rimanda alla homepage senza errore
		}
	
	}
	
	
	public Integer effettuaRegistrazione (String nome, String cognome, String dataNascitaString, String email, String password, String staff) {
		BusinessLogicUtente businessLogicUtente = (BusinessLogicUtente)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_UTENTE);
		boolean checkNome = nome!=null && !nome.equals("");
		boolean checkCognome = cognome!=null && !cognome.equals("");
		boolean checkDataNascita = dataNascitaString!=null && !dataNascitaString.equals("");
		boolean checkEmail = email!=null && !email.equals("");
		boolean checkPassword = password!=null && !password.equals("") && password.length()>=8;
		boolean isStaff = staff!=null;
		boolean checkCondizioni = checkNome && checkCognome && checkDataNascita && checkEmail && checkPassword;		
		Integer checkRegistrazione = Costanti.ERRORE_GENERICO;
		try {
			Date dataNascita = DataUtils.convertiDataFromString(dataNascitaString);
			if (checkCondizioni && DataUtils.dataDiNascita(dataNascita)) {
				Ruolo ruoloUtente = new Ruolo();
				if (isStaff) {
					ruoloUtente.setIdRuolo(Costanti.ID_RUOLO_STAFF);
					ruoloUtente.setNomeRuolo(Costanti.RUOLO_STAFF);
				}
				else {
					ruoloUtente.setIdRuolo(Costanti.ID_RUOLO_CLIENTE);
					ruoloUtente.setNomeRuolo(Costanti.RUOLO_CLIENTE);
				}
				Utente utente = new Utente(email,password,nome,cognome,dataNascita,ruoloUtente);
				checkRegistrazione = businessLogicUtente.registrazione(utente);
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkRegistrazione;
	}
	
}


