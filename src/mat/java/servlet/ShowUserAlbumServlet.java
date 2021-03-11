package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import mat.java.beans.Album;
import mat.java.ope.AlbumOpe;
/**
 * Servlet implementation class ShowUserAlbumServlet
 */
@WebServlet("/ShowUserAlbumServlet")
public class ShowUserAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowUserAlbumServlet() {
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
		AlbumOpe albumope=AlbumOpe.getInstance();
		List<Album> list=albumope.findAlbumByUserid(guserid);
		request.setAttribute("guserid", guserid);
		request.setAttribute("list", list);
		request.getRequestDispatcher("pages/front/showUserAlbum.jsp").forward(request, response);
	}

}
