<%@page import="com.comunenapoli.progetto.model.Utente"%>
<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>

<form action="/Nolejava/LogoutServlet" method="post">
     	<input type="submit" value="Logout">
 	</form>

<%
	Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
	Integer ruoloUtente = utente.getRuoloUtente().getIdRuolo();			
%>
	 	
 	
 	<br><br><br><br>
		<form action="/Nolejava/DashboardServlet" method="post">
<% 
		if (ruoloUtente==Costanti.ID_RUOLO_ADMIN) {
%>		
	    <input type="submit" name="action" value="Verifica utente">
<% 
		}
%>		    
     	<input type="submit" name="action" value="Gestisci utenti">
	    <input type="submit" name="action" value="Gestisci auto">  
<% 
		if (ruoloUtente==Costanti.ID_RUOLO_ADMIN) {
%>		     
   	    <input type="submit" name="action" value="Gestisci noleggi">
   	    <input type="submit" name="action" value="Calendario ferie">    
<% 
		}
%>	   	    		      	
 	</form>
 	
 	

</body>
</html>