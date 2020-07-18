package com.comunenapoli.progetto.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.comunenapoli.progetto.businessLogic.BusinessLogicUtente;
import com.comunenapoli.progetto.model.Utente;
import com.comunenapoli.progetto.utils.Costanti;




@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String username = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		if ( username != null && !username.isEmpty() 
				&& password != null && !password.isEmpty() && password.length()>=8)
		{
			BusinessLogicUtente businessLogic = (BusinessLogicUtente) getServletContext().getAttribute(Costanti.BUSINESS_LOGIC_UTENTE);
	        Utente user = businessLogic.login(username,password);
	        if ( user != null )
			{	
	        	Integer ruolo = businessLogic.checkRuolo(user.getIdUtente());
	        	System.out.println("utente trovato");
	        	if (user.getIsVerificato()) {
					req.getSession().setAttribute(Costanti.USER_IN_SESSION,user);
					if (ruolo == Costanti.ID_RUOLO_ADMIN) {
						req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req,resp);
					}else if (ruolo == Costanti.ID_RUOLO_STAFF) {
						req.getRequestDispatcher("/jsp/dashboard.jsp").forward(req,resp);
					}else if (ruolo == Costanti.ID_RUOLO_CLIENTE) {
						req.getRequestDispatcher("").forward(req,resp);
					}
	        	}else {
	        		resp.getWriter().println("<h3>Login fallito non sei verificato</h3>");
	        	}
			}
			else
			{
				resp.getWriter().println("<h3>Login fallito</h3>");
			}
	    }
		else
		{
			resp.getWriter().println("<h3>Non sono stati forniti username e password</h3>");
		}
	}

}