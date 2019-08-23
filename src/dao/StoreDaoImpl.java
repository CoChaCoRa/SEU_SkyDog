package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import vo.Store;

public class StoreDaoImpl implements StoreDao{

	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<Store> ResultSetToArrayList(){
		try {
			ArrayList<Store> list=new ArrayList<Store>();
			
			do {
				Store pi=new Store();
				pi.setStoreName(rs.getString("storeName"));
				pi.setSellerID(rs.getString("sellerID"));
				pi.setMarketID(rs.getString("marketID"));
				pi.setTOKEN(rs.getString("TOKEN"));
				pi.setCategory(rs.getString("category"));
				list.add(pi);
			}while(rs.next());
			
			return list;
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<Store> getAllStores() {
		try {
			String sql="SELECT * FROM store";
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
	public Store queryByStoreName(String storeName) {
		try {
			String sql="SELECT * FROM store where storeName=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,storeName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				Store pi=new Store();
				pi.setStoreName(rs.getString("storeName"));
				pi.setSellerID(rs.getString("sellerID"));
				pi.setMarketID(rs.getString("marketID"));
				pi.setTOKEN(rs.getString("TOKEN"));
				pi.setCategory(rs.getString("category"));
				return pi;
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertStore(Store store) throws RecordAlreadyExistException {
		try {
			Store gf=queryByStoreName(store.getStoreName());
			if(gf!=null)throw new RecordAlreadyExistException();
			//UPDATE store
			String sql="INSERT INTO store (storeName,sellerID,marketID,TOKEN,category)"
					+" VALUES (?,?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,store.getStoreName());
			stmt.setString(2,store.getSellerID());
			stmt.setString(3,store.getMarketID());
			stmt.setString(4,store.getTOKEN());
			stmt.setString(5,store.getCategory());
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
