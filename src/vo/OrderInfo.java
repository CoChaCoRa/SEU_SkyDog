package vo;

import java.sql.Date;

public class OrderInfo {
	private String goodsName = null;
	private double goodsPrice = 0;
	private int goodsNumber = 0;
	private String goodsSKU = null;
	private String orderID = null;
	private Date orderCreateTime = null;
	private String orderStatus = null;
	private String orderTrackingID = null;
	private String username = null;
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public void setGoodsSKU(String goodsSKU){
		this.goodsSKU=goodsSKU;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setOrderTrackingID(String orderTrackingID) {
		this.orderTrackingID = orderTrackingID;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	public double getGoodsPrice() {
		return goodsPrice;
	}
	public int getGoodsNumber() {
		return goodsNumber;
	}
	public String getGoodsSKU(){
		return goodsSKU;
	}
	public String getOrderID() {
		return orderID;
	}
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public String getOrderTrackingID() {
		return orderTrackingID;
	}
	public String getUserName() {
		return username;
	}
}
