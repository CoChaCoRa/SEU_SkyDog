package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import vo.WishList;

public class WishListDaoImpl implements WishListDao{

	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<WishList> ResultSetToArrayList(){
		try {
			ArrayList<WishList> list=new ArrayList<WishList>();
			
			do {
				WishList pi;
				pi=new WishList();
				pi.setUsername(rs.getString("username"));
				pi.setSKU(rs.getString("sku"));
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
	public ArrayList<WishList> queryByUsername(String Username) {
		try {
			String sql="SELECT * FROM wishlist where username=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,Username);
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
	public boolean insertWishList(WishList wishlist) throws RecordAlreadyExistException {
		try {
			//UPDATE wishlist
			String sql="INSERT INTO wishlist (username,sku) VALUES (?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,wishlist.getUsername());
			stmt.setString(2,wishlist.getSKU());
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
	public boolean deleteWishList(WishList wishlist) throws RecordNotFoundException {
		try {
			//UPDATE wishlist
			String sql="DELETE FROM wishlist where username = ? AND sku = ?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,wishlist.getUsername());
			stmt.setString(2,wishlist.getSKU());
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
