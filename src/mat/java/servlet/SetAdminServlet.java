package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mat.java.ope.UserOpe;

/**
 * Servlet implementation class SetAdminServlet
 */
@WebServlet("/SetAdminServlet")
public class SetAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetAdminServlet() {
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
		String guserid=request.getParameter("guserid");
		UserOpe userope=UserOpe.getInstance();
		boolean b=userope.setAlbum(guserid);
		if(b)
		{
			request.getRequestDispatcher("ShowUserServlet?currentPage=1").forward(request, response);
		}
		else
		{
			// ß∞‹µƒÃ· æ
		}
	}
}
