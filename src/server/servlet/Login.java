package server.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import dao.LoginDaoImpl;
import vo.*;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginDao login=new LoginDaoImpl();
    
    //ÐÂÔö

    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String  username=request.getParameter("username");
		String  password=request.getParameter("pass");
		User user=login.selectUser(username);
		String id = user.getAuthentification();
		
		if(user!=null&&user.getPassword().equals(password)){
			
			HttpSession session = request.getSession();
			String t_username = username;
			String userKey = new String("username");
			session.setAttribute(userKey, t_username);
			System.out.println(session.getAttribute(userKey));
			
			if(id.equals("admin")){
				response.sendRedirect("index_admin.jsp");
			}else if(id.equals("BVO")){
				response.sendRedirect("index_BVO.jsp");
			}else if(id.equals("MVO")){
				response.sendRedirect("index_MVO.jsp");
			}
	
		}else{
			response.sendRedirect("login.html");
		}
	}

}