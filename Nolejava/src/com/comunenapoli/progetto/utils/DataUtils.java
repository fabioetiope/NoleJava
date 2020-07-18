package com.comunenapoli.progetto.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DataUtils {
	
	public static Date convertiFromLocalDate (LocalDate localDate) {
		Date date = Date.valueOf(localDate);
		return date;
	}
	
	public static Date convertiDataFromString(String dataString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = formatter.parse(dataString);
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
	    return date;
	}
	
	
	public static String convertiDataFromSQLDate(Date dataSql) {
		String date = (String) new SimpleDateFormat("dd-MM-yyyy").format(dataSql);
		return date;
	}
	
	public static boolean dataDiNascita(Date sqlDataNascita) throws ParseException {
		java.util.Date dataNascita = new java.util.Date(sqlDataNascita.getTime());
		java.util.Date dateOggi = new java.util.Date();
		if (dateOggi.getYear() - dataNascita.getYear() < 18) {
			return false;
		}else if (dateOggi.getYear() - dataNascita.getYear() > 18) {
			return true;
		}else if (dateOggi.getYear() - dataNascita.getYear() == 18) {
			if (dateOggi.getMonth() > dataNascita.getMonth()) {
				return true;
			}else if (dateOggi.getMonth() < dataNascita.getMonth()) {
				return false;
			}else if (dateOggi.getMonth() == dataNascita.getMonth()) {
				if (dateOggi.getDay() >= dataNascita.getDay()) {
					return true;
				}else if (dateOggi.getDay() < dataNascita.getDay()) {
					return false;
				}
			}
		
		}
		return false;
	}
	
//	public static boolean dataScadenza(Date sqlDataScadenza) throws Exception {
//		java.util.Date dateOggi = new java.util.Date();
//	    //java.util.Date dataScadenza = dataScadenzaCorrente;
//		java.util.Date dataScadenza = new java.util.Date(sqlDataScadenza.getTime());
//		Integer confrontaAnno = dateOggi.getYear() - dataScadenza.getYear();
//		if (confrontaAnno<0) {
//			return false;
//		}
//		else if (confrontaAnno>0) {
//			return true;
//		}
//		else if (confrontaAnno==0) {
//			boolean mesePassato = dateOggi.getMonth() > dataScadenza.getMonth();
//			boolean meseCorrente = dateOggi.getMonth() == dataScadenza.getMonth();
//			boolean meseFuturo = dateOggi.getMonth() < dataScadenza.getMonth();
//
//			if (mesePassato) {
//				return false;
//			}
//			else if (meseCorrente) {
//				boolean giornoPassato = dateOggi.getMonth() >= dataScadenza.getMonth();
//				boolean giornoFuturo = dateOggi.getMonth() < dataScadenza.getMonth();
//				if (giornoPassato) {
//					return false;
//				}
//				else if (giornoFuturo) {
//					return true;
//				}
//			} else if (meseFuturo){
//				return true;
//			}
//		}
//		return false;
//	}
	
	public static boolean dataScadenza(Date sqlDataScadenza) throws Exception {
		java.util.Date dateOggi = new java.util.Date();
		java.util.Date dataScadenza = new java.util.Date(sqlDataScadenza.getTime());
		
		if(dataScadenza.before(dateOggi)) {
			return false;
		}
		
		if(dataScadenza.after(dateOggi)) {
			return true;
		}
		
		if(dataScadenza.equals(dateOggi)) {
			return true;
		}
		
		return false;
	}

}
