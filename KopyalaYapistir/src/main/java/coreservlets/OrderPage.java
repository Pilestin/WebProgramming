package coreservlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;


@WebServlet("/orderpage")
public class OrderPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderPage() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		ShoppingCart cart;
		String itemID = request.getParameter("itemID");
		
		synchronized(session) {
			cart = (ShoppingCart)session.getAttribute("shoppingCart");
			System.out.println("�imdi cart yazd�r�cam ->"+cart);
			// cart nesnemiz session �zerinden al�nd� fakat ilk se�i�te buras� null olacakt�r.
			// Yani daha �nce set edilmemi�tir.
			// bu y�zden bu kontrol yap�lmal� ve set edilmeli
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
				// �lk kez kart eklemesi oldu�u i�in shoppingCart key'ine cart bilgisi set edilir. 
			}
			if (itemID != null) {
				String urunSayisi = request.getParameter("numItems");
				// buras� Sepet sayfas�nda �r�n say�s� g�ncelleme k�sm� i�indir.
				// Ka� adet �r�n eklendi�i, say� de�i�kenini tutan k�s�md�r.
				System.out.println("numItemsString="+urunSayisi);
				if (urunSayisi == null) {
					// e�er bu de�i�ken null d�nd�yse , �r�n ekleme k�sm�ndan buraya gelinmi�tir. 
					// sepet k�sm�nda g�ncelleme yap�lmam��t�r.
					cart.addItem(itemID);
				} 
				else {
					// E�er null de�ilse Update Order ile �r�n say�s� g�ncellenmi�tir. 
					// after changing the number of items in order.
					// Note that specifying a number of 0 results
					// in item being deleted from cart.
					int guncelleme;
					try {
						guncelleme = Integer.parseInt(urunSayisi);
					} catch(NumberFormatException nfe) {
						// hatal� de�er girilmi�se(int olmayan) 1 olarak ayarlan�r
						guncelleme = 1;
					}
					// ard�ndan girilen say� �r�n say�s�n� g�ncelleyecek olan fonksiyona verilir.
					cart.setNumOrdered(itemID, guncelleme);
				}
			}
		}
		response.setContentType("text/html");
		
		String title = "Status of Your Order";
		String docType ="<!DOCTYPE HTML>\n";
		out.println(docType +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body BGCOLOR=\"#FDF5E6\">\n" +
				"<h1 ALIGN=\"CENTER\">" + title + "</h1>"
				);
		synchronized(session) {
			List eklenenItemler = cart.getItemsOrdered();
			if (eklenenItemler.size() == 0) {
					out.println("<H2><I>No items in your cart...</I></H2>");
			} else {
					// If there is at least one item in cart, show table
					// of items ordered.
					out.println("<TABLE BORDER=1 ALIGN=\"CENTER\">\n" +"<TR BGCOLOR=\"#FFAD00\">\n" +" <TH>Item ID<TH>Description\n" +" <TH>Unit Cost<TH>Number<TH>Total Cost");
					
					ItemOrder order;
							// Rounds to two decimal places, inserts dollar
							// sign (or other currency symbol), etc., as
							// appropriate in current Locale.
					NumberFormat formatter = NumberFormat.getCurrencyInstance();
							// For each entry in shopping cart, make
							// table row showing ID, description, per-item
							// cost, number ordered, and total cost.
							// Put number ordered in textfield that user
							// can change, with "Update Order" button next
							// to it, which resubmits to this same page
							// but specifying a different number of items.
					for(int i=0; i<eklenenItemler.size(); i++) {
						order = (ItemOrder)eklenenItemler.get(i);
						out.println("<TR>\n" +
							" <TD>" + order.getItemID() + "\n" +
							" <TD>" + order.getShortDescription() + "\n" +
							" <TD>" + formatter.format(order.getUnitCost()) + "\n" +
							" <TD>" + "<FORM>\n" + // Submit to current URL
							"<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\"\n" +
							" VALUE=\"" + order.getItemID() + "\">\n" +
							"<INPUT TYPE=\"TEXT\" NAME=\"numItems\"\n" +
							" SIZE=3 VALUE=\"" + order.getNumItems() + "\">\n" +
							"<SMALL>\n" + "<INPUT TYPE=\"SUBMIT\"\n "+
							" VALUE=\"Update Order\">\n" +
							"</SMALL>\n" + "</FORM>\n" + " <TD>" +
							formatter.format(order.getTotalCost())
							);
					}
					String checkoutURL = response.encodeURL("Checkout.html");
					// "Proceed to Checkout" button below table
					out.println("</TABLE>\n" +
							"<FORM ACTION=\"" + checkoutURL + "\">\n" +
							"<BIG><CENTER>\n" +
							"<INPUT TYPE=\"SUBMIT\"\n" +
							" VALUE=\"Proceed to Checkout\">\n" +
							"</CENTER></BIG></FORM>"
							);
			}
			out.println("</BODY></HTML>");
			}
		}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
