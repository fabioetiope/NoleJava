package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.businessLogic.BusinessLogicPatente;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Patente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;

/**
 * Servlet implementation class ScanQRServlet
 */
@WebServlet("/scanQR")
public class ScanQRServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {	
		BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);
		String html = "";
		
		String numeroPrenotazione = request.getParameter("numeroPrenotazione");
		System.out.println("Numero prenotazione: " + numeroPrenotazione);
		
		Noleggio noleggio = null;
		
		if (numeroPrenotazione != null && !numeroPrenotazione.isEmpty()) {
			noleggio = businessLogicNoleggio.getNoleggioByNumeroPrenotazione(numeroPrenotazione);
		}
		
		
		if (noleggio != null) {
			request.getSession().setAttribute(Costanti.NOLEGGIO_DA_CONFERMARE, noleggio);
			
			BusinessLogicPatente businessLogicPatente = (BusinessLogicPatente) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_PATENTE);
			Patente patente = businessLogicPatente.getPatenteByUtente(noleggio.getUtente());
			request.getSession().setAttribute(Costanti.PATENTE_CLIENTE, patente);
			
			html = "/jsp/private/confermanoleggio.jsp";
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher(html);
			requestDispatcher.forward(request, response);
		}else {
			String messaggioNoleggioNonTrovato = "Non esistono noleggi con numero di prenotazione: " + numeroPrenotazione;
			request.getSession().setAttribute(Costanti.MESSAGGIO_NOLEGGIO_NON_TROVATO, messaggioNoleggioNonTrovato);
			html = "/jsp/private/noleggionontrovato.jsp";
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher(html);
			requestDispatcher.forward(request, response);
		}
		

	}

}
