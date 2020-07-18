<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>patente</title>
</head>
<body>
	
<!-- 	<form action="/Nolejava/PatenteServlet" method="post"> -->
<!-- 		<input type="date" name="dataScadenza"> -->
<!-- 		<input type="text" name="numeroPatente"> -->
<!-- 		<input type="submit" value="Crea patente"> -->
<!-- 	</form> -->
	
	<%	String numeroPatente = (String) request.getAttribute(Costanti.NUMERO_PATENTE);
	
 	%> 
	
	
	<form action="/Nolejava/PatenteServlet" method="post">
		<input type="date" name="dataScadenza">
		<input type="text" name="numeroPatente" value="<%=numeroPatente%>" <%if (!numeroPatente.isEmpty()){%>readonly<%}%> >
		<input type="submit" value="Conferma patente">
	</form>
	
</body>
</html>