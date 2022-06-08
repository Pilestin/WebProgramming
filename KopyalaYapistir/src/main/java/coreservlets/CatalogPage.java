package coreservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer.Form;


@WebServlet("/catalogpage") // Burayý doldur 
public class CatalogPage extends HttpServlet {
       
	// Alýnan bilgiler ile (itemId )
	
	private static final long serialVersionUID = -992266179993976567L;
	private CatalogItem[] items;
	private String[] itemIDs;
	private String title;
    
    public CatalogPage() {
        super();
        // TODO Auto-generated constructor stub
    }
    // itemID bilgisini kaydeder.
    // 
    protected void setItems(String[] itemIDs) {
    	// tüm kitaplarýn id'sini tutan listeyi aldý
    	this.itemIDs = itemIDs;
    	items = new CatalogItem[itemIDs.length];
    	for(int i=0; i<items.length; i++) {
    		// AllBooks içerisinden setItems fonksiyonunu çaðýrmýþtýk
    		// ve kitap ýd'leri tutan bir liste vermiþtik.
    		// þimdi bu id'lerin kontrol edilmeleri 
    		// ve uyuþmayanlarýn listeye konmamasý gerekli
    		items[i] = Catalog.getItem(itemIDs[i]) ;
    	}
    }
    // title bilgisini kaydeder.
    protected void setTitle(String title) {
    	this.title = title;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		if (items == null) {
			response.sendError(11);
			return;
		}
		String docType ="<!DOCTYPE HTML\n";	// html 5
		out.println(docType +
			"<html>\n" +
			"<meta charset=\"UTF-8\">"	+
			"<head><title>" + title + "</title></head>\n" +
			"<body style=\"background-color: rgb(226, 189, 227)\">\n" +
			"<h1 style=\"align-items: center;\">" + title + "</h1>");
		
		
		// þimdi kitap bilgileri ekrana yazýlmalý
		for(int i=0; i<items.length; i++){
			
			CatalogItem item;
			out.println("<hr>");
			item = items[i];
			
			if (item == null) {
				// item id'si 
				out.println("<FONT COLOR=\"RED\">" +
				"Unknown item ID "  + itemIDs[i] +
				"</FONT>");
			} else {
				// 
				String formURL ="orderpage";
				// Pass URLs that reference own site through encodeURL.
				formURL = response.encodeURL(formURL);
				out.println(
						"<form action=\"" + formURL + "\">\n" +
						"<input type=\"hidden\" name=\"itemID\" " +
						" value=\"" + item.getItemID() + "\">\n" +
						"<H2>" + item.getShortDescription() +
						" ($" + item.getCost() + ")</H2>\n" +
						item.getLongDescription() + "\n" +
						"<p>\n<center>\n" +      
						"<input type=\"submit\" " +"value=\"Add to Shopping Cart\">\n" +
						"</center>\n<p>\n</form>");     
				
				
			}
		}
		out.println("<hr>\n</body></h>");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
