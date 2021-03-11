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
			//����SmartUpload����
			SmartUpload su = new SmartUpload();
			//��ʼ���ϴ�����
			su.initialize(this.getServletConfig(),request,response);
			//��ȡwebԪ��
			//���������ϴ����ļ����ֵ
			su.setMaxFileSize(1024*1024*10);//���10M
			//���������ϴ��ļ�������
			su.setAllowedFilesList("jpg,png,bmp,gif,emf");
			//��ʼ�ϴ�
			su.upload();
			//��ȡ�����ļ�
			Files files = su.getFiles();
			//��ȡ�����ļ�(question1:Ϊʲôֻ��ȡ�����ļ�����û��ѭ��������)
			File file = files.getFile(0);
			//��ȡ�ļ���,
			String filename = file.getFileName();//ͼƬ����
			//�����ļ�(�����������ļ����Ƶ�����������ļ���Ŀ¼��)
			//����(question2�����Զ����������ڵ��ļ����𣿣��������???����!!!)
			Request req = su.getRequest();
			//����aid��Ҫ�ĳ�id,id�����id
			String pdes = req.getParameter("pdescription");//��ȡ��Ƭ������Ϣ
			String sid = req.getParameter("id");
			String name=req.getParameter("name");
			int id = Integer.parseInt(sid);//��ȡ���ID
			String path=rootPath+userid+"\\"+sid;
			boolean b=CreateFile.createfile(path);
			if(b)
			{
				//�½�ͼƬ����
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
						//�ϴ��ɹ�
						if("Temp".equals(name))
						{
							request.getRequestDispatcher("QueryPhotoServlet?currentPage=1&name=Temp").forward(request, response);
						}
						else
							request.getRequestDispatcher("ShowPhotoByAlbumServlet?id="+id+"&currentPage=1").forward(request, response);
					}else{
						//ʧ��
					}
				}
				else
				{
					PrintWriter pw = new PrintWriter(response.getOutputStream());
					pw.println("<script>alert('ͼƬ�Ѵ���!');history.back()'</script>");
					pw.flush();
				}
			}
			else
			{
				//ʧ��
			}
			
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
