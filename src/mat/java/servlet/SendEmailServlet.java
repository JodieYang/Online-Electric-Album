package mat.java.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mat.java.util.*;


/**
 * Servlet implementation class SendEmailServlet
 */
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
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
		System.out.println("���䷢�͹���");
		try {
			String useremail = request.getParameter("useremail");
			JavaMailUtil.receiveMailAccount = useremail; // ���û���������䷢���ʼ�
 
			// 1�������������ã�������������������Ĳ�������
			Properties props = new Properties();
			// ����debug����
			props.setProperty("mail.debug", "true");
			// ���ͷ�������Ҫ�����֤
			props.setProperty("mail.smtp.auth", "true");
			// �����Ҽ���������������
			props.setProperty("mail.host", JavaMailUtil.emailSMTPHost);
			// �����ʼ�Э������
			props.setProperty("mail.transport.protocol", "smtp");
 
			// 2���������ô����Ự�������ں��ʼ�����������
			Session session = Session.getInstance(props);
			// ����debug�����Բ鿴��ϸ�ķ���log
			session.setDebug(true);
			// 3������һ���ʼ�
			String code = RandomUtil.getRandom();
			System.out.println("������֤�룺" + code);
			String html = htmlText.html(code);
			MimeMessage message = JavaMailUtil.creatMimeMessage(session, JavaMailUtil.emailAccount,
					JavaMailUtil.receiveMailAccount, html);
 
			// 4������session��ȡ�ʼ��������
			Transport transport = session.getTransport();
			// 5��ʹ�������˺ź������������������emailAccount������message�еķ���������һ�£����򱨴�
			transport.connect(JavaMailUtil.emailAccount, JavaMailUtil.emailPassword);
			// 6�������ʼ�,���������ռ��˵�ַ
			transport.sendMessage(message, message.getAllRecipients());
			// 7���ر�����
			transport.close();
			//  д��session
			request.getSession().setAttribute("code", code);
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("error", "�ʼ�����ʧ��");
		}

	}

}
