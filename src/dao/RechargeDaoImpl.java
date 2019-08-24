package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.OutOfLimitException;
import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import vo.Recharge;
import vo.Wallet;

public class RechargeDaoImpl implements RechargeDao{
	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<Recharge> ResultSetToArrayList(){
		try {
			ArrayList<Recharge> list=new ArrayList<Recharge>();
			
			do {
				Recharge pi;
				pi=new Recharge();
				pi.setUsername(rs.getString("username"));
				pi.setType(rs.getString("op_type"));
				pi.setAmount(rs.getDouble("amount"));
				pi.setTime(rs.getDate("op_time"));
				pi.setState(rs.getString("op_state"));
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
	public ArrayList<Recharge> getAllRecharge() {
		try {
			String sql="SELECT * FROM audit";
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
	public Recharge queryByNameTime(String username,Date time) {
		try {
			String sql="SELECT * FROM audit where username=?,op_time=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,username);
			stmt.setDate(2, time);
			rs=stmt.executeQuery();
			if(rs.next()) {
				Recharge pi=new Recharge();
				pi.setUsername(rs.getString("username"));
				pi.setType(rs.getString("type"));
				pi.setAmount(rs.getDouble("amount"));
				pi.setTime(rs.getDate("op_time"));
				pi.setState(rs.getString("op_state"));
				return pi;
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertRecharge(Recharge recharge)throws RecordAlreadyExistException{
		try {
			Recharge gf=queryByNameTime(recharge.getUsername(),recharge.getTime());
			if(gf==null)throw new RecordAlreadyExistException();
			//UPDATE recharge
			String sql="INSERT INTO audit (username,op_type,amount,op_time,op_state) VALUES (?,?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,recharge.getUsername());
			stmt.setString(2,recharge.getType());
			stmt.setDouble(3,recharge.getAmount());
			stmt.setDate(4,recharge.getTime());
			stmt.setString(5,recharge.getState());
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
	public boolean addressRecharge(Recharge recharge)throws RecordNotFoundException,OutOfLimitException {
		try {
			Recharge gf=queryByNameTime(recharge.getUsername(),recharge.getTime());
			if(gf!=null)throw new RecordNotFoundException();
			//UPDATE recharge
			String sql="UPDATE recharge SET op_type=?,amount=?,op_state=? where username=?,op_time=? ";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,recharge.getType());
			stmt.setDouble(2,recharge.getAmount());
			stmt.setString(3,recharge.getState());
			stmt.setString(4,recharge.getUsername());
			stmt.setDate(5,recharge.getTime());
			stmt.executeUpdate();
			//handle money if passed
			if(recharge.getState()=="passed"){
				WalletDao walletDao=new WalletDaoImpl();
				Wallet wallet=walletDao.selectWallet(recharge.getUsername());
				if(wallet==null)throw new RecordNotFoundException();
				Double money=wallet.getMoney();
				if(recharge.getType()=="extract"&&money<recharge.getAmount())
					throw new OutOfLimitException();
				if(recharge.getType()=="extract")money-=recharge.getAmount();
				else money+=recharge.getAmount();
				wallet.setMoney(money);
				walletDao.updateWallet(wallet);
			}
		}
		catch (SQLException e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}
