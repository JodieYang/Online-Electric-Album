package mat.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.beans.Album;
import mat.java.ope.AlbumOpe;

/**
 * Servlet implementation class FindAlbumServlet
 */
@WebServlet("/FindAlbumServlet")
public class FindAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAlbumServlet() {
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
		//将响应信息的编码设置为utf-8
		response.setCharacterEncoding("utf-8");
		//设置HTTP响应头信息
		response.setHeader("contextType", "html/text;charset=utf-8");
		
		//获取相册ID
		String id = request.getParameter("id");
		int aid = Integer.parseInt(id);
		
		AlbumOpe albumope = AlbumOpe.getInstance();
		Album a = albumope.getAlbum(aid).get(0);
		String name = a.getName();
		PrintWriter pw = new PrintWriter(response.getWriter());
		pw.println(name);
		pw.flush();
	}

}
