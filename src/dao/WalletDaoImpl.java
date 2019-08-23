package dao;

import exception.*;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.*;

/**
 * @author YangHangyuan
 *
 */
public class WalletDaoImpl implements WalletDao{

	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	@Override
	public Wallet selectWallet(String account) {
		String sql="SELECT * FROM wallet WHERE account=?";
		try {
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,account);
			rs = stmt.executeQuery();
			if(rs.next()){
				Wallet wallet=new Wallet();
				wallet.setUserName(rs.getString("username"));
				wallet.setAccount(rs.getString("account"));
				wallet.setEmail(rs.getString("email"));
				wallet.setPassword(rs.getString("password"));
				wallet.setMoney(rs.getDouble("money"));
				return wallet;
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertWallet(Wallet wallet) throws RecordAlreadyExistException, RecordNotFoundException {
		try {
			LoginDao login = new LoginDaoImpl();
			User user=login.selectUser(wallet.getUserName());
			if(user==null)throw new RecordNotFoundException();
			Wallet wallet1=selectWallet(wallet.getAccount());
			if(wallet1!=null)throw new RecordAlreadyExistException();
			//UPDATE wallet
			String sql="INSERT INTO wallet (username,account,email,password,money) VALUES (?,?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,wallet.getUserName());
			stmt.setString(2,wallet.getAccount());
			stmt.setString(3,wallet.getEmail());
			stmt.setString(4,wallet.getPassword());
			stmt.setDouble(5,wallet.getMoney());
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
	public boolean updateWallet(Wallet wallet) throws RecordNotFoundException {
		try {
			Wallet wallet1=selectWallet(wallet.getAccount());
			if(wallet1==null)throw new RecordNotFoundException();
			//UPDATE wallet
			String sql="UPDATE wallet SET username=?,email=?,password=?,money=?,"
					+ "WHERE account=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,wallet.getUserName());
			stmt.setString(2,wallet.getEmail());
			stmt.setString(3,wallet.getPassword());
			stmt.setDouble(4,wallet.getMoney());
			stmt.setString(5,wallet.getAccount());
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
