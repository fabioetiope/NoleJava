<%@page import="com.comunenapoli.progetto.model.Patente"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.comunenapoli.progetto.model.CalendarioChiusure"%>
<%@page import="java.sql.Date"%>
<%@page import="com.comunenapoli.progetto.model.Noleggio"%>
<%@page import="java.util.List"%>
<%@page import="com.comunenapoli.progetto.model.Utente"%>
<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conferma noleggio</title>

<!-- <meta name="viewport" -->
<!-- 	content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->

<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;"/>

	
<link rel="icon" type="image/png" href="/Nolejava/images/favicon.png"/>

<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900"
	rel="stylesheet">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"
	rel="stylesheet">

<link rel="stylesheet" href="/Nolejava/css/style-dashboard.css">

<link rel="stylesheet" href="/Nolejava/css/my-style-dashboard.css">

<script src="https://rawgit.com/sitepoint-editors/jsqrcode/master/src/qr_packed.js"></script>


</head>
<body>

	 <div class="wrapper d-flex align-items-stretch">

    <!-- INIZIO sidebar -->
    <nav id="sidebar">
      <div class="p-4 pt-3">
        <img class="logo-dashboard" src="/Nolejava/images/nolejava-vector.svg">


        <ul class="list-unstyled components mb-5">
          <li>
            <a href="/Nolejava/notificheDashboard">Dashboard</a>
          </li>
          <li>
            <a href="/Nolejava">Homepage</a>
          </li>
          <li>
            <form action="/Nolejava/logoutServlet" method="post">
              <input id="logout" type="submit" value="Logout">
            </form>
          </li>
        </ul>

        <div class="footer">
          <p>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            Copyright ©
            <script>document.write(new Date().getFullYear());</script>
          <p>All rights reserved | NoleJava</p>
          <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          </p>
        </div>

      </div>
    </nav>
    <!-- FINE sidebar -->



    <!-- Page Content  p-4 p-md-5-->
    <div id="content" class="p-4 p-md-3">

      <!-- INIZIO NAVBAR -->
      <nav id="navbar" style="min-height: 60px; border-radius: 0.25rem;" class="navbar navbar-expand-lg navbar-light sticky">

        <div class="container-fluid ">       
          <p id="paragrafo-dashboard" style="margin-right: auto !important;">DASHBOARD ADMIN</p>


          <!-- INIZIO NAVBAR-MOBILE -->
          <div id="navbar-mobile">


            <p style="margin-left:auto !important; margin-right:0 !important">DASHBOARD ADMIN</p>



            <button class="btn btn-primary d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
            aria-label="Toggle navigation">
            <i class="fa fa-bars"></i>
          </button>

          <img class="d-inline-block d-lg-none ml-auto" src="/Nolejava/images/nolejava-vector.svg" alt="" style="float: right !important; margin-left: 100px !important; width: 40%; height: 40px !important;">

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="nav navbar-nav">
                <li class="nav-item active">
                  <a class="nav-link" href="/Nolejava/notificheDashboard">Dashboard</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="/Nolejava">Homepage</a>
                </li>
                <li class="nav-item">
                  <form action="/Nolejava/logoutServlet" method="post">
                    <input id="logout" type="submit" value="Logout">
                  </form>
                </li>
              </ul>
            </div>



          </div>
          <!-- FINE NAVBAR-MOBILE -->

          <button type="button" id="sidebarCollapse" class="ml-auto btn btn-primary">
            <i class="fa fa-bars"></i>
            <span class="sr-only">Toggle Menu</span>
          </button>

        </div>
      </nav>
      <!-- FINE NAVBAR -->

	<!------- QUI Tabella-------->

<!--       <h3 style="color: #FFFFFFCC;">Calendario ferie</h3> -->
      
<!--       <form action="/Nolejava/calendarioChiusureServlet" method="post"> -->
<!--         <div class="row"> -->

<!--           <div class="col-md-3 col-lg-3 col-xl-3 mt-2 d-flex flex-column"> -->
<!--             <label for="datainizio">Data inizio</label> -->
<!--             <input class="form-control" type="date" placeholder="Data inizio chiusura" name="datainizio" required> -->

<!--           </div> -->
<!--           <div class="col-md-3 col-lg-3 col-xl-3 mt-2 d-flex flex-column"> -->
<!--             <label for="dataFine">Data fine</label> -->
<!--             <input class="form-control" type="date" placeholder="Data fine chiusura" name="datafine" required> -->

<!--           </div> -->
<!--           <div class="col-md-3 col-lg-3 col-xl-3 mt-2 d-flex flex-column"> -->
<!--             <input class="form-control btn btn-primary mt-auto" type="submit" name="action" value="Inserisci chiusura"> -->

<!--           </div> -->
          
<!-- 		</div> -->
<!--       </form> -->

<%	Noleggio noleggio = (Noleggio) request.getSession().getAttribute(Costanti.NOLEGGIO_DA_CONFERMARE);
	Patente patente = (Patente) request.getSession().getAttribute(Costanti.PATENTE_CLIENTE);
    if (noleggio != null && patente != null) {
    	String numeroPrenotazione = noleggio.getNumeroPrenotazione();
    	
    	String nomeCliente = noleggio.getUtente().getNome();
    	String cognomeCliente = noleggio.getUtente().getCognome();
    	Date dataDiNascitaDate = noleggio.getUtente().getDataNascita();
		DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
		String dataDiNascita = df.format(dataDiNascitaDate);
		String numeroPatente = patente.getNumeroPatente();
		Date dataDiScadenzaPatenteDate = patente.getDataScadenza();
		String dataDiScadenzaPatente = df.format(dataDiScadenzaPatenteDate);
		
		Date dataPrenotazioneDate = noleggio.getDataPrenotazione();
		String dataPrenotazione = df.format(dataPrenotazioneDate);
		Date dataInizioDate = noleggio.getDataInizio();
		String dataInizio = df.format(dataInizioDate);
		Date dataFineDate = noleggio.getDataFine();
		String dataFine = df.format(dataFineDate);
		Boolean isScansionato = noleggio.getIsScansionato();
		String autoRitirata = "";
		if (isScansionato == true){
			autoRitirata = "Sì";
		}else {
			autoRitirata = "No";
		}
		String marcaAuto = noleggio.getAuto().getMarca();
		String modelloAuto = noleggio.getAuto().getModello();
		String targa = noleggio.getAuto().getTarga();
		
		java.sql.Date dataOggi = new java.sql.Date(new java.util.Date().getTime());

%>
      <h3 style="color: #FFFFFFCC;">Numero prenotazione: <%=numeroPrenotazione%></h3>
      
      <form action="/Nolejava/confermaNoleggio" method="post">
         <div class="row">
         	<div class="col-md-3 col-lg-3 col-xl-3 mt-2 d-flex flex-column">
         		 <h5 class="mt-3"style="color: #FFFFFFCC;">Dati Cliente</h5>
         		 <label for="nome">Nome</label>
         		 <input class="form-control" type="text" name="nome" value="<%=nomeCliente%>" readonly>
         		 <label for="cognome">Cognome</label>
         		 <input class="form-control" type="text" name="cognome" value="<%=cognomeCliente%>" readonly>
         		 <label for="dataDiNascita">Data di nascita</label>
         		 <input class="form-control" type="text" name="dataDiNascita" value="<%=dataDiNascita%>" readonly>
         		 <label for="numeroPatente">Numero patente</label>
         		 <input class="form-control" type="text" name="numeroPatente" value="<%=numeroPatente%>" readonly>
         		 <label for="dataDiScadenzaPatente">Scadenza patente</label>
         		 <input class="form-control" type="text" name="dataDiScadenzaPatente" value="<%=dataDiScadenzaPatente%>" readonly>
         		 
         		 <h5 class="mt-5" style="color: #FFFFFFCC;">Dati Noleggio</h5>
         		 <label for="dataPrenotazione">Data prenotazione</label>
         		 <input class="form-control" type="text" name="dataPrenotazione" value="<%=dataPrenotazione%>" readonly>
         		 <label for="dataInizio">Data consegna auto</label>
         		 <input class="form-control" type="text" name="dataInizio" value="<%=dataInizio%>" readonly>
         		 <label for="dataFine">Data fine noleggio</label>
         		 <input class="form-control" type="text" name="dataFine" value="<%=dataFine%>" readonly>
         		 <label for="autoRitirata">Auto ritirata</label>
         		 <input class="form-control" type="text" name="autoRitirata" value="<%=autoRitirata%>" readonly>
         		 <label for="marcaAuto">Marca Auto</label>
         		 <input class="form-control" type="text" name="marcaAuto" value="<%=marcaAuto%>" readonly>
         		 <label for="modelloAuto">Modello Auto</label>
         		 <input class="form-control" type="text" name="modelloAuto" value="<%=modelloAuto%>" readonly>
         		 <label for="targa">Targa Auto</label>
         		 <input class="form-control" type="text" name="targa" value="<%=targa%>" readonly>
         		 
<%
		if (noleggio.getIsScansionato() == true) {
%>
				<h4 class="mt-5 mb-5 text-danger">L'auto è già stata ritirata</h4>
<%		
		}else if (dataInizioDate.after(dataOggi)) {
%>
				<h4 class="mt-5 mb-5 text-danger">La data di consegna dell'auto non è oggi</h4>
<%		
		}else {
%>
				<input class="form-control btn btn-primary mt-5 mb-5" type="submit" name="action" value="Conferma noleggio">
				
<%
		}
%>         		 
         		 
         	</div>
      	 </div>
      </form>
            
<%
    }
%>
    

	

    <!------- FINE Tabella-------->

    <footer id="footer" style="height: 50px; background-color: rgb(36, 37, 38);">

      <div class="footer" style="text-align: center">
        <p>
          <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          Copyright ©
          <script>document.write(new Date().getFullYear());</script>
          <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
          All rights reserved | NoleJava
        </p>
      </div>

    </footer>

  </div>


  </div>

  <script src="/Nolejava/js/jquery.min.js"></script>
  <script src="/Nolejava/js/popper.js"></script>
  <script src="/Nolejava/js/bootstrap.min.js"></script>
  <script src="/Nolejava/js/main-dashboard.js"></script>
  <script src="/Nolejava/js/qrCodeScanner.js"></script>

</body>
</html>