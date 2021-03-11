package mat.java.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.ope.CommentOpe;
import mat.java.beans.Comment;

/**
 * Servlet implementation class AddCommentServlet
 */
@WebServlet("/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommentServlet() {
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
		String sid=request.getParameter("id");
		int id=Integer.parseInt(sid);
		String spid=request.getParameter("pid");
		int pid=Integer.parseInt(spid);
		String puserid=request.getParameter("userid");
		String cuserid=request.getSession().getAttribute("userid").toString();
		String content=request.getParameter("content");
		CommentOpe commentope=CommentOpe.getInstance();
		int cid=commentope.getMaxCid(puserid, id, pid)+1;
		Date commenttime=new Date(System.currentTimeMillis());
		//返回会评论两次先不管，待会试试看
		Comment comment=new Comment();
		comment.setCid(cid);
		comment.setContent(content);
		comment.setCommenttime(commenttime);
		comment.setCuserid(cuserid);
		comment.setPuserid(puserid);
		comment.setAlbumid(id);
		comment.setPid(pid);
		boolean flag=commentope.addComment(comment);
		if(flag)
		{
			request.getRequestDispatcher("CommentServlet?userid="+puserid+"&id="+id+"&pid="+pid).forward(request, response);
		}
		else {
			//评论失败的操作
		}
	}

}
