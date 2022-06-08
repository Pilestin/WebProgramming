package coreservlets;

public class AllBooks extends CatalogPage {
	// CatalogPage'den miras al�r
	private static final long serialVersionUID = 2573326764146699400L;

	// T�m kitaplar , yani techbooks ve kidsbooks ,  ayn� b�l�me topland� ve AllBooks ad� verildi.
	// Hepsi ayn� sayfada g�r�necekler. 
	public void init() {
		// ids listesi ile kitaplar�n id bilgileri tutulacak.
		String[] ids = {"hall001", "hall002", "lewis001", "alexander001", "rowling001" ,"hall003","deneme" };
		// set item'e parametre olarak liste verilir.
		setItems(ids);
		setTitle("All Books");
	}
}
