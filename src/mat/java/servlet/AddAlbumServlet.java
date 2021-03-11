package mat.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mat.java.ope.AlbumOpe;
import mat.java.util.CreateFile;
import mat.java.beans.Album;
/**
 * Servlet implementation class AddAlbumServlet
 */
public class AddAlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAlbumServlet() {
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
	
			AlbumOpe albumope = AlbumOpe.getInstance();
			String rootPath = this.getInitParameter("rootpath");
			//获取前台表单中相册基本信息
			String albumname = request.getParameter("name");//相册名
			String albumdescription = request.getParameter("description");//相册描述
			//获取创建相册的用户
			String userid = request.getSession().getAttribute("userid").toString();//用户id
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			response.setHeader("contentType", "text/html;charset=utf-8");
			//创建相册
			int num=albumope.findAlbumByName(userid, albumname);
			if(num<=0)
			{
				Album album = new Album();
				//为新建的相册赋上属性值
				album.setName(albumname);
				album.setDescription(albumdescription);
				album.setUserid(userid);
				int id=albumope.getMaxAlbum()+1;
				Date createtime=new Date(System.currentTimeMillis());
				album.setId(id);
				album.setCreatetime(createtime);
				//执行添加相册
				String path=rootPath+userid+"\\"+String.valueOf(id);
				boolean flag = albumope.addAlbum(album);
				boolean flag1=CreateFile.createfile(path);
				//查询该相册的所有信息
				if(flag&&flag1){
					//相册创建成功
					request.setAttribute("album", album);
					request.getRequestDispatcher("pages/front/createSuccess.jsp").forward(request, response);
				}else{
					//失败
					request.setAttribute("msg", "创建失败,请检查");
					request.getRequestDispatcher("pages/front/addAlbum.jsp").forward(request, response);
				}
			}
			else
			{
				PrintWriter pw = new PrintWriter(response.getOutputStream());
				pw.println("<script>alert('相册已存在!');history.back()'</script>");
				pw.flush();
			}
		}
	}
