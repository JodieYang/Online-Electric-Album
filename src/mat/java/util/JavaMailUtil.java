package mat.java.util;

import java.io.IOException;
import java.util.Date;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class JavaMailUtil {
		// �����˵�����-�޸�Ϊ�Լ���
		public static String emailAccount = "695678541@qq.com";
		// ������������Ȩ��-�޸�Ϊ�Լ���
		public static String emailPassword = "mhhnxidvbcwebeba";
		// ��������������ַ-�޸�Ϊ�Լ���
		public static String emailSMTPHost = "smtp.qq.com";
		//  �ռ�������
		public static String receiveMailAccount = "";
		
		public static  MimeMessage creatMimeMessage(Session session,String sendMail,String receiveMail,String html) throws MessagingException, IOException {
			// 1������һ���ʼ�����
			MimeMessage message = new MimeMessage(session);
			// 2��From��������
			message.setFrom(new InternetAddress(sendMail, "�������ǳ�", "UTF-8"));
			// 3��To:�ռ��ˣ��������Ӷ���ռ��ˣ����ͻ������ͣ�
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "����", "UTF-8"));
			// 4��Subject:�ʼ�����
			message.setSubject("������֤","UTF-8");
			// 5��Content:�ʼ����ģ�����ʹ��Html��ǩ��
			message.setContent(html,"text/html;charset=UTF-8");
			// 6�����÷���ʱ��
			message.setSentDate(new Date());
			// 7����������
			message.saveChanges();
			// 8�������ʼ������ڱ���
			//OutputStream out = new FileOutputStream("D://MyEmail" + UUID.randomUUID().toString() + ".eml");
			//message.writeTo(out);
			//out.flush();
			//out.close();
			return message;
		} 

}
