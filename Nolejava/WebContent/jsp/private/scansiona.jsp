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
<title>Scansiona prenotazioni</title>

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
      
      
     <div id="container" class="containerQR">
      <h1 id="QrTitle">QR Code Scanner</h1>
		
      <a id="btn-scan-qr">
        <img src="https://dab1nmslvvntp.cloudfront.net/wp-content/uploads/2017/07/1499401426qr_icon.svg">
      <a/>

      <canvas hidden="" id="qr-canvas"></canvas>

      <div id="qr-result" hidden="">
      	<b>Numero prenotazione:</b> <span id="outputData"></span>
	     <form action="/Nolejava/scanQR" method="post">
	      	<input type="hidden" name="numeroPrenotazione" id="numeroPrenotazione" value="">
	      	<input class="btn btn-primary mt-3" type="submit" name="action" value="Cerca prenotazione">
	     </form>
      </div>
    </div>
    

	

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