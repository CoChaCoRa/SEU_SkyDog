package vo;

public class Store {
	private String storeName;
	private String sellerID;
	private String marketID;
	private String TOKEN;
	private String category;
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public void setSellerID(String sellerID) {
		this.sellerID = sellerID;
	}
	public void setMarketID(String marketID) {
		this.marketID = marketID;
	}
	public void setTOKEN(String TOKEN) {
		this.TOKEN = TOKEN;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getStoreName() {
		return storeName;
	}
	public String getSellerID() {
		return sellerID;
	}
	public String getMarketID() {
		return marketID;
	}
	public String getTOKEN() {
		return TOKEN;
	}
	public String getCategory() {
		return category;
	}
}
