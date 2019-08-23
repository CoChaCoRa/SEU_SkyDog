package vo;

public class Store {
	private String No;
	private String Categ;
	private String goodsSKU;
	
	public void setNo(String no) {
		this.No = no;
	}
	public void setCateg(String categ) {
		this.Categ = categ;
	}
	public void setgoodsSKU(String sku) {
		this.goodsSKU = sku;
	}
	
	public String getNo() {
		return No;
	}
	public String getCateg() {
		return Categ;
	}
	public String getgoodsSKU() {
		return goodsSKU;
	}
}
