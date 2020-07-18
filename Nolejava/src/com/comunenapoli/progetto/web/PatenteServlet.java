package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicPatente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;

/**
 * Servlet implementation class PatenteServlet
 */
@WebServlet("/PatenteServlet")
public class PatenteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Last-modified", LocalDateTime.now().toString());
		response.setHeader("Cache-control", "no-store");
		
		BusinessLogicPatente businessLogicPatente = (BusinessLogicPatente) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_PATENTE);
		Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
		
		String dataScadenza = request.getParameter("dataScadenza");
		String numeroPatente = request.getParameter("numeroPatente");
		
		
		try {
			businessLogicPatente.operazioniPatente(dataScadenza, numeroPatente, utente);
			request.getRequestDispatcher("/NoleggioServlet").forward(request, response);
			System.out.println("sono in patente servlet");
//			ServletContext context = getServletContext();
//			RequestDispatcher requestVar = context.getNamedDispatcher("NoleggioServlet");
			
			//response.sendRedirect("/NoleggioServlet");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
	
		