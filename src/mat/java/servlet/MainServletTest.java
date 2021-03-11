package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mat.java.ope.*;
import mat.java.beans.*;

/**
 * Servlet implementation class MainServletTest
 */
public class MainServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServletTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		String path=request.getContextPath(); //���������Ŀ��
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String username=request.getParameter("username");
		String password=request.getParameter("password");
//		String path=request.getContextPath();
		UserOpe userope=UserOpe.getInstance();
		User user=userope.checkUser(username, password);
		if(user!=null&&user.getName().equals(username)&&user.getPass().equals(password)&&username!=""&&password!="")
		//ע��:��""����ʾ�մ���������null	
		{
			session.setAttribute("userid", user.getId());
			session.setAttribute("username",user.getName());
			session.setAttribute("uflag", user.getUflag());
			session.setAttribute("password", user.getPass());
			request.getRequestDispatcher("/pages/front/success.jsp").forward(request, response);	
		}
		else
		{
			request.setAttribute("msg", "�û������������");
	//		response.getOutputStream().write(strError.getBytes("utf-8")); ���������̨��������ǶԵģ������������ã��������ִ��˵�ʹ��ϰ��
			request.getRequestDispatcher("/pages/front/login.jsp").forward(request, response);			
	//		response.sendRedirect(path+"/pages/front/login.jsp");
	//		response.getWriter().append("failed login��please try again ");

		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
