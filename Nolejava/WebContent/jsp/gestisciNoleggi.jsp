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
<title>Gestisci Noleggi</title>
</head>
<body>
		<h1> Gestisci Noleggi</h1>
		
		<form action="/Nolejava/GestisciNoleggiServlet" method="post">
			<label>Ricerca per</label>
			<input list="ricerca" name="ricerca">           
			<datalist id="ricerca" name="ricerca">
				<option value="Username utente"></option>
				<option value="Marca auto"></option>
		  	</datalist>
		  	<input type="text" name="filtro" value="" required>
		  	<input type="submit" name="action" value="cerca per campo">
	  	</form>
	  	
	  	<br>
	  	<br>
	  	<br>
	  	<br>
	  	
	  	<form action="/Nolejava/GestisciNoleggiServlet" method="post">
	  		<input type="date" placeholder="Data inizio noleggio" name="dataInizio" required>
			<input type="date" placeholder="Data fine noleggio" name="dataFine" required>
	 		 <input type="submit" name="action" value="cerca per date">
	  	</form>
	  	
	  	<br>
	  	<br>
	  	<br>
	  	<br>
	  	<br>
	  	<br>
<%
	Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);

	List<Noleggio> listaNoleggi = (List<Noleggio>) request.getSession().getAttribute(Costanti.LISTA_COMPLETA_NOLEGGI);

	if (listaNoleggi!=null && !listaNoleggi.isEmpty()){
%>	
		
	
		<table>
			<tr>
				<th>id Noleggio</th>
				<th>Data Prenotazione</th>
				<th>Data Inizio Noleggio</th>
				<th>Data Fine Noleggio</th>	
				<th>Marca Auto</th>
				<th>Modello Auto</th>
				<th>Cliente</th>
<%
	if(utente.getRuoloUtente().getIdRuolo() == Costanti.ID_RUOLO_ADMIN){
%>
				
				<th>operazione</th>
<%
	}
%>	
					
			</tr>
		
<% 		
		for (int i=0; i<listaNoleggi.size();i++){
			
			Noleggio noleggioCorrente = listaNoleggi.get(i);
			Integer idNoleggio = noleggioCorrente.getIdNoleggio();
			Date dataPrenotazione = noleggioCorrente.getDataPrenotazione();
			Date dataInizio = noleggioCorrente.getDataInizio();
			Date dataFine = noleggioCorrente.getDataFine();
			String marca = noleggioCorrente.getAuto().getMarca();
			String modello = noleggioCorrente.getAuto().getModello();
			String username = noleggioCorrente.getUtente().getUsername();
			String operazione = "Cancella Noleggio";
%>	
			<tr>
				<td><%=idNoleggio%></td>	
				<td><%=dataPrenotazione%></td>
				<td><%=dataInizio%></td>				
				<td><%=dataFine%></td>	
				<td><%=marca%></td>
				<td><%=modello%></td>
				<td><%=username%></td>
				<td>
				
<%
	if(utente.getRuoloUtente().getIdRuolo() == Costanti.ID_RUOLO_ADMIN){
%>
					<form action="/Nolejava/GestisciNoleggiServlet" method="POST">
						<input type="hidden" name="idNoleggio" value="<%=idNoleggio%>"> 
						<input type="submit" name="action" value=<%=operazione%>> 	 		     	
					</form>
<%
	}
%>
				</td>
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