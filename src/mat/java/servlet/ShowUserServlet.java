package mat.java.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.ope.UserOpe;
import mat.java.beans.User;

/**
 * Servlet implementation class ShowUserServlet
 */
@WebServlet("/ShowUserServlet")
public class ShowUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cp = request.getParameter("currentPage");
		int currentPage = Integer.parseInt(cp);
		if(currentPage<1){
			currentPage = 1;
		}
		
		UserOpe userope = UserOpe.getInstance();
		int totalPage = userope.getTotalPage(10);
		
		if(currentPage>totalPage){
			currentPage = totalPage;
		}
		
		List<User> list = userope.findAllUser(10, currentPage);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("pages/front/showAllUser.jsp").forward(request, response);
	}

}
