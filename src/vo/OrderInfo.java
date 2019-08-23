package vo;

public class OrderInfo {
	private String OrderID = null;
	private String OrderCreateTime = null;
	private String OrderStatus = null;
	private String OrderTrackingID = null;
	
	public void setOrderID(String id) {
		this.OrderID = id;
	}
	public void setOrderCreateTime(String time) {
		this.OrderCreateTime = time;
	}
	public void setOrderStatus(String status) {
		this.OrderStatus = status;
	}
	public void setOrderTrackingID(String id) {
		this.OrderTrackingID = id;
	}
	
	public String getOrderID() {
		return OrderID;
	}
	public String getOrderCreateTime() {
		return OrderCreateTime;
	}
	public String getOrderStatus() {
		return OrderStatus;
	}
	public String getOrderTrackingID() {
		return OrderTrackingID;
	}
}
