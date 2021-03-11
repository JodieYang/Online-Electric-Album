package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.ope.UserOpe;

/**
 * Servlet implementation class UpdatePassWordServlet
 */
@WebServlet("/UpdatePassWordServlet")
public class UpdatePassWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassWordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取前台页面传到后台的原始密码
		String oldpass = request.getParameter("oldpass");
		//获得当前登陆用户的真实密码
		String password=request.getSession().getAttribute("password").toString();
		if(oldpass.equals(password)){
			//密码匹配上
			//获取前台页面传过来的新密码
			String newpass = request.getParameter("newpass");
			UserOpe userope = UserOpe.getInstance();
			String userid=request.getSession().getAttribute("userid").toString();
			boolean b = userope.updatePass(userid, newpass);
			if(b){
				//密码修改成功
				//将session中的用户密码更正,同时重新将用户信息设置到session中
				request.getSession().setAttribute("password", newpass);
				request.setAttribute("success", "密码修改成功");
				request.getRequestDispatcher("pages/front/updatePassword.jsp").forward(request, response);
			}else{
				//密码修改失败
				request.setAttribute("error", "密码修改失败");
				request.getRequestDispatcher("pages/front/updatePassword.jsp").forward(request, response);
			}
		}else{
			//原始密码错误
			request.setAttribute("error", "原始密码不正确");
			request.getRequestDispatcher("pages/front/updatePassword.jsp").forward(request, response);
		}
		
	}

}
