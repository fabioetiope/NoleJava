package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicAuto;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.model.Auto;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.BusinessLogicAutoNoleggioUtils;
import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.DataUtils;


@WebServlet("/HomepageServlet")
public class HomepageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//String idAuto = req.getParameter("bottone");
		
		List <Auto> listaCompletaAuto;
		
		Utente utente = (Utente) req.getSession().getAttribute(Costanti.USER_IN_SESSION);
		BusinessLogicNoleggio businessLogicNoleggio = (BusinessLogicNoleggio) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_NOLEGGIO);
		BusinessLogicAuto businessLogicAuto = (BusinessLogicAuto) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_AUTO);

		String hidden = req.getParameter("formCompilato");
		
		if (hidden == null || hidden.isEmpty() || hidden == "" || hidden.equals("")) {
			listaCompletaAuto = businessLogicAuto.getListaCompletaAuto();
			req.getSession().setAttribute(Costanti.LISTA_COMPLETA_AUTO, listaCompletaAuto);
			req.getRequestDispatcher("/jsp/homepage.jsp").forward(req, resp);
		}else {
			try {
			String stringDataInizioNoleggio = req.getParameter("stringaDataInizioNoleggio");
			String stringaDataFineNoleggio = req.getParameter("stringaDataFineNoleggio");
			//String stringNumeroPostiAuto = req.getParameter("stringNumeroPostiAuto");
			//String stringPrezzoAutoPerGiorno = req.getParameter("stringPrezzoAutoPerGiorno");
			
			////////////////////////////////////////////////////////////////////////////
			
			Date dataInizioNoleggio = DataUtils.convertiDataFromString(stringDataInizioNoleggio);
			Date dataFineNoleggio = DataUtils.convertiDataFromString(stringaDataFineNoleggio);
			
			req.getSession().setAttribute(Costanti.DATA_INIZIO_NOLEGGIO, dataInizioNoleggio);
			req.getSession().setAttribute(Costanti.DATA_FINE_NOLEGGIO, dataFineNoleggio);

			
//			Integer numeroPostiAuto;
//			if (stringNumeroPostiAuto.isEmpty() || stringNumeroPostiAuto.equals("")) {
//				numeroPostiAuto = null;
//			}else {
//				numeroPostiAuto = Integer.valueOf(stringNumeroPostiAuto);
//			}
//			
//			Double prezzoAutoPerGiorno;
//			if (stringPrezzoAutoPerGiorno.isEmpty() || stringPrezzoAutoPerGiorno.equals("")) {
//				prezzoAutoPerGiorno = null;
//			}else {
//				prezzoAutoPerGiorno = Double.valueOf(stringPrezzoAutoPerGiorno);
//			}
			
			String tipologiaAuto = req.getParameter("tipologiaAuto");
			//String marcaAuto = req.getParameter("marcaAuto");
			//String cambioAuto = req.getParameter("cambioAuto");
			
			req.getSession().setAttribute(Costanti.TIPOLOGIA_AUTO_SCELTA, tipologiaAuto);
			//BusinessLogicAutoNoleggioUtils.filtroRicerca(dataInizioNoleggio, dataFineNoleggio, numeroPostiAuto, prezzoAutoPerGiorno, tipologiaAuto, marcaAuto, cambioAuto, businessLogicAuto, businessLogicNoleggio);
			List <Auto> risultati = BusinessLogicAutoNoleggioUtils.filtroRicerca(dataInizioNoleggio, dataFineNoleggio, tipologiaAuto, businessLogicAuto, businessLogicNoleggio);
			
			req.getSession().setAttribute(Costanti.LISTA_COMPLETA_AUTO, risultati);
			//TODO ricarica pagina
			req.getRequestDispatcher("/jsp/homepage.jsp").forward(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}



		}
		
	}
}
