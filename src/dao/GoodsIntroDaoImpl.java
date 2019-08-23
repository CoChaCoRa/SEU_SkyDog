package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import vo.GoodsInfo;

public class GoodsIntroDaoImpl implements GoodsIntroDao{

	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<GoodsInfo> ResultSetToArrayList(){
		try {
			ArrayList<GoodsInfo> list=new ArrayList<GoodsInfo>();
			
			do {
				GoodsInfo pi;
				pi=new GoodsInfo();
				pi.setName(rs.getString("goodsName"));
				pi.setPrice(rs.getDouble("goodsPrice"));
				pi.setStock(rs.getInt("goodsStock"));
				pi.setSKU(rs.getString("goodsSKU"));
				pi.setCateg(rs.getString("goodsCateg"));
				pi.setPic(rs.getString("goodsPic"));
				pi.setState(rs.getString("goodsState"));
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
	public ArrayList<GoodsInfo> getAllGoods() {
		try {
			String sql="SELECT * FROM goodsInfo";
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
	public ArrayList<GoodsInfo> queryByGoodsName(String goodsName) {
		try {
			String sql="SELECT * FROM goodsInfo where goodsName=?";
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
	public GoodsInfo queryByGoodsSKU(String goodsSKU) {
		try {
			String sql="SELECT * FROM goodsInfo where goodsSKU=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,goodsSKU);
			rs=stmt.executeQuery();
			if(rs.next()) {
				GoodsInfo pi=new GoodsInfo();
				pi.setName(rs.getString("goodsName"));
				pi.setPrice(rs.getDouble("goodsPrice"));
				pi.setStock(rs.getInt("goodsStock"));
				pi.setSKU(rs.getString("goodsSKU"));
				pi.setCateg(rs.getString("goodsCateg"));
				pi.setPic(rs.getString("goodsPic"));
				pi.setState(rs.getString("goodsState"));
				return pi;
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertGoodsInfo(GoodsInfo goodsInfo) throws RecordAlreadyExistException {
		try {
			GoodsInfo gf=queryByGoodsSKU(goodsInfo.getSKU());
			if(gf!=null)throw new RecordAlreadyExistException();
			//UPDATE goodsInfo
			String sql="INSERT INTO goodsInfo (goodsName,goodsPrice,goodsStock,goodsSKU,goodsCateg,goodsPic,goodsState) VALUES (?,?,?,?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,goodsInfo.getName());
			stmt.setDouble(2,goodsInfo.getPrice());
			stmt.setInt(3,goodsInfo.getStock());
			stmt.setString(4,goodsInfo.getSKU());
			stmt.setString(5,goodsInfo.getCateg());
			stmt.setString(6,goodsInfo.getPic());
			stmt.setString(7,goodsInfo.getState());
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
