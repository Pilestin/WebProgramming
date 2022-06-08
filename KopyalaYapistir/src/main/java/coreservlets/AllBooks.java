package coreservlets;

public class AllBooks extends CatalogPage {
	// CatalogPage'den miras alýr
	private static final long serialVersionUID = 2573326764146699400L;

	// Tüm kitaplar , yani techbooks ve kidsbooks ,  ayný bölüme toplandý ve AllBooks adý verildi.
	// Hepsi ayný sayfada görünecekler. 
	public void init() {
		// ids listesi ile kitaplarýn id bilgileri tutulacak.
		String[] ids = {"hall001", "hall002", "lewis001", "alexander001", "rowling001" ,"hall003","deneme" };
		// set item'e parametre olarak liste verilir.
		setItems(ids);
		setTitle("All Books");
	}
}
