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
		//获取当前用户的操作
		String op = request.getParameter("op");
	
		//获取当前登陆用户,显示名字,不用吗?
		String username = request.getSession().getAttribute("username").toString();
		//获得当前登陆用户的ID
		String userid = request.getSession().getAttribute("userid").toString();
		AlbumOpe albumope = AlbumOpe.getInstance();
		List<Album> list = null;
		if("showAlbum".equals(op)){
			
			//接收前台页面传过来的页码
			String cp = request.getParameter("currentPage");
			int currentPage = Integer.parseInt(cp);
			//如果获取到的当前页码数小于1则为当前页强行赋值为1
			if(currentPage<1){
				currentPage=1;
			}
			int totalPage = albumope.getTotalPage(userid,8);
			//判断当前页是否大于总页数
			if(currentPage>totalPage){
				currentPage = totalPage;
			}
			//查询当前用户下所有的相册(分页/部分)
			list = albumope.findAlbumByUserid(userid, 8, currentPage);
			//将总页数传递到下个页面中
			request.setAttribute("totalPage", totalPage);
			//将当前页传递到下个页面中
			request.setAttribute("currentPage", currentPage);
			//将查询到的所有相册存放到request范围中
			request.setAttribute("list", list);
			//页面跳转
			request.getRequestDispatcher("pages/front/showAlbum.jsp").forward(request, response);
		
		}else if("query".equals(op)){
			//查询照片时需要使用到的所有相册集合
			list = albumope.findAlbumByUserid(userid);
			//将查询到的所有相册存放到request范围中
			request.setAttribute("list", list);
			//页面跳转
			Album album=albumope.findAlbumIdByName(userid, "Temp").get(0);
			int id=album.getId();
			String name=album.getName();
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.getRequestDispatcher("pages/front/QueryPhoto.jsp").forward(request, response);
		}else{
			//添加照片时需要获得的所有相册名称
			
			list = albumope.findAlbumByUserid(userid);
			//将查询到的所有相册存放到request范围中
			request.setAttribute("list", list);
			//页面跳转
			request.getRequestDispatcher("pages/front/addPhoto_main.jsp").forward(request, response);
		}
	}

}
