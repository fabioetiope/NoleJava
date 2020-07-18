package com.comunenapoli.progetto.utils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.comunenapoli.progetto.businessLogic.BusinessLogicAuto;
import com.comunenapoli.progetto.businessLogic.BusinessLogicNoleggio;
import com.comunenapoli.progetto.model.Auto;

public class BusinessLogicAutoNoleggioUtils {

	public static List<Auto> filtroRicerca(Date dataInizioNoleggio, Date dataFineNoleggio, String tipologiaAuto, BusinessLogicAuto businessLogicAuto, BusinessLogicNoleggio businessLogicNoleggio) {
		List <Integer> idAutoNonDisponibili = businessLogicNoleggio.getAutoNonDisponibili(dataInizioNoleggio, dataFineNoleggio);
		List <Auto> autoDisponibili = businessLogicAuto.getAutoDisponibili(idAutoNonDisponibili);
		List <Auto> risultati = new ArrayList<Auto>();
		
		if (tipologiaAuto == null || tipologiaAuto.isEmpty() || tipologiaAuto.equals("")) {
			return autoDisponibili;
		}else {
			for (Auto auto: autoDisponibili) {
				if (auto.getTipologiaAuto().equals(tipologiaAuto)) {
					risultati.add(auto);
				}
			}
		}
		
		return risultati;
	}
	

//	public static List<Auto> filtroRicerca(Date dataInizioNoleggio, Date dataFineNoleggio, Integer numeroPostiAuto, Double prezzoAutoPerGiorno, String tipologiaAuto, String marcaAuto, String cambioAuto, BusinessLogicAuto businessLogicAuto, BusinessLogicNoleggio businessLogicNoleggio) {
//		List <Integer> idAutoNonDisponibili = businessLogicNoleggio.getAutoNonDisponibili(dataInizioNoleggio, dataFineNoleggio);
//		List <Auto> autoDisponibili = businessLogicAuto.getAutoDisponibili(idAutoNonDisponibili);
//		List <Auto> risultati = new ArrayList<Auto>();
//	
//		
//	}
	

}
