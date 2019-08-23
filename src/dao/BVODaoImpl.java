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
	public BVO selectBVO(String name) {
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
			LoginDao login = new LoginDaoImpl();
			User user=login.selectUser(bvo.getUserName());
			if(user==null)throw new RecordNotFoundException();
			BVO BVO1=selectBVO(bvo.getName());
			if(BVO1!=null)throw new RecordAlreadyExistException();
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

}
