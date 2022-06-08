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
			System.out.println("þimdi cart yazdýrýcam ->"+cart);
			// cart nesnemiz session üzerinden alýndý fakat ilk seçiþte burasý null olacaktýr.
			// Yani daha önce set edilmemiþtir.
			// bu yüzden bu kontrol yapýlmalý ve set edilmeli
			if (cart == null) {
				cart = new ShoppingCart();
				session.setAttribute("shoppingCart", cart);
				// Ýlk kez kart eklemesi olduðu için shoppingCart key'ine cart bilgisi set edilir. 
			}
			if (itemID != null) {
				String urunSayisi = request.getParameter("numItems");
				// burasý Sepet sayfasýnda ürün sayýsý güncelleme kýsmý içindir.
				// Kaç adet ürün eklendiði, sayý deðiþkenini tutan kýsýmdýr.
				System.out.println("numItemsString="+urunSayisi);
				if (urunSayisi == null) {
					// eðer bu deðiþken null döndüyse , ürün ekleme kýsmýndan buraya gelinmiþtir. 
					// sepet kýsmýnda güncelleme yapýlmamýþtýr.
					cart.addItem(itemID);
				} 
				else {
					// Eðer null deðilse Update Order ile ürün sayýsý güncellenmiþtir. 
					// after changing the number of items in order.
					// Note that specifying a number of 0 results
					// in item being deleted from cart.
					int guncelleme;
					try {
						guncelleme = Integer.parseInt(urunSayisi);
					} catch(NumberFormatException nfe) {
						// hatalý deðer girilmiþse(int olmayan) 1 olarak ayarlanýr
						guncelleme = 1;
					}
					// ardýndan girilen sayý ürün sayýsýný güncelleyecek olan fonksiyona verilir.
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
