package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.utils.Costanti;

/**
 * Servlet implementation class confermaNoleggioServlet
 */
@WebServlet("/confermaNoleggio")
public class ConfermaNoleggioServlet extends HttpServlet {
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
		Noleggio noleggio = (Noleggio) request.getSession().getAttribute(Costanti.NOLEGGIO_DA_CONFERMARE);
		businessLogicNoleggio.confermaNoleggio(noleggio);
		
		String messaggioNoleggioConfermato = "Noleggio con numero di prenotazione " + noleggio.getNumeroPrenotazione() + " confermato con successo."
				+ "\n"
				+ "\n"
				+ "Consegna le chiavi dell'auto con targa " + noleggio.getAuto().getTarga() + " al cliente.";
		request.getSession().setAttribute(Costanti.MESSAGGIO_NOLEGGIO_CONFERMATO, messaggioNoleggioConfermato);
		String html = "/jsp/private/noleggioconfermato.jsp";
		RequestDispatcher requestDispatcher; 
		requestDispatcher = request.getRequestDispatcher(html);
		requestDispatcher.forward(request, response);
		

	}

}