package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicAuto;
import com.comunenapoli.progetto.businessLogic.BusinessLogicCarta;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.businessLogic.BusinessLogicPatente;
import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.CalendarioChiusure;
import com.comunenapoli.progetto.model.CartaDiCredito;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Patente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

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
		//TODO filtro area solo admin e staff
		Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
		BusinessLogicUtente businessLogicUtente = (BusinessLogicUtente)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_UTENTE);
		List<Utente> utentiNonVerificati = businessLogicUtente.listaUtentiNonVerificati();
		request.setAttribute(Costanti.LISTA_UTENTI_NON_VERIFICATI, utentiNonVerificati);
		List<Utente> listaUtenti = businessLogicUtente.getListaUtenti();
		request.setAttribute(Costanti.LISTA_UTENTI, listaUtenti);
		
	
		String html = "/jsp/";
		String action = request.getParameter("action").toLowerCase();
		if (action.contains("utente")) {
			html += "verificaUtenti.jsp";
		}
		
		else if (action.contains("utenti")) {
			html += "gestisciUtenti.jsp";

		}
		
		else if (action.contains("auto")) {
			BusinessLogicAuto businessLogicAuto = (BusinessLogicAuto) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_AUTO);
			List <Auto> listaAuto = businessLogicAuto.getListaCompletaAuto();
			request.getSession().setAttribute(Costanti.LISTA_COMPLETA_AUTO, listaAuto);
			html += "gestisciAuto.jsp";
		}else if (action.contains("noleggi")) {
			BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);
			List <Noleggio> listaNoleggi = businessLogicNoleggio.getListaCompletaNoleggi();
			request.getSession().setAttribute(Costanti.LISTA_COMPLETA_NOLEGGI, listaNoleggi);
			html += "gestisciNoleggi.jsp";
			
		}else if (action.contains("calendario")) {
			BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);
			List <CalendarioChiusure> chiusure = businessLogicNoleggio.getChiusure();
			request.getSession().setAttribute(Costanti.LISTA_COMPLETA_CHIUSURE, chiusure);
			html += "calendario.jsp";
			
		}
		RequestDispatcher requestDispatcher; 
		requestDispatcher = request.getRequestDispatcher(html);
		requestDispatcher.forward(request, response);


	}

}

