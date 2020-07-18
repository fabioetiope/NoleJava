<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>carta di credito</title>
</head>
<body>
	
	

	<%	String numeroCarta = (String) request.getAttribute(Costanti.NUMERO_CARTA);
		String cvv =  (String) request.getAttribute(Costanti.CVV_CARTA);
		
		
	%>
	
	
	<form action="/Nolejava/CartaDiCreditoServlet" method="post">
		<input type="date" name="dataDiScadenza">
		<input type="text" name="numeroCarta" value="<%=numeroCarta%>" <%if (!numeroCarta.isEmpty()){%>readonly<%}%> >
		<input type="number" name="CVV" value="<%=cvv%>" <%if (!numeroCarta.isEmpty()){%>readonly<%}%>>
		<input type="submit" value="Crea carta">
	</form>
</body>
</html>