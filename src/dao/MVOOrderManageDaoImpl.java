package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.*;
import vo.GoodsInfo;
import vo.OrderInfo;

public class MVOOrderManageDaoImpl implements MVOrderManageDao{

	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<OrderInfo> ResultSetToArrayList(){
		try {
			ArrayList<OrderInfo> list=new ArrayList<OrderInfo>();
			
			do {
				OrderInfo pi=new OrderInfo();
				pi.setGoodsName(rs.getString("goodsName"));
				pi.setGoodsNumber(rs.getInt("goodsNumber"));
				pi.setGoodsPrice(rs.getDouble("goodsPrice"));
				pi.setOrderCreateTime(rs.getDate("orderCreateTime"));
				pi.setOrderID(rs.getString("orderID"));
				pi.setOrderStatus(rs.getString("orderStatus"));
				pi.setOrderTrackingID(rs.getString("orderTrackingID"));
				pi.setUserName(rs.getString("username"));
				list.add(pi);
			}while(rs.next());
			
			return list;
		}
		catch (Exception e) {
			// TODO: handle exception
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<OrderInfo> getAllOrders() {
		try {
			String sql="SELECT * FROM orderInfo";
			stmt=DBC.con.prepareStatement(sql);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return ResultSetToArrayList();
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<OrderInfo> queryByGoodsName(String goodsName) {
		try {
			String sql="SELECT * FROM orderInfo where goodsName=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,goodsName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return ResultSetToArrayList();
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public OrderInfo queryByOrderID(String orderID) {
		try {
			String sql="SELECT * FROM orderInfo where orderID=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,orderID);
			rs=stmt.executeQuery();
			if(rs.next()) {
				OrderInfo pi=new OrderInfo();
				pi.setGoodsName(rs.getString("goodsName"));
				pi.setGoodsNumber(rs.getInt("goodsNumber"));
				pi.setGoodsPrice(rs.getDouble("goodsPrice"));
				pi.setOrderCreateTime(rs.getDate("orderCreateTime"));
				pi.setOrderID(rs.getString("orderID"));
				pi.setOrderStatus(rs.getString("orderStatus"));
				pi.setOrderTrackingID(rs.getString("orderTrackingID"));
				pi.setUserName(rs.getString("username"));
				return pi;
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertOrderInfo(OrderInfo orderInfo) 
			throws RecordNotFoundException,RecordAlreadyExistException,OutOfLimitException {
		try {
			OrderInfo gf=queryByOrderID(orderInfo.getOrderID());
			if(gf!=null)throw new RecordAlreadyExistException();
			GoodsMenuDao goods=new GoodsMenuDaoImpl();
			GoodsInfo goodsInfo = goods.queryByGoodsSKU(orderInfo.getGoodsSKU());
			if(goodsInfo==null)throw new RecordNotFoundException();
			if(goodsInfo.getStock()<orderInfo.getGoodsNumber())throw new OutOfLimitException();
			//UPDATE orderInfo
			String sql="INSERT INTO orderInfo (goodsName,goodsPrice,goodsNumber,goodsSKU,orderID,orderCreateTime,"
					+ "orderStatus,orderTrackingID,username) VALUES (?,?,?,?,?,?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,orderInfo.getGoodsName());
			stmt.setDouble(2,orderInfo.getGoodsPrice());
			stmt.setString(3,orderInfo.getGoodsName());
			stmt.setString(4,orderInfo.getGoodsSKU());
			stmt.setString(5,orderInfo.getOrderID());
			stmt.setDate(6,orderInfo.getOrderCreateTime());
			stmt.setString(7,orderInfo.getOrderStatus());
			stmt.setString(8,orderInfo.getOrderTrackingID());
			stmt.setString(9,orderInfo.getUserName());
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

	@Override
	public boolean updateOrderInfo(OrderInfo orderInfo) throws RecordNotFoundException {
		try {
			OrderInfo oderInfo1=queryByOrderID(orderInfo.getOrderID());
			if(oderInfo1==null)throw new RecordNotFoundException();
			//UPDATE orderInfo
			String sql="UPDATE orderInfo SET goodsName=?,goodsPrice=?,goodsNumber=?,goodsSKU=?,"
					+ "orderCreateTime=?,orderStatus=?,orderTrackingID=?,username=?"
					+ "WHERE orderID=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,orderInfo.getGoodsName());
			stmt.setDouble(2,orderInfo.getGoodsPrice());
			stmt.setInt(3,orderInfo.getGoodsNumber());
			stmt.setString(4,orderInfo.getGoodsSKU());
			stmt.setDate(5,orderInfo.getOrderCreateTime());
			stmt.setString(6,orderInfo.getOrderStatus());
			stmt.setString(7,orderInfo.getOrderTrackingID());
			stmt.setString(8,orderInfo.getUserName());
			stmt.executeUpdate();
			
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
