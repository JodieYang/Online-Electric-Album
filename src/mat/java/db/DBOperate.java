package mat.java.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Date;

import java.util.ArrayList;
import mat.java.beans.*;



public class DBOperate {

	private static DBOperate operate = null;

	private DBOperate() {
		// ˽�еĹ��췽����Ϊ����ʵ�ֵ���ģʽ��׼��
	}

	/**
	 * ���һ��ControlDB�����ʵ��
	 * 
	 * @return
	 */
	public static DBOperate getInstance() {
		if (operate == null) {
			operate = new DBOperate();
		}
		return operate;
	}
	
	//����ִ��sql���Ĵ��룬ִ�����еĲ�ѯ��������صļ�¼
	/**
	 * ִ��select���
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public User executeQuery_CheckLogin(String sql) throws Exception {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		User user=null;
		if (rs!=null&&rs.next()) {
			user=new User();
			int i = 1;
			user.setId(rs.getString(i++));
			user.setName(rs.getString(i++));
			user.setPass(rs.getString(i++));
			user.setUflag(rs.getInt(i++));
		}
		DBClose.closeQuery(con,stmt,rs);
		return user;
	}
	
	public boolean executeQuery_CheckRegister(String sql) throws Exception {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		boolean flag=false;
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		if (rs!=null&&rs.next()) {
			flag=true; //���������Ҫ��Ϊ���ע��ʱ�Ƿ����ͬ��
		}
		DBClose.closeQuery(con,stmt,rs);
		return flag;
	}
	
	public int executeQuery_UserNum(String sql)throws Exception
	{
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		int total=0;
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		if (rs!=null&&rs.next()) {
			total=rs.getInt(1);
		}
		DBClose.closeQuery(con,stmt,rs);
		return total;
	}
	
	public int executeQuery_AlbumNum(String sql) throws Exception {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		int total=0;
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		if (rs!=null&&rs.next()) {
			total=rs.getInt(1);
		}
		DBClose.closeQuery(con,stmt,rs);
		return total;
	}

	public int executeQuery_PhotoNum(String sql) throws Exception {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		int total=0;
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		if (rs!=null&&rs.next()) {
			total=rs.getInt(1);
		}
		DBClose.closeQuery(con,stmt,rs);
		return total;
	}
	
	public int executeQuery_CommentNum(String sql) throws Exception {
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		int total=0;
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		if (rs!=null&&rs.next()) {
			total=rs.getInt(1);
		}
		DBClose.closeQuery(con,stmt,rs);
		return total;
	}
	

	
	public List<Album> executeQuery_Album(String sql)throws Exception
	{
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		List<Album> list=new ArrayList<Album>();
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		while (rs!=null&&rs.next()) {
			Album album=new Album();
			//����ǰ��ѯ��������ŵ�������
			album.setId(rs.getInt("id"));
			album.setName(rs.getString("name"));
			album.setCreatetime(rs.getDate("createtime"));
			album.setDescription(rs.getString("description"));
			album.setUserid(rs.getString("userid"));
			list.add(album);
			}
		DBClose.closeQuery(con,stmt,rs);
		return list;
	}
	
	
	public List<Photo> executeQuery_Photo(String sql)throws Exception
	{
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		List<Photo> list=new ArrayList<Photo>();
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		
		while (rs!=null&&rs.next()) {
			//����ǰ��ѯ��������ŵ�������
			int i=1;
			Photo photo=new Photo();
			photo.setPid(rs.getInt(i++));
			photo.setPname(rs.getString(i++));
			photo.setPuploadtime(rs.getDate(i++));
			photo.setPdescription(rs.getString(i++));
			photo.setAlbumid(rs.getInt(i++));
			photo.setUserid(rs.getString(i++));
			photo.setPath(rs.getString(i++));
			list.add(photo);
			}
		DBClose.closeQuery(con,stmt,rs);
		return list;
	}
	
	public List<Comment> executeQuery_Comment(String sql)throws Exception
	{
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		List<Comment> list=new ArrayList<Comment>();
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		
		while (rs!=null&&rs.next()) {
			//����ǰ��ѯ��������ŵ�������
			int i=1;
			Comment comment=new Comment();
			comment.setCid(rs.getInt(i++));
			comment.setContent(rs.getString(i++));
			comment.setCommenttime(rs.getDate(i++));
			comment.setCuserid(rs.getString(i++));
			comment.setPuserid(rs.getString(i++));
			comment.setAlbumid(rs.getInt(i++));
			comment.setPid(rs.getInt(i++));
			list.add(comment);
			}
		DBClose.closeQuery(con,stmt,rs);
		return list;
	}
	
	
	public List<User> executeQuery_User(String sql)throws Exception
	{
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		List<User> list=new ArrayList<User>();
		try {
			con = ConnectionFactory.getConnection();  //jj:ִ��һ��sql���ʱ�����������ݿ�
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);  //jj:������ִ��һ��sql��䣬����һ����¼����װ��rs��
		} catch (SQLException e) {
			throw e;
		}
		while (rs!=null&&rs.next()) {
			User user=new User();
			//����ǰ��ѯ��������ŵ�������
			user.setId(rs.getString("id"));
			user.setName(rs.getString("username"));
			user.setPass(rs.getString("password"));
			user.setUflag(rs.getInt("uflag"));
			list.add(user);
			}
		DBClose.closeQuery(con,stmt,rs);
		return list;
	}
	
	public boolean executeUpdate(String sql) throws Exception {
		boolean flag = false;
		Connection con = null;
		Statement stmt = null;
		try {
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();  //jj:������Ƿ�װ�����ڲ���һ�������������Լ�д��
			int row = stmt.executeUpdate(sql);
			flag = row > 0 ? true : false;
		} catch (SQLException ex) {
			ex.printStackTrace();
			flag = false;
		} finally {
			DBClose.closeUpdate(con,stmt); //jj:�ر������������𣿣���
		}
		return flag;
	}
	
//��δ����
	public int executeUpdateNum(String sql) throws Exception {
		Connection con = null;
		Statement stmt = null;
		int row=0;
		try {
			con = ConnectionFactory.getConnection();
			stmt = con.createStatement();  //jj:������Ƿ�װ�����ڲ���һ�������������Լ�д��
			row = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBClose.closeUpdate(con,stmt); //jj:�ر������������𣿣���
		}
		return row;
	}

}
