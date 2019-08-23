package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTool {
     
/**
 * 前提条件 要在项目中加入mysql-connector  jar
 * 使用jdbc步骤：
 * 1.加载驱动
 * 2.获取连接
 * 3.获取声明对象
 * 4.操作sql
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
	 执行sql 增删改 不包括查询
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
	 执行sql 查询 返回结果为 ResultSet 结果集
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