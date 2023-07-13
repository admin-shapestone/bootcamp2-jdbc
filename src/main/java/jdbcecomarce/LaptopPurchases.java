package jdbcecomarce;

public class LaptopPurchases {

	private int customerId;
	private int purchaseId;
	private int quantity;
	private String itemPurchased;
	private double price;
	private String dateOfPurchase;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemPurchased() {
		return itemPurchased;
	}

	public void setItemPurchased(String itemPurchased) {
		this.itemPurchased = itemPurchased;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	@Override
	public String toString() {
		return "LaptopPurchases [customerId=" + customerId + ", purchaseId=" + purchaseId + ", quantity=" + quantity
				+ ", itemPurchased=" + itemPurchased + ", price=" + price + ", dateOfPurchase=" + dateOfPurchase + "]";
	}

}
