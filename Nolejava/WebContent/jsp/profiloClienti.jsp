<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/Nolejava/LogoutServlet" method="post">
       	<input type="submit" value="Logout">
	 </form>
	 
	 
	 <form action="/Nolejava/ProfiloClienteServlet" method="post">		    
     	<input type="submit" name="action" value="Aggiorna dati personali">   
	    <input type="submit" name="action" value="Aggiorna dati carta">     
     	<input type="submit" name="action" value="Gestisci prenotazioni">    	  	   	    		      	
 	</form>

</body>
</html>