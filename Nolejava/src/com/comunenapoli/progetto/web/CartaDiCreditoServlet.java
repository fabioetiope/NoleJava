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

import com.comunenapoli.progetto.businessLogic.BusinessLogicCarta;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;


@WebServlet("/CartaDiCreditoServlet")
public class CartaDiCreditoServlet extends HttpServlet {
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
		
		BusinessLogicCarta businessLogicCarta = (BusinessLogicCarta) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_CARTA);
		Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
		
		String dataDiScadenza = request.getParameter("dataDiScadenza");
		String numeroCarta = request.getParameter("numeroCarta");
		String stringCVV = request.getParameter("CVV");
		
		Integer cvv;
		
		if(stringCVV == null || stringCVV.isEmpty()) {
			cvv = 0;
		}else {
			cvv= Integer.valueOf(stringCVV);
		}
		
		
		try {
			businessLogicCarta.operazioniCarta(dataDiScadenza, numeroCarta, cvv, utente);
			request.getRequestDispatcher("/NoleggioServlet").forward(request, response);
			
//			ServletContext context = getServletContext();
//			RequestDispatcher requestVar = context.getNamedDispatcher("NoleggioServlet");
			
			//response.sendRedirect("/NoleggioServlet");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}