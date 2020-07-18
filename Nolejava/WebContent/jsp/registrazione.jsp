<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Nolejava/registrazione" method="post">

	<input type="text" placeholder="nome" name="nome">

	<input type="text" placeholder="cognome" name="cognome">

	<input type="date" placeholder="data di nascita" name="dataNascita">

	<input type="text" placeholder="email" name="email">

	<input type="text" placeholder="password" name="password">

	<label>Selezionare casella in caso di registrazione Staff</label>
	<input type="checkbox" name="staff">

	<input type="submit" value="Registrati ora">

</form>

</body>
</html>