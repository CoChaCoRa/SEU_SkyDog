package dao;

import exception.RecordAlreadyExistException;
import java.sql.ResultSet;


import java.sql.SQLException;
import java.sql.PreparedStatement;
import vo.*;
import exception.*;

/**
 * @author YangHangyuan
 *
 */
public class MVODaoImpl implements MVODao {
	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	@Override
	public MVO selectMVO(String CName_C) {
		String sql="SELECT * FROM mvo WHERE MVO_CName_C=?";
		try {
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,CName_C);
			rs = stmt.executeQuery();
			if(rs.next()){
				MVO mvo=new MVO();
				mvo.setCName_C(rs.getString("MVO_CName_C"));
				mvo.setCName_E(rs.getString("MVO_CName_E"));
				mvo.setIntro(rs.getString("MVO_Intro"));
				mvo.setVeriType(rs.getString("MVO_VeriType"));
				mvo.setCertiAdd(rs.getString("MVO_CertiAdd"));
				mvo.setCertiAdd(rs.getString("username"));
				return mvo;
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertMVO(MVO mvo) throws RecordAlreadyExistException,RecordNotFoundException {
		try {
			LoginDao login = new LoginDaoImpl();
			User user=login.selectUser(mvo.getID());
			if(user==null)throw new RecordNotFoundException();
			MVO mvo1=selectMVO(mvo.getCName_C());
			if(mvo1!=null)throw new RecordAlreadyExistException();
			//UPDATE mvo
			String sql="INSERT INTO mvo (MVO_CName_C,MVO_CName_E,MVO_Intro,MVO_VeriType,MVO_CertiAdd,username) VALUES (?,?,?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,mvo.getCName_C());
			stmt.setString(2,mvo.getCName_E());
			stmt.setString(3,mvo.getIntro());
			stmt.setString(4,mvo.getVeriType());
			stmt.setString(5,mvo.getCertiAdd());
			stmt.setString(6,mvo.getID());
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
