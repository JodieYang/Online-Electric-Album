package mat.java.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mat.java.beans.Photo;
import mat.java.ope.PhotoOpe;
import mat.java.util.DeleteFile;

/**
 * Servlet implementation class DeletePhotoServlet1
 */
@WebServlet("/DeletePhotoServlet1")
public class DeletePhotoServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePhotoServlet1() {
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
				response.setCharacterEncoding("utf-8");
				request.setCharacterEncoding("utf-8");
				response.setHeader("contentType", "text/html;charset=utf-8");
				//��ȡ����id
				String sid = request.getParameter("id");
				int id = Integer.parseInt(sid);
				//��ȡҳ��˴���������ƬID
				String spid = request.getParameter("pid");
				//��String���͵�IDת��Ϊint�͵�ID
				int pid = Integer.parseInt(spid);
				String userid=request.getParameter("userid");
				//��ȡ��Ƭ�����ݷ��ʶ���
				PhotoOpe photoope = PhotoOpe.getInstance();
				//ִ��ɾ��
				Photo photo=photoope.findByPid(userid,id, pid).get(0);
				String sPath=photo.getPath();
				boolean b = photoope.delPhotoByPid(userid,id,pid);
				PrintWriter pw = new PrintWriter(response.getOutputStream());
				if(b){
					File dirFile=new File(sPath);
					DeleteFile.deleteFolder(dirFile);
					pw.println("<script>alert('Delete Success!');window.location.href='ShowGPhotoServlet?currentPage=1&id="+id+"&guserid="+userid+"'</script>");
					pw.flush();
				}else{
					pw.println("<script>alert('ɾ��ʧ��!');history.back()'</script>");
					pw.flush();
				}
	}

}
