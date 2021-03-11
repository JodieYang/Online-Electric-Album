package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import mat.java.beans.Album;
import mat.java.ope.UserOpe;
import mat.java.util.CreateFile;
import mat.java.ope.AlbumOpe;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		boolean flag=false;
		boolean isUsedName=false;
		String strError="";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String useremail=request.getParameter("useremail");
		
		String sessionCode = (String) request.getSession().getAttribute("code");
		System.out.println(sessionCode);
		
		if(sessionCode != null) 
		{
			//  获取页面提交的验证码
			String inputCode = request.getParameter("code");
			System.out.println("页面提交的验证码:" + inputCode);
			if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) 
			{
				if(!(username==null||password==null||useremail==null))
				{
					UserOpe userope=UserOpe.getInstance();
					isUsedName=userope.checkUserName(username);
					if(!isUsedName)
					{	
						flag=userope.addUser(username, password, useremail);
					}
				}
				if(flag)
				{
					String rootPath = this.getInitParameter("rootpath");
					AlbumOpe albumope=AlbumOpe.getInstance();
					int id=albumope.getMaxAlbum()+1;
					String path=rootPath+useremail+"\\"+String.valueOf(id);
					Album tempAlbum=new Album();
					Date createtime=new Date(System.currentTimeMillis());
					tempAlbum.setId(id);
					tempAlbum.setDescription("This is your first album");
					tempAlbum.setName("Temp");
					tempAlbum.setUserid(useremail);
					tempAlbum.setCreatetime(createtime);
					boolean flag1=albumope.addAlbum(tempAlbum);
					if(flag1)
					{
						boolean flag2=CreateFile.createfile(path);
						if(flag2)
						{
							request.setAttribute("msg","<font color='green'>注册成功,请输入用户名密码登陆</font>");
							request.getRequestDispatcher("/pages/front/login.jsp").forward(request, response);
						}
					}
				}
				else
				{
					if(isUsedName)
					{
						strError = "该用户名已使用！";
					}
					else 
					{
						strError = "注册失败，请检查！";
					}
				}
			}
			else
			{
				strError="验证码错误！";
			}
		}
		else
		{
			strError="验证码不存在！";
		}
		request.setAttribute("msg", strError);
		request.getRequestDispatcher("/pages/front/register.jsp").forward(request, response);			
	}

}
