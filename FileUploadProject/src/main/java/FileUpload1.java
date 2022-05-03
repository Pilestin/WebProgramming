import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;


@WebServlet("/fileupload") // bu servletin ismi , kesin tan�mlanmal� 
@MultipartConfig(	       // ilk sorun buradan kaynakl�. Buras� olmad���nda 400 hata kodlar� al�n�yor 
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class FileUpload1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload1() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		    /* Receive file uploaded to the Servlet from the HTML5 form */
		    Part filePart = request.getPart("file");
		    String fileName = filePart.getSubmittedFileName();
		    // ikinci sorun fileName i�erisindeki : karakteri server'da sorun yaratmakta. 500 hata kodu d�nd�rmekte 
		    String fileName2 = fileName.replace(":", " "); // : karakteri sorun yaratt��� i�in bunu bo�luk ile de�i�tirdik c:\desk
		    for (Part part : request.getParts()) {
		      part.write("C:\\upload\\" + fileName2);
		    }
		    response.getWriter().print("The file uploaded sucessfully.");
		  }
	  
}
