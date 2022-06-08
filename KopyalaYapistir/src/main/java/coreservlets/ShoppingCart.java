package coreservlets;
import java.util.*;

public class ShoppingCart {
	
	// Eklenen ögeler için liste 
	private ArrayList<ItemOrder> itemsOrdered;
	
	public ShoppingCart() {
		// ilk oluþturulmada boþ liste oluþturulur.
		// Listedeki veriler ItemOrdere tipindedir.
		// CatalogItem gibi.
		itemsOrdered = new ArrayList<ItemOrder>();
	}
		/** Returns List of ItemOrder entries giving
		* Item and number ordered. Declared as List instead
		* of ArrayList so that underlying implementation
		* can be changed later.
		*/
	
	public List<ItemOrder> getItemsOrdered() {
		return(itemsOrdered); // listeyi döndürür
	}
		/** Looks through cart to see if it already contains
		* an order entry corresponding to item ID. If it does,
		* increments the number ordered. If not, looks up
		* Item in catalog and adds an order entry for it.
		*/
	public synchronized void addItem(String itemID) {
		ItemOrder order;
		// ürün eklenir 
		for(int i=0; i<itemsOrdered.size(); i++) {
			order = (ItemOrder)itemsOrdered.get(i);
			// eðer itemID'ler eþleniyorsa sayý bir artýrýlýr. 
			if (order.getItemID().equals(itemID)) {
				order.incrementNumItems();
				return;
			}
		}
		ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID));
		itemsOrdered.add(newOrder);
	}
		/** Looks through cart to find order entry corresponding
		* to item ID listed. If the designated number
		* is positive, sets it. If designated number is 0
		* (or, negative due to a user input error), deletes
		* item from cart.
		*/
	public synchronized void setNumOrdered(String itemID,int numOrdered) {
			ItemOrder order;
			
			for(int i=0; i<itemsOrdered.size(); i++) {
				order = (ItemOrder)itemsOrdered.get(i);
				if (order.getItemID().equals(itemID)) {
					if (numOrdered <= 0) {
						itemsOrdered.remove(i);
					} else {
						order.setNumItems(numOrdered);
					}
					return;
				}
			}
			
			ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID));
			itemsOrdered.add(newOrder);
		}
}
