package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicAuto;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.DataUtils;

/**
 * Servlet implementation class PrenotazioniClientiServlet
 */
@WebServlet("/PrenotazioniClientiServlet")
public class PrenotazioniClientiServlet extends HttpServlet {

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
		Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
		
		BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);

		String stringIdNoleggio = request.getParameter("idNoleggio");
		String recipient = request.getParameter("recipient");
		
		Integer IdNoleggio = Integer.valueOf(stringIdNoleggio);
		
		boolean cancellazione = businessLogicNoleggio.cancellaNoleggioByIdNoleggio(IdNoleggio);
		
		if (cancellazione == true) {
			request.setAttribute(Costanti.CANCELLAZIONE_AVVENUTA, cancellazione);
			request.getRequestDispatcher("/EmailSendingServlet").include(request, response);
		}
		
		
	}
	
	
	

}
