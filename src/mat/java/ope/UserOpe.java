package mat.java.ope;
import java.util.ArrayList;
import java.util.List;
import mat.java.beans.*;
import mat.java.db.*;

public class UserOpe {
	private static UserOpe userope = null;
	private DBOperate operate = null;

	private UserOpe() {
		operate = DBOperate.getInstance();
	} //jj：调用ControlDB的工厂方法，获取数据库操作对象，这个对象是自己写的ControlDB类的对象

	/**
	 * 该方法返回一个AdminBiz对象
	 * 
	 * @return adminbiz
	 */
	public static UserOpe getInstance() {
		if (userope == null) {
			userope = new UserOpe();
		}
		return userope;
	}    //jj：工厂方法
	
	public boolean checkUserName(String name)
	{
		boolean flag=false;
		String sql = "SELECT * FROM users WHERE username='" + name  + "'";
		try {
			flag =operate.executeQuery_CheckRegister(sql); //jj:执行sql语句，返回一条记录
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public User checkUser(String name, String pass) {
		User user = new User();
		String sql = "SELECT * FROM users WHERE username='" + name + "' and password='"
				+ pass + "'";     //jj:sql语句
		try {
			user =operate.executeQuery_CheckLogin(sql); //jj:执行sql语句，返回一条记录
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean addUser(String name,String pass,String email)
	{
		boolean flag=false;
		
		String sql="INSERT INTO users(id,username,password,uflag)VALUES('"+email+"','"+name+"','"+pass+"',0)";
		try {
				flag=operate.executeUpdate(sql); //jj:执行sql语句，返回一条记录
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updatePass(String userid, String newpass) {
		String sql = "update users set password='"+newpass+"' where id='"+userid+"'";
		boolean flag=false;
		try {
			flag=operate.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getTotalPage(int pageSize) {
		String sql = "select count(*) from users";
		int totalNum = 0;
		int totalPage = 0;
		try {
			totalNum=operate.executeQuery_UserNum(sql);
			if(totalNum%pageSize == 0){
				totalPage = totalNum/pageSize;
			}else{
				totalPage = totalNum/pageSize+1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalPage;
	}
	

	/**
	 * 查询出所有用户
	 */
	public List<User> findAllUser(int pageSize,int currentPage) {
		int index=(currentPage-1)*pageSize;
		String sql="select top "+String.valueOf(pageSize)+
				" * from users where id not in(select top "+String.valueOf(index)+
				" id from users)";
		List<User> list = new ArrayList<User>();
		try {
			list=operate.executeQuery_User(sql);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return list;
	}
	
	public boolean delUser(String userid) {
		boolean flag=false;
		String sql = "delete from users where id='"+userid+"'";
		AlbumOpe albumope=AlbumOpe.getInstance();
		int num=albumope.getTotalAlbum(userid);
		if(num>0)
		{
			boolean flag0=albumope.delAlbumByUid(userid);
			if(flag0)
			{
				try 
				{
					flag=operate.executeUpdate(sql);
				} 
				catch (Exception e) {
						// TODO Auto-generated catch block		
						e.printStackTrace();
				}
			}
		}
		else
		{
			try {
				flag=operate.executeUpdate(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block		
					e.printStackTrace();
				}
		}
		return flag;
	}
	
	public boolean setAlbum(String userid)
	{
		boolean flag=false;
		String sql="update users set uflag=1 where id='"+userid+"'";
		try {
			flag=operate.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
		
