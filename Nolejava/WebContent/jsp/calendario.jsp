<%@page import="com.comunenapoli.progetto.model.CalendarioChiusure"%>
<%@page import="java.sql.Date"%>
<%@page import="com.comunenapoli.progetto.model.Noleggio"%>
<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@page import="com.comunenapoli.progetto.model.Utente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calendario</title>
</head>
<body>
		<h1> Calendario</h1>
		
		
	  	
	  	<form action="/Nolejava/CalendarioChiusureServlet" method="post">
	  		<input type="date" placeholder="Data inizio chiusura" name="dataInizio" required>
			<input type="date" placeholder="Data fine chiusura" name="dataFine" required>
	 		<input type="submit" name="action" value="Inserisci chiusura">
	  	</form>
	  	
	  	<br>
	  	<br>
	  	<br>
	  	<br>
	  	<br>
	  	<br>
<%

	List<CalendarioChiusure> listaChiusure = (List<CalendarioChiusure>) request.getSession().getAttribute(Costanti.LISTA_COMPLETA_CHIUSURE);

	if (listaChiusure!=null && !listaChiusure.isEmpty()){
%>	
		
	
		<table>
			<tr>
				<th>Data Inizio Chiusura</th>
				<th>Data Fine Chiusura</th>				
			</tr>
		
<% 		
		for (int i=0; i<listaChiusure.size();i++){
			
			CalendarioChiusure chiusura = listaChiusure.get(i);
			Date dataInizio = chiusura.getDataInizioChiusura();
			Date dataFine = chiusura.getDataFineChiusura();
			
%>	
			<tr>
				<td><%=dataInizio%></td>				
				<td><%=dataFine%></td>	
			</tr>
				
				
<% 			
	}
%>
		</table>

<% 			
	}
%>

</body>
</html>