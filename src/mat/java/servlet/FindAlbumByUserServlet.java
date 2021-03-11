package mat.java.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mat.java.ope.AlbumOpe;
import mat.java.beans.Album;
import mat.java.beans.User;

/**
 * Servlet implementation class FindAlbumByUserServlet
 */
@WebServlet("/FindAlbumByUserServlet")
public class FindAlbumByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAlbumByUserServlet() {
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
		//��ȡ��ǰ�û��Ĳ���
		String op = request.getParameter("op");
	
		//��ȡ��ǰ��½�û�,��ʾ����,������?
		String username = request.getSession().getAttribute("username").toString();
		//��õ�ǰ��½�û���ID
		String userid = request.getSession().getAttribute("userid").toString();
		AlbumOpe albumope = AlbumOpe.getInstance();
		List<Album> list = null;
		if("showAlbum".equals(op)){
			
			//����ǰ̨ҳ�洫������ҳ��
			String cp = request.getParameter("currentPage");
			int currentPage = Integer.parseInt(cp);
			//�����ȡ���ĵ�ǰҳ����С��1��Ϊ��ǰҳǿ�и�ֵΪ1
			if(currentPage<1){
				currentPage=1;
			}
			int totalPage = albumope.getTotalPage(userid,8);
			//�жϵ�ǰҳ�Ƿ������ҳ��
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			//��ѯ��ǰ�û������е����(��ҳ/����)
			list = albumope.findAlbumByUserid(userid, 8, currentPage);
			//����ҳ�����ݵ��¸�ҳ����
			request.setAttribute("totalPage", totalPage);
			//����ǰҳ���ݵ��¸�ҳ����
			request.setAttribute("currentPage", currentPage);
			//����ѯ������������ŵ�request��Χ��
			request.setAttribute("list", list);
			//ҳ����ת
			request.getRequestDispatcher("pages/front/showAlbum.jsp").forward(request, response);
		
		}else if("query".equals(op)){
			//��ѯ��Ƭʱ��Ҫʹ�õ���������Ἧ��
			list = albumope.findAlbumByUserid(userid);
			//����ѯ������������ŵ�request��Χ��
			request.setAttribute("list", list);
			//ҳ����ת
			Album album=albumope.findAlbumIdByName(userid, "Temp").get(0);
			int id=album.getId();
			String name=album.getName();
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.getRequestDispatcher("pages/front/QueryPhoto.jsp").forward(request, response);
		}else{
			//�����Ƭʱ��Ҫ��õ������������
			
			list = albumope.findAlbumByUserid(userid);
			//����ѯ������������ŵ�request��Χ��
			request.setAttribute("list", list);
			//ҳ����ת
			request.getRequestDispatcher("pages/front/addPhoto_main.jsp").forward(request, response);
		}
	}

}
