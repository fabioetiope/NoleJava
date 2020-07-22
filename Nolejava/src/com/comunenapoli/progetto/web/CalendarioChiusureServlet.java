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
import com.comunenapoli.progetto.model.CalendarioChiusure;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.DataUtils;

/**
 * Servlet implementation class CalendarioChiusureServlet
 */
@WebServlet("/CalendarioChiusureServlet")
public class CalendarioChiusureServlet extends HttpServlet {
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
				
			
			try {
			String dataInizioString = request.getParameter("dataInizio").toLowerCase();
			String dataFineString = request.getParameter("dataFine").toLowerCase();
			
			
			Date dataInizio = DataUtils.convertiDataFromString(dataInizioString);
			Date dataFine = DataUtils.convertiDataFromString(dataFineString);

			CalendarioChiusure calendario = new CalendarioChiusure (dataInizio, dataFine);
			
			businessLogicNoleggio.cancellaNoleggiByDataInizioDataFine(dataInizio, dataFine);
			
			
			
			List <CalendarioChiusure> chiusure = businessLogicNoleggio.getChiusure();
			request.getSession().setAttribute(Costanti.LISTA_COMPLETA_CHIUSURE, chiusure);
			request.getRequestDispatcher("/jsp/calendario.jsp").forward(request, response);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		
		
	}
	

}