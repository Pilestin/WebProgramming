package coreservlets;

public class CatalogItem {
	// Sayfada g�r�lecek olan kitaplar�n , YAPIs�n� olu�turan s�n�f.
	// 4 tane bilgi al�r(itemID , shortDescription , longDescription , cost) 
	// Bu bilgileri kaps�lleme ile de�i�kenlere atar. 
	private String itemID;
	private String shortDescription;
	private String longDescription;
	private double cost;

	public CatalogItem(String itemID, String shortDescription, String longDescription, double cost) {
		setItemID(itemID);
		// this.itemID = itemID;
		setShortDescription(shortDescription);
		// this.shortDescription = shortDescription;
		setLongDescription(longDescription);
		// this.longDescription = longDescription;
		setCost(cost);
		// this.cost = cost;
	}

	public String getItemID() {
		return (itemID);
	}

	protected void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getShortDescription() {
		return (shortDescription);
	}

	protected void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return (longDescription);
	}

	protected void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public double getCost() {
		return (cost);
	}

	protected void setCost(double cost) {
		this.cost = cost;
	}
}
