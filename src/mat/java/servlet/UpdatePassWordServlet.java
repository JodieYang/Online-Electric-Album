package mat.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.ope.UserOpe;

/**
 * Servlet implementation class UpdatePassWordServlet
 */
@WebServlet("/UpdatePassWordServlet")
public class UpdatePassWordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePassWordServlet() {
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
		//��ȡǰ̨ҳ�洫����̨��ԭʼ����
		String oldpass = request.getParameter("oldpass");
		//��õ�ǰ��½�û�����ʵ����
		String password=request.getSession().getAttribute("password").toString();
		if(oldpass.equals(password)){
			//����ƥ����
			//��ȡǰ̨ҳ�洫������������
			String newpass = request.getParameter("newpass");
			UserOpe userope = UserOpe.getInstance();
			String userid=request.getSession().getAttribute("userid").toString();
			boolean b = userope.updatePass(userid, newpass);
			if(b){
				//�����޸ĳɹ�
				//��session�е��û��������,ͬʱ���½��û���Ϣ���õ�session��
				request.getSession().setAttribute("password", newpass);
				request.setAttribute("success", "�����޸ĳɹ�");
				request.getRequestDispatcher("pages/front/updatePassword.jsp").forward(request, response);
			}else{
				//�����޸�ʧ��
				request.setAttribute("error", "�����޸�ʧ��");
				request.getRequestDispatcher("pages/front/updatePassword.jsp").forward(request, response);
			}
		}else{
			//ԭʼ�������
			request.setAttribute("error", "ԭʼ���벻��ȷ");
			request.getRequestDispatcher("pages/front/updatePassword.jsp").forward(request, response);
		}
		
	}

}
