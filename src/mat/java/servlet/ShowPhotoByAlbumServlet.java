package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import mat.java.ope.PhotoOpe;
import mat.java.beans.*;

/**
 * Servlet implementation class ShowPhotoByAlbumServlet
 */
//�����ļ���û��
@WebServlet("/ShowPhotoByAlbumServlet")
public class ShowPhotoByAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPhotoByAlbumServlet() {
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
		//��ȡ��ǰ�����
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		//��ȡ��ǰҳ
		String userid=request.getSession().getAttribute("userid").toString();
		String cp = request.getParameter("currentPage");
		int currentPage = Integer.parseInt(cp);
		//�жϵ�ǰҳ�Ƿ�С��1
		if(currentPage<1)
		{
			currentPage=1;
		}
		//�����Ƭ�����ݷ��ʶ���
		PhotoOpe photoope = PhotoOpe.getInstance();
		int totalNum = photoope.getTotalPhoto(userid, id);
		int totalPage;
		if(totalNum % 8 == 0 ){
			totalPage = totalNum/8;
		}else{
			totalPage = totalNum/8+1;
		}
		//�жϵ�ǰҳ�Ƿ������ҳ��
		if(currentPage>totalPage)
		{
			currentPage = totalPage;
		}
					//���ò�ѯ��Ƭ�ķ���
		List<Photo> list = photoope.findByAid(userid,id, 8, currentPage);	
	/*	for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getPid());
		}*/
		request.setAttribute("id",id);
		if(list.size()<1){
			request.getRequestDispatcher("pages/front/noresults.jsp").forward(request, response);
		}
		else
		{
				request.setAttribute("userid", userid);
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("currentPage", currentPage);
				//����ѯ����ͼƬ�ļ������õ��¸�ҳ����
				request.setAttribute("list", list);
				//ҳ����ת
				request.getRequestDispatcher("pages/front/showPhotoByAlbum.jsp").forward(request, response);
		}
	}
}

