
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
         
   Cookie username = new Cookie("username",request.getParameter("uname"));
   Cookie password = new Cookie("password",request.getParameter("psw"));

   
   username.setMaxAge(60*60*10); 
   password.setMaxAge(60*60*10); 

   // Add both the cookies in the response header.
   response.addCookie( username );
   response.addCookie( password );
   
   
   
   
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Coooooooooooooooooookie</title>
</head>
<body>

	<h1>Selam</h1>
	<p>Welcome sayfasındasın</p>

</body>
</html>