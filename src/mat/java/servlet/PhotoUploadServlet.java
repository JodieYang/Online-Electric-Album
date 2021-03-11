package mat.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import mat.java.beans.*;
import mat.java.ope.PhotoOpe;
import mat.java.util.CreateFile;

/**
 * Servlet implementation class PhotoUploadServlet
 */
public class PhotoUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoUploadServlet() {
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
		String rootPath = this.getInitParameter("rootpath");
//		System.out.println(rootPath);
		String userid=request.getSession().getAttribute("userid").toString();
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setHeader("contentType", "text/html;charset=utf-8");
		try {
			//创建SmartUpload对象
			SmartUpload su = new SmartUpload();
			//初始化上传环境
			su.initialize(this.getServletConfig(),request,response);
			//获取web元素
			//设置允许上传的文件最大值
			su.setMaxFileSize(1024*1024*10);//最大10M
			//设置允许上传文件的类型
			su.setAllowedFilesList("jpg,png,bmp,gif,emf");
			//开始上传
			su.upload();
			//获取所有文件
			Files files = su.getFiles();
			//获取单个文件(question1:为什么只获取单个文件，又没有循环？？？)
			File file = files.getFile(0);
			//获取文件名,
			String filename = file.getFileName();//图片名称
			//保存文件(将传过来的文件复制到服务器存放文件的目录中)
			//问题(question2：会自动创建不存在的文件夹吗？？？假设会???不会!!!)
			Request req = su.getRequest();
			//这里aid需要改成id,id是相册id
			String pdes = req.getParameter("pdescription");//获取照片描述信息
			String sid = req.getParameter("id");
			String name=req.getParameter("name");
			int id = Integer.parseInt(sid);//获取相册ID
			String path=rootPath+userid+"\\"+sid;
			boolean b=CreateFile.createfile(path);
			if(b)
			{
				//新建图片对象
				PhotoOpe photoope = PhotoOpe.getInstance();
				int num=photoope.findByPname(userid, id, filename);
				if(num<=0)
				{
					Photo p = new Photo();
					p.setAlbumid(id);
					p.setPname(filename);
					p.setPdescription(pdes);
					p.setUserid(userid);
					p.setPath(path+"\\"+filename);
					Date puploadtime=new Date(System.currentTimeMillis());
					p.setPuploadtime(puploadtime);
					int pid=photoope.getMaxPid(userid, id)+1;
					p.setPid(pid);
					boolean f = photoope.addPhoto(p);
					if(f){
						file.saveAs(path+"\\"+filename);
						//上传成功
						if("Temp".equals(name))
						{
							request.getRequestDispatcher("QueryPhotoServlet?currentPage=1&name=Temp").forward(request, response);
						}
						else
							request.getRequestDispatcher("ShowPhotoByAlbumServlet?id="+id+"&currentPage=1").forward(request, response);
					}else{
						//失败
					}
				}
				else
				{
					PrintWriter pw = new PrintWriter(response.getOutputStream());
					pw.println("<script>alert('图片已存在!');history.back()'</script>");
					pw.flush();
				}
			}
			else
			{
				//失败
			}
			
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
