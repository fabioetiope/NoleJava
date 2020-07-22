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
<title>Prenotazioni</title>
</head>
<body>
	<h1> Prenotazioni</h1>
<%
	Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);

	List<Noleggio> listaPrenotazioni = (List<Noleggio>) request.getSession().getAttribute(Costanti.NOLEGGI_UTENTE);

	if (listaPrenotazioni!=null && !listaPrenotazioni.isEmpty()){
%>
		<table>
			<tr>
				<th>id Noleggio</th>
				<th>Data Prenotazione</th>
				<th>Data Inizio Noleggio</th>
				<th>Data Fine Noleggio</th>	
				<th>Marca Auto</th>
				<th>Modello Auto</th>
				<th>operazione</th>		
					
			</tr>
		
<% 		
		for (int i=0; i<listaPrenotazioni.size();i++){
			
			Noleggio noleggioCorrente = listaPrenotazioni.get(i);
			Integer idNoleggio = noleggioCorrente.getIdNoleggio();
			Date dataPrenotazione = noleggioCorrente.getDataPrenotazione();
			Date dataInizio = noleggioCorrente.getDataInizio();
			Date dataFine = noleggioCorrente.getDataFine();
			String marca = noleggioCorrente.getAuto().getMarca();
			String modello = noleggioCorrente.getAuto().getModello();
			String operazione = "Cancella Noleggio";
%>	
			<tr>
				<td><%=idNoleggio%></td>	
				<td><%=dataPrenotazione%></td>
				<td><%=dataInizio%></td>				
				<td><%=dataFine%></td>	
				<td><%=marca%></td>
				<td><%=modello%></td>		
				<td>
					<form action="/Nolejava/PrenotazioniClientiServlet" method="POST">
						<input type="hidden" name="idNoleggio" value="<%=idNoleggio%>"> 
						<input type="hidden" value="<%=utente.getUsername()%>" name="recipient"/>
						<input type="submit" value=<%=operazione%>> 	 		     	
					</form>
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