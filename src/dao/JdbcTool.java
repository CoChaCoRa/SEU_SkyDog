package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTool {
     
/**
 * ǰ������ Ҫ����Ŀ�м���mysql-connector  jar
 * ʹ��jdbc���裺
 * 1.��������
 * 2.��ȡ����
 * 3.��ȡ��������
 * 4.����sql
 */
	public static final String url = "jdbc:mysql://localhost:3306/gmcbrand";
	public Connection con=null;
	private String username="root";
	private String password="123456";
	
	public JdbcTool(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	*//**
	 ִ��sql ��ɾ�� ��������ѯ
	 *//*
	public  static  void   executeSql(String   sql){
		try {
			Statement  st=(Statement) conn.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	*//**
	 ִ��sql ��ѯ ���ؽ��Ϊ ResultSet �����
	 *//*
	public static  ResultSet executeSqlByQuery(String  querysql){
		ResultSet rs=null;
		try {
			Statement  st=(Statement) conn.createStatement();
			rs = (ResultSet) st.executeQuery(querysql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return  rs;
	}  
	*/
	
	
	
	
	
	
	
	
}