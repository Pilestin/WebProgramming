

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CookieControl
 */
@WebServlet("/cookiecontrol")
public class CookieControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CookieControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("welcomePages.jsp");
		
		if(request.getParameter("remember") == null ){
			// iþaretli olmadýðý durumda direk sayfaya gir.
	        requestDispatcher.forward(request, response); 
		}
		else{ // request.getParameter("remember").equals("on") durumu , yani kutucuk iþaretli

			// beni hatýrla kutucuðu iþaretlendi 
				// yeni cookie oluþturulacak 
				/*
				Cookie cUsername = new Cookie("username", request.getParameter("uname"));
			    Cookie cPassword = new Cookie("password", request.getParameter("psw"));
			    
			    // cookie ayarlarý
			    cUsername.setMaxAge(60*60*10);  // Cookie yaþam süresi saniye cinsinden ifade edilir
			    cPassword.setMaxAge(60*60*10); 	// . . .  
			    
			    // cookie eklendi
			    response.addCookie(cUsername);
			    response.addCookie(cPassword); 
			    */
				//								//      key=username         ,     value=password   
				Cookie userCookie = new Cookie(request.getParameter("uname") , request.getParameter("psw"));
				userCookie.setMaxAge(60*60*10);
				response.addCookie(userCookie);
			    
						
				// sayfa deðiþimi
			    requestDispatcher.forward(request, response);	// welcomePages sayfasýna gidilir.
			   	    
		}
	}
}












