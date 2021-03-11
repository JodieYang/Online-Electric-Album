package mat.java.servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mat.java.beans.Photo;
import mat.java.ope.PhotoOpe;

/**
 * Servlet implementation class QueryPhotoServlet
 */
@WebServlet("/QueryPhotoServlet")
public class QueryPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryPhotoServlet() {
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
		String cp = request.getParameter("currentPage");
		int currentPage = Integer.parseInt(cp);
//		String userid=request.getSession().getAttribute("userid").toString();
		String name=request.getParameter("name");
		if(currentPage<1)
		{
			currentPage=1;
		}
		PhotoOpe photoope = PhotoOpe.getInstance();
		List<Photo> lists=photoope.findByAname(name);
		List<Photo> list=new ArrayList<Photo>();
		int totalNum=lists.size();
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
		int base=(currentPage-1)*8;
		int index=totalNum-base;
		int end=index<8?index:8;
		for(int i=base;i<base+end;i++)
		{
			list.add(lists.get(i));
		}
					//调用查询照片的方法
		/*	for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getPid());
		}*/
		if(list.size()<1){
			request.getRequestDispatcher("pages/front/noresults.jsp").forward(request, response);
		}
		else
		{
				request.setAttribute("totalPage", totalPage);
				request.setAttribute("currentPage", currentPage);
				//将查询到的图片的集合设置到下个页面中
				request.setAttribute("list", list);
				//页面跳转
				request.getRequestDispatcher("pages/front/selectPhoto.jsp").forward(request, response);
		}
	}

}
