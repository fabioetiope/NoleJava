package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicCarta;
import com.comunenapoli.progetto.businessLogic.BusinessLogicPatente;
import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.CartaDiCredito;
import com.comunenapoli.progetto.model.Patente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;

/**
 * Servlet implementation class NoleggioServlet
 */
@WebServlet("/NoleggioServlet")
public class NoleggioServlet extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Utente utente = (Utente) req.getSession().getAttribute(Costanti.USER_IN_SESSION);
		
		BusinessLogicPatente businessLogicPatente = (BusinessLogicPatente) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_PATENTE);
		BusinessLogicCarta businessLogicCarta = (BusinessLogicCarta) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_CARTA);
		
		Patente patente = businessLogicPatente.getPatenteByUtente(utente);
		CartaDiCredito cartaDiCredito = businessLogicCarta.getCartaByUtente(utente);
		
		String numeroPatente = "";
		
		
		if (patente == null) {
			req.setAttribute(Costanti.NUMERO_PATENTE, numeroPatente);
			req.getRequestDispatcher("/jsp/patente.jsp").forward(req, resp);
			
//			PrintWriter printWriter = resp.getWriter();
//			printWriter.println("<h1> Patente inesistente </h1>");
//			printWriter.flush();
//			printWriter.close();
		}else {
			try {
				if (businessLogicPatente.isPatenteValid(patente.getDataScadenza()) == true) {
					System.out.println("sono nell if");
					String numeroCarta = "";
					String cvv = "";
					String html = "";
					if (cartaDiCredito == null){
						System.out.println("sono qui");
						html = "/jsp/carta.jsp";
					}else {
						
						if (businessLogicCarta.isCartaValid(cartaDiCredito.getDataDiScadenza()) == true) {
						  req.getRequestDispatcher("/jsp/concludiNoleggio.jsp").forward(req, resp);
						}else {
							html = "/jsp/carta.jsp";
							numeroCarta= cartaDiCredito.getNumeroCarta();
							cvv = cartaDiCredito.getCvv().toString();
						}
					}
					req.setAttribute(Costanti.NUMERO_CARTA, numeroCarta);
					req.setAttribute(Costanti.CVV_CARTA, cvv);
					req.getRequestDispatcher(html).forward(req, resp);
				}else {
					numeroPatente = patente.getNumeroPatente();
					req.setAttribute(Costanti.NUMERO_PATENTE, numeroPatente);
					req.getRequestDispatcher("/jsp/patente.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
