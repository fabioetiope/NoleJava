package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicCarta;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.businessLogic.BusinessLogicPatente;
import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.CartaDiCredito;
import com.comunenapoli.progetto.model.Noleggio;
import com.comunenapoli.progetto.model.Patente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;

/**
 * Servlet implementation class ConludiNoleggioServlet
 */
@WebServlet("/ConcludiNoleggioServlet")
public class ConcludiNoleggioServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);
 
		Utente utente = (Utente) req.getSession().getAttribute(Costanti.USER_IN_SESSION);
		Auto auto = (Auto) req.getSession().getAttribute(Costanti.AUTO_IN_SESSION);
		Date dataInizioNoleggio = (Date) req.getSession().getAttribute(Costanti.DATA_INIZIO_NOLEGGIO);
		Date dataFineNoleggio = (Date) req.getSession().getAttribute(Costanti.DATA_FINE_NOLEGGIO);
		
		Calendar currenttime = Calendar.getInstance();
		Date dataPrenotazione =  new Date((currenttime.getTime()).getTime());
		
		Noleggio noleggio = new Noleggio(dataPrenotazione, dataInizioNoleggio,  dataFineNoleggio,  utente,  auto);
		
		businessLogicNoleggio.setNoleggioByCliente(noleggio);
		
		req.getRequestDispatcher("/jsp/noleggioEffettuato.jsp").forward(req, resp);
		
		
	}
}






