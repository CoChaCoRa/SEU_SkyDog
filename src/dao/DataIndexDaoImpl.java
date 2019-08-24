package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exception.RecordAlreadyExistException;
import exception.RecordNotFoundException;
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
	public DataDict selectDataDict(int code) {
		String sql="SELECT * FROM dataindex WHERE code=?";
		try {
			stmt=DBC.con.prepareStatement(sql);
			stmt.setInt(1,code);
			rs = stmt.executeQuery();
			if(rs.next()){
				DataDict dataDict=new DataDict();
				dataDict.setIndexType(rs.getString("IndexType"));
				dataDict.setDescription(rs.getString("Description"));
				dataDict.setCode(rs.getInt("Code"));
				dataDict.setCodeValue(rs.getString("CodeValue"));
				return dataDict;
			}
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean insertDataDict(DataDict datadict)throws RecordAlreadyExistException{
		try {
			DataDict datadict1=selectDataDict(datadict.getCode());
			if(datadict1!=null)throw new RecordAlreadyExistException();
			//UPDATE dataindex
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
	public boolean updateDataDict(DataDict datadict)throws RecordNotFoundException {
		try {
			DataDict datadict1=selectDataDict(datadict.getCode());
			if(datadict1==null)throw new RecordNotFoundException();
			//UPDATE dataindex
			String sql="UPDATE dataindex SET IndexType=?,Description=?,CodeValue=? where Code=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setString(1,datadict.getIndexType());
			stmt.setString(2,datadict.getDescription());
			stmt.setString(3,datadict.getCodeValue());
			stmt.setInt(4,datadict.getCode());
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
	public boolean deleteDataDict(int code) {
		try {
			//UPDATE orderInfo
			String sql="DELETE * from dataindex WHERE Code=?";
			stmt=DBC.con.prepareStatement(sql);
			stmt.setInt(1,code);
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
