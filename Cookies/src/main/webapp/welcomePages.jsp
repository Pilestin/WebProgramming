<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Hoşgeldin</h1>
	<p><% 
		
		Cookie[] cookies = null;
		cookies = request.getCookies();
		
		// Şimdi cookie oluşturulmuşmu kontrolü yapacağız.
		if( cookies != null ){ 	// eğer cookie null değilse , 
			// ve form'a veri girilip cookie oluşturulmuşsa 
			if(cookies.length > 1){
			
				// Önce isim ve şifre bilgisini alalım. 
				String name = cookies[1].getName();
				String password = cookies[1].getValue();
				// Şimdi bu bilgileri sayfaya yansıtalım
				out.println("<p>Merhaba <strong>" +  name + "</strong> ---- çerez bilgisi kaydedildi </p>"); 
				out.println("username = " + name + "\npassword = " + password );
				
				/*	Eğer çoklu cookie olsaydı bu döngü kullanılabilirdi
				
					for (Cookie c : cookies) {
			   			out.print("[username] : " + c.getName()  + "  ===== " );
			   			out.print("[password] : " + c.getValue() + "<br/>");
					}
				*/
			}
			else{
				// burada sadece formdan gelen değer yazdırılır.
				out.println("<p>Merhaba <i>" + request.getParameter("uname") + "</i> ---- [çerez kaydedilmedi ] </p>");
			}
		}else{
			// hata durumu 
			out.println("çerez bulunamadı");		
		}	
		%></p>
</body>
</html>









