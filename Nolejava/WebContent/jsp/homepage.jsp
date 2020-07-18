<%@page import="java.sql.Date"%>
<%@page import="com.comunenapoli.progetto.model.Utente"%>
<%@page import="java.util.List"%>
<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@page import="com.comunenapoli.progetto.model.Auto"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	

	 
	
	<% String tipologia = (String) request.getSession().getAttribute(Costanti.TIPOLOGIA_AUTO_SCELTA);
	if (tipologia == null || tipologia.isEmpty()){
		tipologia = "Scegli una tipologia";
	}
	
	
	String bottoneNoleggio="Noleggio";
	Utente utente = (Utente)request.getSession().getAttribute(Costanti.USER_IN_SESSION);
	if(utente == null){
		bottoneNoleggio="Dettagli";
	%>
	<form action="/Nolejava/jsp/registrazione.jsp" method="post">
       	<input type="submit" value="Registrati">
	 </form>
	<form action="/Nolejava/jsp/login.jsp" method="post">
       	<input type="submit" value="Login">
	 </form>
	 
	<%}else{
		if (utente.getRuoloUtente().getIdRuolo()==Costanti.ID_RUOLO_CLIENTE){
	%>
	
	<form action="/Nolejava/jsp/profiloCliente.jsp" method="post">
       	<input type="submit" value="Profilo-<%=utente.getRuoloUtente().getNomeRuolo()%>">
	 </form>
	 	
	 	<%}else{ %>
			<form action="/Nolejava/jsp/dashboard.jsp" method="post">
		       	<input type="submit" value="Profilo-<%=utente.getRuoloUtente().getNomeRuolo()%>">
			 </form>
		<%}
	}%>
	
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<form action="/Nolejava/HomepageServlet" method="post">
		<input type="date" placeholder="Data inizio noleggio" name="stringaDataInizioNoleggio" value="<%=request.getSession().getAttribute(Costanti.DATA_INIZIO_NOLEGGIO)%>" required>
		<input type="date" placeholder="Data fine noleggio" name="stringaDataFineNoleggio" value="<%=request.getSession().getAttribute(Costanti.DATA_FINE_NOLEGGIO)%>" required>
		
		<select name="tipologiaAuto" id="tipologiaAuto">
	        <option disabled selected><%=tipologia%></option>
	        <option value="berlina">Berlina</option>
	        <option value="utilitaria">Utilitaria</option>
	        <option value="suv">Suv</option>
      	</select>
		
       	<input type="hidden" value="1" name="formCompilato">
       	<input type="submit" value="Ricerca">
	 </form>
	
	<%List<Auto> automobili = (List<Auto>)request.getSession().getAttribute(Costanti.LISTA_COMPLETA_AUTO); 
	for(Auto auto : automobili){%> 
	     
           <tr> 
                <td><%=auto.getMarca()%></td> 
	                <form action="/Nolejava/AutoServlet" method="post">
	                	<input type="hidden" value="<%=auto.getIdAuto()%>" name="bottone">
	                	<input type="submit" value="<%=bottoneNoleggio%>">
	                </form>
	<%}%>
	
			</tr> 

</body>
</html>