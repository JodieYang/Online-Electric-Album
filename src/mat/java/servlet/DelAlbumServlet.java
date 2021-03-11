package mat.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.ope.AlbumOpe;
import mat.java.util.DeleteFile;
import java.io.File;
import mat.java.beans.Album;

/**
 * Servlet implementation class DelAlbumServlet
 */
@WebServlet("/DelAlbumServlet")
public class DelAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelAlbumServlet() {
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
		//�����Ҫɾ��������ID
		String sid = request.getParameter("id");
		String path="D:\\java�ļ�\\MyAlbumTest\\WebContent\\photos\\";
		String userid=request.getSession().getAttribute("userid").toString();
		String sPath=path+userid+"\\"+sid;
		int id = Integer.parseInt(sid);
		boolean b=false;
		AlbumOpe albumope = AlbumOpe.getInstance();
		//�����������ݷ��ʶ���ɾ��ָ�����
		Album album=albumope.getAlbum(id).get(0);
		if("Temp".equals(album.getName()))
		{
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			response.setHeader("contentType", "text/html;charset=utf-8");
			PrintWriter pw = new PrintWriter(response.getOutputStream());
			pw.println("<script>alert('�޷�ɾ��!');history.back()'</script>");
			pw.flush();
		}
		else
		{
			b = albumope.delAlbum(userid,id);
		}
		//�жϽ��,ҳ����ת
		//System.out.println(b);
		if(b){
			File dirFile=new File(sPath);
			boolean b1=DeleteFile.deleteFolder(dirFile);
			System.out.println(b1);
			request.getRequestDispatcher("FindAlbumByUserServlet?op=showAlbum&currentPage=1").forward(request, response);
		}
	}
}


