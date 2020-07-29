<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.comunenapoli.progetto.model.Utente"%>
<%@page import="java.sql.Date"%>
<%@page import="com.comunenapoli.progetto.utils.Costanti"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	Utente utente = (Utente) request.getSession().getAttribute(Costanti.USER_IN_SESSION);
	Integer idUtente = utente.getIdUtente();
	String nome = utente.getNome();
	String cognome = utente.getCognome();
	Date dataNascitaUtente = utente.getDataNascita();
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    String dataNascita = df.format(dataNascitaUtente);
	String email = utente.getUsername();
	String password = utente.getPassword();	
	String voce1 = "";
	String voce2 = "";
	String link = "/Nolejava/jsp/";
	String link1 = link;
	String link2 = link;
	if(utente == null) {
	    voce1 = "Registrati";
	    link1 += "registrazione.jsp";
	    voce2 = "Accedi";
	    link2 += "login.jsp";
	 }
	 else{
	    voce2 = "Logout";
	    link2 = "/Nolejava/logoutServlet";
	    if (utente.getRuolo().getId()==Costanti.ID_RUOLO_CLIENTE){
		   voce1 = "Profilo";
	       link1 += "privata/profilocliente.jsp";   	
	    } else {
	    	 voce1 = "Dashboard";
	         link1 = "/Nolejava/notificheDashboard";
	    }
	 }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>NoleJava - Dati personali</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="icon" type="image/png" href="/Nolejava/images/favicon.png"/>

    <link href="https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="/Nolejava/ss/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="/Nolejava/css/animate.css">

    <link rel="stylesheet" href="/Nolejava/css/owl.carousel.min.css">
    <link rel="stylesheet" href="/Nolejava/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="/Nolejava/css/magnific-popup.css">

    <link rel="stylesheet" href="/Nolejava/css/aos.css">

    <link rel="stylesheet" href="/Nolejava/css/ionicons.min.css">

    <link rel="stylesheet" href="/Nolejava/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="/Nolejava/css/jquery.timepicker.css">


    <link rel="stylesheet" href="/Nolejava/css/flaticon.css">
    <link rel="stylesheet" href="/Nolejava/css/icomoon.css">
    <link rel="stylesheet" href="/Nolejava/css/style.css">
</head>

<body>


	<!-- INIZIO nav-->
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="/Nolejava/">Nole<span>java</span></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>
			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="/Nolejava/"
						class="nav-link">Home</a></li>
					<li class="nav-item"><a href="<%=link%>about.jsp" class="nav-link">Chi
							siamo</a></li>
					<li class="nav-item"><a href="<%=link%>dovesiamo.jsp"
						class="nav-link">Dove siamo</a></li>
					<li class="nav-item"><a href="<%=link%>contact.jsp" class="nav-link">Contattaci</a></li>
					<li class="nav-item"><a href="<%=link1%>" class="nav-link"><%=voce1%></a></li>
					<li class="nav-item"><a href="<%=link2%>" class="nav-link"><%=voce2%></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- FINE nav -->

    <!-- INIZIO intestazione -->
    <section class="hero-wrap hero-wrap-2 js-fullheight" style="background-image: url('/Nolejava/images/bg_2.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
            <div class="row no-gutters slider-text js-fullheight align-items-end justify-content-start">
                <div class="col-md-9 ftco-animate pb-5">
                    <p class="breadcrumbs"><span class="mr-2"><a href="/Nolejava/">Home <i
                  class="ion-ios-arrow-forward"></i></a></span> <span>Dati personali <i
                class="ion-ios-arrow-forward"></i></span></p>
                    <h1 class="mb-3 bread">Dati personali</h1>
                </div>
            </div>
        </div>
    </section>
    <!-- FINE intestazione -->

    <!-- INIZIO form -->
    <section class="ftco-section contact-section">
        <div class="container">

            <div class="row block-9 justify-content-center mb-5">
                <div class="col-md-8 mb-md-5">
                    <h2 class="text-center">Dati personali</h2>
                    <form action="/Nolejava/datiPersonaliServlet" method="POST" class="bg-light p-5 contact-form">
                    		<input type="hidden" name="idutente" value="<%=idUtente%>">
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Nome</span>
                                    </div>
                                    <input type="text" class="form-control" name="nome" value="<%=nome%>" aria-label="nome" aria-describedby="basic-addon1" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="basic-addon1">Cognome</span>
                                    </div>
                                    <input type="text" class="form-control" name="cognome" value="<%=cognome%>" aria-label="cognome" aria-describedby="basic-addon1" required>
                                </div>
                            </div>              
                            <div class="form-group">
                                <div class="input-group mb-3">
                                <div class="input-group-prepend">
	                                    <span class="input-group-text">
	                                        <i class="fa fa-fw fa-birthday-cake"></i>
	                                    </span>
                                </div>     
                                    <input type="date" class="form-control" name="datanascita" value="<%=dataNascita%>" aria-label="datanascita" aria-describedby="basic-addon1" required>
                                </div>
                            </div>                        
                        <div class="form-group">
                            <div class="input-group mb-3">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="basic-addon1">@</span>
                                </div>
                                <input type="email" class="form-control" name="email"  aria-label="email" value="<%=email%>" placeholder="Inserisci email" aria-describedby="basic-addon1" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group mb-3">
                                <input type="password" class="form-control" name="password" value="" aria-label="password" placeholder="Inserisci una password di almeno 8 caratteri" id="input-pwd" pattern=".{8,12}" required>
                                <div class="input-group-append">
                                    <span class="input-group-text toggle-password">
                                        <i class="fa fa-fw fa-eye"></i>
                                    </span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Modifica profilo" class="form-control btn btn-primary">
                        </div>
                    </form>    
                </div>
            </div>
        </div>
    </section>
    <!-- FINE form -->


    	<jsp:include page="/jsp/footer.jsp"></jsp:include>



    <!-- loader -->
    <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
      <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
      <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
        stroke="#F96D00" /></svg></div>

    <script src="/Nolejava/js/jquery.min.js"></script>
    <script src="/Nolejava/js/jquery-migrate-3.0.1.min.js"></script>
    <script src="/Nolejava/js/popper.min.js"></script>
    <script src="/Nolejava/js/bootstrap.min.js"></script>
    <script src="/Nolejava/js/jquery.easing.1.3.js"></script>
    <script src="/Nolejava/js/jquery.waypoints.min.js"></script>
    <script src="/Nolejava/js/jquery.stellar.min.js"></script>
    <script src="/Nolejava/js/owl.carousel.min.js"></script>
    <script src="/Nolejava/js/jquery.magnific-popup.min.js"></script>
    <script src="/Nolejava/js/aos.js"></script>
    <script src="/Nolejava/js/jquery.animateNumber.min.js"></script>
    <script src="/Nolejava/js/bootstrap-datepicker.js"></script>
    <script src="/Nolejava/js/jquery.timepicker.min.js"></script>
    <script src="/Nolejava/js/scrollax.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
    <script src="/Nolejava/js/google-map.js"></script>
    <script src="/Nolejava/js/main.js"></script>
    <script src="/Nolejava/js/script.js"></script>
    <script src="https://use.fontawesome.com/b9bdbd120a.js"></script>
    <script src="/Nolejava/js/bootstrap-datetimepicker.min.js"></script>

</body>

</html>

</body>
</html>