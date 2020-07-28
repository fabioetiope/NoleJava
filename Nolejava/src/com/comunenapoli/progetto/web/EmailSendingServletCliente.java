package com.comunenapoli.progetto.web;

import java.io.IOException;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.utils.Costanti;
import com.comunenapoli.progetto.utils.EmailUtility;
 

@WebServlet("/emailSendingServletCliente")
public class EmailSendingServletCliente extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	
	private String host;
    private String port;
    private String user;
    private String pass;
 
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // reads form fields
    	String recipient = "";

    	
    	Boolean isCancellato = (Boolean) request.getAttribute(Costanti.CANCELLAZIONE_AVVENUTA);
    	Boolean registrazioneAvvenuta = (Boolean) request.getAttribute(Costanti.REGISTRAZIONE_AVVENUTA_EMAIL);
    	
        String subject = "";
        String content = "";
 
        String resultMessage = "";
        if (isCancellato!=null && isCancellato) {
        	recipient = request.getParameter("recipient");
        	subject = "NoleJava - Noleggio eliminato con successo";
            content = "Il tuo noleggio è stato cancellato";
        }
        
        if (registrazioneAvvenuta!=null && registrazioneAvvenuta) {
        	recipient = request.getParameter("email");
        	subject = "NoleJava - Registrazione avvenuta con successo";
            content = "Il tuo account è stato registrato con successo. A breve riceverai un' email da parte dello "
            		+ "staff di NoleJava per confermare la verifica del tuo account.";
        }
        
 
        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "Operazione avvenuta con successo, controlla la tua casella postale";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/jsp/result.jsp").forward(
                    request, response);
        }
    }
}