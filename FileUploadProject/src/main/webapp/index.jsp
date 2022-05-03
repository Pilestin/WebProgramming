<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8">
<title> Java File Upload Servlet Example </title> 
</head> 
<body>
	<!--  burada kullanılıyor servlet adı  -->
  <form action="fileupload" method="post" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="Yukle"/>
  </form>
  
  <form action="fileupload" method="post" enctype="multipart/form-data">
  	<input type="file" name="file2"/>
  	<input type="submit" value="PDF"/>
  </form>

</body>
</html>