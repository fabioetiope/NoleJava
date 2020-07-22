package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicAuto;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.DataUtils;

/**
 * Servlet implementation class GestisciNoleggiServlet
 */
@WebServlet("/GestisciNoleggiServlet")
public class GestisciNoleggiServlet extends HttpServlet {
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
		
		BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);

		String stringIdNoleggio = request.getParameter("idNoleggio");
		String action = request.getParameter("action").toLowerCase();
		
		boolean cancellazione = false;
		
		if(action.contains("cancella")) {
			Integer IdNoleggio = Integer.valueOf(stringIdNoleggio);
			cancellazione = businessLogicNoleggio.cancellaNoleggioByIdNoleggio(IdNoleggio);
			
		} else if (action.contains("campo")) {
			String ricerca = request.getParameter("ricerca").toLowerCase();
			String filtro = request.getParameter("filtro").toLowerCase();
			
			if (ricerca.contains("utente")) {
				BusinessLogicUtente businessLogicUtente = (BusinessLogicUtente) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_UTENTE);
				Utente utente = businessLogicUtente.getUtenteByUsername(filtro);
				List <Noleggio> listaNoleggi = businessLogicNoleggio.getNoleggiByUtente(utente);
				request.getSession().setAttribute(Costanti.LISTA_COMPLETA_NOLEGGI, listaNoleggi);
				request.getRequestDispatcher("/jsp/gestisciNoleggi.jsp").forward(request, response);

				
			}else if (ricerca.contains("marca")) {
				BusinessLogicAuto businessLogicAuto = (BusinessLogicAuto) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_AUTO);
				List <Auto> listaAutoByMarca = businessLogicAuto.getAutoByMarca(filtro);
				List <Noleggio> listaNoleggi = businessLogicNoleggio.getNoleggiByListaAuto(listaAutoByMarca);
				request.getSession().setAttribute(Costanti.LISTA_COMPLETA_NOLEGGI, listaNoleggi);
				request.getRequestDispatcher("/jsp/gestisciNoleggi.jsp").forward(request, response);


			}
			
		}else if (action.contains("date")){
			
			try {
			String dataInizioString = request.getParameter("dataInizio").toLowerCase();
			String dataFineString = request.getParameter("dataFine").toLowerCase();
			
			
			Date dataInizio = DataUtils.convertiDataFromString(dataInizioString);
			Date dataFine = DataUtils.convertiDataFromString(dataFineString);

			
			List <Noleggio> listaNoleggi = businessLogicNoleggio.getNoleggiByDataIniziDataFine(dataInizio, dataFine);
			request.getSession().setAttribute(Costanti.LISTA_COMPLETA_NOLEGGI, listaNoleggi);
			request.getRequestDispatcher("/jsp/gestisciNoleggi.jsp").forward(request, response);

			
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			
		}
		
		if (cancellazione == true) {
			Integer IdNoleggio = Integer.valueOf(stringIdNoleggio);
			List <Noleggio> listaNoleggi = businessLogicNoleggio.getListaCompletaNoleggi();
			request.getSession().setAttribute(Costanti.LISTA_COMPLETA_NOLEGGI, listaNoleggi);
			request.getRequestDispatcher("/jsp/gestisciNoleggi.jsp").forward(request, response);
		}
		
		
	}
	

}
