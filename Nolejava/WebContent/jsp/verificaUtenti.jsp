<%@page import="com.comunenapoli.progetto.model.Utente"%>
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
	
	
	
<%
	List <Utente> utentiNonVerificati = (List <Utente>) request.getAttribute(Costanti.LISTA_UTENTI_NON_VERIFICATI);
	Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
	Integer ruoloUtente = utente.getRuoloUtente().getIdRuolo();
%>
	<h1>Gestisci utente</h1>
	<table>
		<tr>
			<th>
			Id
			</th>
			<th>
			email
			</th>
			<th>
			Tipologia
			</th>
			<th>
			Azione
			</th>
		</tr>

		<tr>
<% 	if (utentiNonVerificati != null && !utentiNonVerificati.isEmpty()){
		for (Utente user : utentiNonVerificati){
%>
		<td>
		<%=user.getIdUtente()%>
		</td>
		<td>
		<%=user.getUsername()%>
		</td>
		<td>
		<%=user.getRuoloUtente().getNomeRuolo()%>
		</td>
		<td>
			<form action="/Nolejava/GestisciUtentiServlet" method="post">
				<input type="hidden" value= "<%=user.getIdUtente()%>" name="idUtente">
				<input type="submit" name="action" value="Promuovi utente">
       			
       			<%if (ruoloUtente == Costanti.ID_RUOLO_ADMIN ) { %>
       			<input type="submit" name="action" value="Verifica utente">
       			<input type="submit" name="action" value="Elimina utente">
       			<%} %>
	 		</form>
		</td>
	

	
	
<%
	}
%>	
	</tr>
	</table>
<%
}
%>


</body>
</html>