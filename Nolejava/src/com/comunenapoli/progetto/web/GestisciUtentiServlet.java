package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;

/**
 * Servlet implementation class PromozioneServlet
 */
@WebServlet("/GestisciUtentiServlet")
public class GestisciUtentiServlet extends HttpServlet {
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
		
		String idUtente = request.getParameter("idUtente");
		
		
        String recipient = request.getParameter("recipient");

		
		String eliminaUtente = "";
		String promuoviUtente = "";
		String verificaUtente = "";
		
		String action = request.getParameter("action").toLowerCase();
		
		if (action.contains("elimina")) {
			eliminaUtente = action;
		}else if (action.contains("promuovi")){
			promuoviUtente = action;
		}else {
			verificaUtente = action;
		}

		
		
		BusinessLogicUtente businessLogicUtente = (BusinessLogicUtente)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_UTENTE);
		
		Utente utente = businessLogicUtente.getUtenteById(Integer.valueOf(idUtente));
		
		boolean isPromosso = false;
		boolean isRimosso = false;
		boolean isVerificato = false;
		
		if (promuoviUtente != null && !promuoviUtente.isEmpty() && utente.getRuoloUtente().getIdRuolo() == Costanti.ID_RUOLO_CLIENTE) {
			isPromosso = businessLogicUtente.updateRuolo(utente, Costanti.ID_RUOLO_STAFF);
		} else if (eliminaUtente != null && !eliminaUtente.isEmpty()){
			businessLogicUtente.deleteUtente(Integer.valueOf(idUtente));
			isRimosso = true;
		}else if (verificaUtente != null && !verificaUtente.isEmpty()) {
			isVerificato = true;
			businessLogicUtente.verificaUtente(utente, isVerificato);
		}
		
		if (isPromosso) {
			//TODO utente promosso
			System.out.println("utente promosso");
			request.setAttribute(Costanti.UTENTE_PROMOSSO, isPromosso);
			request.getRequestDispatcher("/EmailSendingServlet").include(request, response);

			
		}
		else if (isRimosso){
			//TODO utente rimosso
			System.out.println("utente rimosso");
			request.setAttribute(Costanti.UTENTE_RIMOSSO, isRimosso);
			request.getRequestDispatcher("/EmailSendingServlet").include(request, response);

		}
		else if (isVerificato) {
			System.out.println("utente verificato");
			request.setAttribute(Costanti.UTENTE_VERIFICATO, isVerificato);
			request.getRequestDispatcher("/EmailSendingServlet").include(request, response);
		}
		else {
			//TODO operazione non avvenuta
			System.out.println("operazione non avvenuta");
		}
	
	}
}