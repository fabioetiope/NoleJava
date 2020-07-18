package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;


@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Utente utente = (Utente) req.getSession().getAttribute(Costanti.USER_IN_SESSION);
		BusinessLogicUtente businessLogicUtente = (BusinessLogicUtente)getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_UTENTE);
		
		List <Utente> utentiNonVerificati = businessLogicUtente.listaUtentiNonVerificati();
		
		req.setAttribute(Costanti.LISTA_UTENTI_NON_VERIFICATI, utentiNonVerificati);

		
		
		
		String bottone = req.getParameter("dashboard");
		
		if (bottone.contains("utenti")) {
			req.getRequestDispatcher("jsp/verificaUtenti.jsp").forward(req, resp);
		}
		
	}

}
