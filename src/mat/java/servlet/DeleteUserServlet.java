package mat.java.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.ope.UserOpe;
import mat.java.util.DeleteFile;
/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
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
		String path="D:\\java文件\\MyAlbumTest\\WebContent\\photos\\";
		String sPath=path+guserid;
		UserOpe userope=UserOpe.getInstance();
		boolean b=userope.delUser(guserid);
		if(b){
			File dirFile=new File(sPath);
			boolean b1=DeleteFile.deleteFolder(dirFile);
			System.out.println(b1);
			request.getRequestDispatcher("ShowUserServlet?currentPage=1").forward(request, response);
		}
		else
		{
			//失败的提示
		}
		
	}

}
