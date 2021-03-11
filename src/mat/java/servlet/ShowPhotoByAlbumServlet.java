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
//配置文件还没改
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
		//获取当前相册编号
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);
		//获取当前页
		String userid=request.getSession().getAttribute("userid").toString();
		String cp = request.getParameter("currentPage");
		int currentPage = Integer.parseInt(cp);
		//判断当前页是否小于1
		if(currentPage<1)
		{
			currentPage=1;
		}
		//获得照片的数据访问对象
		PhotoOpe photoope = PhotoOpe.getInstance();
		int totalNum = photoope.getTotalPhoto(userid, id);
		int totalPage;
		if(totalNum % 8 == 0 ){
			totalPage = totalNum/8;
		}else{
			totalPage = totalNum/8+1;
		}
		//判断当前页是否大于总页数
		if(currentPage>totalPage)
		{
			currentPage = totalPage;
		}
					//调用查询照片的方法
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
				//将查询到的图片的集合设置到下个页面中
				request.setAttribute("list", list);
				//页面跳转
				request.getRequestDispatcher("pages/front/showPhotoByAlbum.jsp").forward(request, response);
		}
	}
}

