package vo;

public class GoodsInfo {
	private String Name;
	private double Price;
	private int Stock;
	private String SKU;
	private String Categ;
	private String Pic;
	private String State;
	
	public void setName(String name) {
		this.Name = name;
	}
	public void setPrice(double price) {
		this.Price = price;
	}
	public void setStock(int stock) {
		this.Stock = stock;
	}
	public void setSKU(String sku) {
		this.SKU = sku;
	}
	public void setCateg(String categ) {
		this.Categ = categ;
	}
	public void setPic(String pic) {
		this.Pic = pic;
	}
	public void setState(String state) {
		this.State = state;
	}
	
	public String getName() {
		return Name;
	}
	public double getPrice() {
		return Price;
	}
	public int getStock() {
		return Stock;
	}
	public String getSKU() {
		return SKU;
	}
	public String getCateg() {
		return Categ;
	}
	public String getPic() {
		return Pic;
	}
	public String getState() {
		return State;
	}
}
