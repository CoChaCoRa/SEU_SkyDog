package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.DataDict;
public class DataIndexDaoImpl implements DataIndexDao{
	
	private JdbcTool DBC=new JdbcTool();
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private ArrayList<DataDict> ResultSetToArrayList(){
		try {
			ArrayList<DataDict> list=new ArrayList<DataDict>();
			
			do {
				DataDict pi;
				pi=new DataDict();
				pi.setCode(rs.getInt("Code"));
				pi.setCodeValue(rs.getString("CodeValue"));
				pi.setDescription(rs.getString("Description"));
				pi.setIndexType(rs.getString("IndexType"));
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
	public ArrayList<DataDict> getAllData() {
		try {
			String sql="SELECT * FROM dataindex";
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
	public boolean insertDataDict(DataDict datadict){
		try {
			//UPDATE goodsInfo
			String sql="INSERT INTO dataindex (IndexType,Description,Code,CodeValue) VALUES (?,?,?,?)";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,datadict.getIndexType());
			stmt.setString(2,datadict.getDescription());
			stmt.setInt(3,datadict.getCode());
			stmt.setString(4,datadict.getCodeValue());
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
	public boolean updateDataDict(DataDict datadict) {
		try {
			//UPDATE orderInfo
			String sql="UPDATE dataindex SET IndexType=?,Description=?,Code=?,CodeValue=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,datadict.getIndexType());
			stmt.setString(2,datadict.getDescription());
			stmt.setInt(3,datadict.getCode());
			stmt.setString(4,datadict.getCodeValue());
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
	public boolean deleteDataDict(DataDict datadict) {
		try {
			//UPDATE orderInfo
			String sql="DELETE from dataindex WHERE IndexType=?,Description=?,Code=?,CodeValue=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,datadict.getIndexType());
			stmt.setString(2,datadict.getDescription());
			stmt.setInt(3,datadict.getCode());
			stmt.setString(4,datadict.getCodeValue());
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
