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
		String path=req.getContextPath(); //这个就是项目名
/*		UserOpe userope=UserOpe.getInstance();
		User user=userope.checkUser(username, password);*/
		if("admin".equals(username))
		{
		//	System.out.println("1111111111");
	//		session.setAttribute("id", user.getId());
			req.getRequestDispatcher("/pages/front/success.jsp").forward(req, resp);	
			//先假设成功，跳转success界面
		}
		else
		{
			String strError = "[ 提示：用户名或密码有误! ]";
			req.setAttribute("error", strError);
			resp.sendRedirect(path+"/pages/front/fail.jsp");
			//失败，则跳转fail界面
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	

}
