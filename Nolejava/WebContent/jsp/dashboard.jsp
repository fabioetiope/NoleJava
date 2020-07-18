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
	
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<form action="/Nolejava/DashboardServlet" method="post">
       	<input type="submit" name="dashboard" value="Verifica utenti">
       	<input type="submit" name="dashboard" value="Aggiungi auto">
	</form>
	
	
	

</body>
</html>