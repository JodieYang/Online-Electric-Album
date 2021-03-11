package mat.java.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mat.java.beans.Photo;
import mat.java.ope.CommentOpe;
import mat.java.ope.PhotoOpe;
import mat.java.beans.Comment;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		String sid = request.getParameter("id");
		String spid = request.getParameter("pid");
		String puserid=request.getParameter("userid");
		int id = Integer.parseInt(sid);
		int pid = Integer.parseInt(spid);
		PhotoOpe photoope = PhotoOpe.getInstance();
		List<Photo> list=photoope.findByPid(puserid,id,pid);
		CommentOpe commentope=CommentOpe.getInstance();
		List<Comment> plist=commentope.findAllComment(puserid, id, pid);
		Photo p=null;
		if(list!=null&&!list.isEmpty())
		{
			p = list.get(0);
		}
		//int count = pdao.getTotalNum(aid);
		List<Photo> photos = photoope.findByAname("Temp");
		if(p != null){
			
			request.setAttribute("photos",photos);
			//将照片对象传递到下个页面中
			request.setAttribute("p", p);
			request.setAttribute("plist", plist);
			request.getRequestDispatcher("pages/front/showPhotoAndComment.jsp").forward(request, response);
		}else{
			//照片不存在
		}

	}

}
