package com.comunenapoli.progetto.web;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
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


@WebServlet("/OperazioniAutoServlet")
public class OperazioniAutoServlet extends HttpServlet {

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
		BusinessLogicAuto businessLogicAuto = (BusinessLogicAuto) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_AUTO);
		String stringIdAuto = request.getParameter("idAuto");
		String modello = request.getParameter("modello");
		String marca = request.getParameter("marca");
		String stringCilindrata = request.getParameter("cilindrata");
		String stringNumeroPosti = request.getParameter("numeroposti");
		String tipoCarburante = request.getParameter("tipocarburante");
		String tipologiaAuto = request.getParameter("tipologiaauto");
		String cambio = request.getParameter("cambio");
		String colore = request.getParameter("colore");
		String targa = request.getParameter("targa");
		String stringPrezzoPerGiorno = request.getParameter("prezzopergiorno");
		String urlImg = request.getParameter("imgurl");
		String action = request.getParameter("action").toLowerCase();
		String html = "";
		
		Double cilindrata = Double.valueOf(stringCilindrata);
		Integer numeroPosti = Integer.valueOf(stringNumeroPosti);
		Double prezzoPerGiorno = Double.valueOf(stringPrezzoPerGiorno);
		
		marca = marca.toLowerCase();
		modello = modello.toLowerCase();
		tipologiaAuto = tipologiaAuto.toLowerCase();
		
		boolean isHiddenEmpty = action==null || action.isEmpty() || action.equals("");
		if (action.contains("aggiungi")) {
				//TODO settare qui i campi e fare il parse
				Auto auto = new Auto(tipologiaAuto, marca, modello, cambio, cilindrata, colore, numeroPosti, targa, tipoCarburante, prezzoPerGiorno, urlImg);
				businessLogicAuto.create(auto);			
				html = "/jsp/gestisciAuto.jsp";
		}
		
		else if (action.contains("modifica") && stringIdAuto!=null && !stringIdAuto.isEmpty()) {
			Integer idAuto = Integer.parseInt(stringIdAuto);
			Auto auto = businessLogicAuto.getAutobyIdAuto(idAuto);
			Auto autoCorrente = new Auto(tipologiaAuto,marca, modello, cambio, cilindrata,colore, numeroPosti, 
					targa, tipoCarburante, prezzoPerGiorno, urlImg);
			businessLogicAuto.updateAuto(auto,autoCorrente);
			html = "/jsp/gestisciAuto.jsp";
	}
					
			RequestDispatcher requestDispatcher; 
			requestDispatcher = request.getRequestDispatcher(html);
			requestDispatcher.forward(request, response);

	}

}

