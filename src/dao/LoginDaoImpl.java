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
public class LoginDaoImpl implements LoginDao{
	
	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;

	@Override
	public User selectUser(String username){
		String sql="SELECT * FROM login WHERE username=?";
		try {
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,username);
			rs = stmt.executeQuery();
			if(rs.next()){
				User user=new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAuthentification(rs.getString("authentification"));
				return user;
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertUser(String username,String password,String authentification)throws RecordAlreadyExistException{
		try {
			User user=selectUser(username);
			if(user!=null)throw new RecordAlreadyExistException();
			//UPDATE login
			String sql="INSERT INTO login (username,password,authentification) VALUES (?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,username);
			stmt.setString(2,password);
			stmt.setString(3,authentification);
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