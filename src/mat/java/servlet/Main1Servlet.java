package mat.java.servlet;
import java.io.IOException;
import mat.java.ope.*;
import mat.java.beans.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


class Main1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	
	public Main1Servlet()
	{
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String path=req.getContextPath(); //���������Ŀ��
/*		UserOpe userope=UserOpe.getInstance();
		User user=userope.checkUser(username, password);*/
		if("admin".equals(username))
		{
		//	System.out.println("1111111111");
	//		session.setAttribute("id", user.getId());
			req.getRequestDispatcher("/pages/front/success.jsp").forward(req, resp);	
			//�ȼ���ɹ�����תsuccess����
		}
		else
		{
			String strError = "[ ��ʾ���û�������������! ]";
			req.setAttribute("error", strError);
			resp.sendRedirect(path+"/pages/front/fail.jsp");
			//ʧ�ܣ�����תfail����
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	

}
