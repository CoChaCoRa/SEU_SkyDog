package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.lang.*;

public class Myweb extends HttpServlet{

//	@Override
//	public void init() throws ServletException {
//		// TODO Auto-generated method stub
//		System.out.println("这是MyWeb的init方法");
//		super.init();
//	}
//	
//	@Override
//	protected void service(HttpServletRequest arg0, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		System.out.println("这是MyWeb的service方法");
//		resp.getWriter().write("welcom to seu");
//		super.service(arg0, resp);
//	}
//	
//	@Override
//	public void destroy() {
//		// TODO Auto-generated method stub
//		System.out.println("这是MyWeb的destroy方法");
//		super.destroy();
//	}
//	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("这是一个doGet方法=====================");
		String username=req.getParameter("username");
		resp.getWriter().write("welcom "+username+"to seu");
		//super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=req.getParameter("username");
		String password=req.getParameter("pass");
		if(username.equals("admin")){
			
			user_cha=1;//1为管理员
			req.setAttribute("user_cha", user_cha);
			req.getRequestDispatcher("test.jsp").forward(req, resp);
		}
		else{
			user_cha=2;
			req.setAttribute("user_cha", user_cha);
			req.getRequestDispatcher("test.jsp").forward(req, resp);
		}
		System.out.println("这是一个doPost方法");
		user_cha=0;
	}
	
public int user_cha;
}
