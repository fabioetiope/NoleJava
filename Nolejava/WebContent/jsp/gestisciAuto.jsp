<%@page import="java.sql.Date"%>
<%@page import="com.comunenapoli.progetto.model.Utente"%>
<%@page import="com.comunenapoli.progetto.model.Auto"%>
<%@page import="java.util.List"%>
<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Gestisci Auto</h1>
	
	<h2>Aggiungi Auto</h2>
	<a href='jsp/inserisciauto.jsp'> Add auto</a>
	
	<h2>Lista Auto</h2>
	
	
<%
		Date dataInizioNoleggio = (Date) request.getSession().getAttribute(Costanti.DATA_INIZIO_NOLEGGIO);
		Date dataFineNoleggio = (Date) request.getSession().getAttribute(Costanti.DATA_FINE_NOLEGGIO);
		String tipologiaAuto = (String) request.getSession().getAttribute(Costanti.TIPOLOGIA_AUTO_SCELTA);
		String marcaAuto = (String) request.getSession().getAttribute(Costanti.MARCA_AUTO_SCELTA);
		String modelloAuto = (String) request.getSession().getAttribute(Costanti.MODELLO_AUTO_SCELTA);

		if (tipologiaAuto==null || tipologiaAuto.isEmpty()) {
			tipologiaAuto = "Scegli tipologia auto";
		}
		if (marcaAuto==null) marcaAuto = "";
		if (modelloAuto == null) modelloAuto = "";
		
%>	
	
	
	<br>
	<br>
	<form action="/Nolejava/GestisciAutoServlet" method="post">
		<input type="date" placeholder="Data inizio noleggio" name="dataInizio" value="<%=dataInizioNoleggio%>" required>
		<br>
		<input type="date" placeholder="Data fine noleggio" name="dataFine" value="<%=dataFineNoleggio%>" required>
		<br>
		<select name="tipologiaAuto" id="tipologia">
			<option disabled selected><%=tipologiaAuto%></option>
			<option value="berlina">Berlina</option>
			<option value="utilitaria">Utilitaria</option>
			<option value="suv">Suv</option>
		</select> 
		<br>
		<input type="text" name="marcaAuto" value="<%=marcaAuto%>">
		<br>
		<input type="text" name="modelloAuto" value="<%=modelloAuto%>">
		<br>
		<input name="action" type="submit" value="Ricerca">
	</form>
	<br>
	<br>

<%
	List <Auto> listaAuto = (List <Auto>) request.getSession().getAttribute(Costanti.LISTA_COMPLETA_AUTO);
	Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
	
	if (listaAuto!=null && !listaAuto.isEmpty()){
%>

	<table>
		<tr>
			<th>idAuto</th>
			<th>Marca</th>
			<th>Modello</th>	
			<th>Cilindrata</th>
<%		
	if (utente.getRuoloUtente().getIdRuolo() == Costanti.ID_RUOLO_ADMIN){
%>			
			<th>Operazione</th>	
<%
	}
%>		
		</tr>

<% 	
	for (int i=0; i<listaAuto.size();i++){
	
		Auto autoCorrente = listaAuto.get(i);
		Integer idAuto = autoCorrente.getIdAuto();
		String marca = autoCorrente.getMarca();
		String modello = autoCorrente.getModello();
		Double cilindrata = autoCorrente.getCilindrata();
%>


	<tr>
		<td><%=idAuto%></td>	
		<td><%=marca%></td>		
		<td><%=modello%></td>	
		<td><%=cilindrata%></td>
<%		
	if (utente.getRuoloUtente().getIdRuolo() == Costanti.ID_RUOLO_ADMIN){

%>			
		<td>
			<form action="/Nolejava/GestisciAutoServlet" method="POST">
					<input type="hidden" name="idAuto" value="<%=idAuto%>"> 
					<input type="submit" name="action" value="Modifica">
					<input type="submit" name="action" value="Cancella">
			</form>
		<td>  	
<%
	}
%>
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