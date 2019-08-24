package dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import vo.*;
import exception.*;

/**
 * @author YangHangyuan
 *
 */
public class BVODaoImpl implements BVODao {
	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;

	@Override
	public BVO selectBVO(String username) {
		String sql="SELECT * FROM bvo WHERE username=?";
		try {
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,username);
			rs = stmt.executeQuery();
			if(rs.next()){
				BVO bvo=new BVO();
				bvo.setUserName(rs.getString("username"));
				bvo.setName(rs.getString("name"));
				bvo.setEmail(rs.getString("email"));
				bvo.setPhone(rs.getString("phone"));
				return bvo;
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public BVO selectBVOName(String name) {
		String sql="SELECT * FROM bvo WHERE name=?";
		try {
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,name);
			rs = stmt.executeQuery();
			if(rs.next()){
				BVO bvo=new BVO();
				bvo.setUserName(rs.getString("username"));
				bvo.setName(rs.getString("name"));
				bvo.setEmail(rs.getString("email"));
				bvo.setPhone(rs.getString("phone"));
				return bvo;
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertBVO(BVO bvo) throws RecordAlreadyExistException, RecordNotFoundException {
		try {
			BVO bvo0=selectBVO(bvo.getUserName());
			if(bvo0==null)throw new RecordNotFoundException();
			BVO bvo1=selectBVOName(bvo.getName());
			if(bvo1!=null)throw new RecordAlreadyExistException();
			//UPDATE bvo
			String sql="INSERT INTO bvo (username,name,email,phone) VALUES (?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,bvo.getUserName());
			stmt.setString(2,bvo.getName());
			stmt.setString(3,bvo.getEmail());
			stmt.setString(4,bvo.getPhone());
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
	public boolean updateBVO(BVO bvo) throws RecordNotFoundException {
		try {
			BVO bvo0=selectBVO(bvo.getUserName());
			if(bvo0==null)throw new RecordNotFoundException();
			//UPDATE bvo
			String sql="UPDATE orderInfo SET name=?,email=?,phone=? WHERE username=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,bvo.getName());
			stmt.setString(2,bvo.getEmail());
			stmt.setString(3,bvo.getPhone());
			stmt.setString(4,bvo.getUserName());
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
