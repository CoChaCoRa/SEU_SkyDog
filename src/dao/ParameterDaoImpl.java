package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
import vo.Parameter;

public class ParameterDaoImpl implements ParameterDao{
	
	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<Parameter> ResultSetToArrayList(){
		try {
			ArrayList<Parameter> list=new ArrayList<Parameter>();
			
			do {
				Parameter pi;
				pi=new Parameter();
				pi.setKey(rs.getString("parameter_key"));
				pi.setValue(rs.getInt("parameter_value"));
				pi.setDeclare(rs.getString("parameter_declare"));
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
	public ArrayList<Parameter> getAllParameter() {
		try {
			String sql="SELECT * FROM parameter";
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
	public Parameter queryByKey(String key){
		try {
			String sql="SELECT * FROM parameter where parameter_key=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,key);
			rs=stmt.executeQuery();
			if(rs.next()) {
				Parameter pi=new Parameter();
				pi.setKey(rs.getString("parameter_key"));
				pi.setValue(rs.getInt("paramater_value"));
				pi.setDeclare(rs.getString("paramater_declare"));
				return pi;
			}
		}catch(Exception e) {
    		System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertParameter(Parameter parameter) throws RecordAlreadyExistException{
		try {
			Parameter gf=queryByKey(parameter.getKey());
			if(gf!=null)throw new RecordAlreadyExistException();
			//UPDATE goodsInfo
			String sql="INSERT INTO parameter (parameter_key,parameter_value,parameter_declare) VALUES (?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,parameter.getKey());
			stmt.setInt(2,parameter.getValue());
			stmt.setString(3,parameter.getDeclare());
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
	public boolean updateParameter(Parameter parameter)throws RecordNotFoundException {
		try {
			Parameter gf=queryByKey(parameter.getKey());
			if(gf==null)throw new RecordNotFoundException();
			//UPDATE orderInfo
			String sql="UPDATE parameter SET parameter_value=?,parameter_declare=? WHERE parameter_key=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setInt(1,parameter.getValue());
			stmt.setString(2,parameter.getDeclare());
			stmt.setString(3,parameter.getKey());
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
	public boolean deleteParameter(String key) {
		try {
			//UPDATE orderInfo
			String sql="DELETE from parameter WHERE parameter_key=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,key);
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
