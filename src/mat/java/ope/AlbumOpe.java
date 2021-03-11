package mat.java.ope;
import java.util.ArrayList;
import java.util.List;
import mat.java.db.DBOperate;
import mat.java.beans.Album;
import java.sql.Date;

public class AlbumOpe {
	private static AlbumOpe albumope = null;
	private DBOperate operate = null;

	private AlbumOpe() {
		operate = DBOperate.getInstance();
	} //jj������ControlDB�Ĺ�����������ȡ���ݿ������������������Լ�д��ControlDB��Ķ���

	
	public static AlbumOpe getInstance() {
		if (albumope == null) {
			albumope = new AlbumOpe();
		}
		return albumope;
	}    //jj����������
	
	/**
	 * ��ȡ��ǰ�û�������ҳ��
	 */
	public int getTotalPage(String userid,int pageSize) {
		//��ҳ��
		int totalPage = 0;
		//�ܼ�¼����
		int totalNum = 0;
		String sql = "select count(*) from albums where userid='"+userid+"'";
		try {
			totalNum=operate.executeQuery_AlbumNum(sql);
			//�ж��ܼ�¼���ܷ����ÿҳ��С
			if(totalNum % pageSize == 0){
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
	
	public List<Album> findAlbumByUserid(String userid, int pageSize, int currentPage) {
//		String sql = "select * from album where userid=? limit ?,?";
		int index=(currentPage-1)*pageSize;
		String sql="select top "+String.valueOf(pageSize)+
				" * from albums where userid='"+userid+
				"' and id not in(select top "+String.valueOf(index)+
				" id from albums where userid='"+userid+
				"')";
		List<Album> list = new ArrayList<Album>();
		try {
			list=operate.executeQuery_Album(sql);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return list;
	}
	
	
	
	public List<Album> findAlbumByUserid(String userid) {
//		String sql = "select * from album where userid=? limit ?,?";
		
		String sql="select * from albums where userid='"+userid+"'";
		List<Album> list = new ArrayList<Album>();
		try {
			list=operate.executeQuery_Album(sql);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return list;
	}
	
	public List<Album> getAlbum(int id) {
//		String sql = "select * from album where userid=? limit ?,?";
		
		String sql="select * from albums where id="+id;
		List<Album> list = new ArrayList<Album>();
		try {
			list=operate.executeQuery_Album(sql);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return list;
	}

	public int findAlbumByName(String userid,String name)
	{
		int num=0;
		String sql="select count(*) from albums where userid='"+userid+"' and name='"+name+"'";
		try {
		num=operate.executeQuery_AlbumNum(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return num;
	}
	public List<Album> findAlbumIdByName(String userid,String name)
	{
		List<Album> list = new ArrayList<Album>();
		String sql="select * from albums where userid='"+userid+"' and name='"+name+"'";
		try {
		list=operate.executeQuery_Album(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Album> findAllAlbumIdByName(String name)
	{
		List<Album> list = new ArrayList<Album>();
		String sql="select * from albums where name='"+name+"'";
		try {
		list=operate.executeQuery_Album(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}

	
	public int getTotalAlbum(String userid)
	{
		int totalAlbum=0;
		String sql = "select count(*) from albums where userid='"+userid+"'";
		try {
			totalAlbum=operate.executeQuery_AlbumNum(sql);
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return totalAlbum;
	}
	
	public int getMaxAlbum()
	{
		int allAlbum=0;
		String sql = "select max(id) from albums";
		try {
			allAlbum=operate.executeQuery_AlbumNum(sql);
			}
		catch(Exception e){
			e.printStackTrace();
		}
		return allAlbum;
	}
	
	public boolean addAlbum(Album album)
	{
		boolean flag=false;
		int id=album.getId();
		String albumname=album.getName();
		Date createtime=album.getCreatetime();
		String albumdescription=album.getDescription();
		String userid=album.getUserid();
		String sql="INSERT INTO albums(id,name,createtime,description,userid)VALUES("+id+",'"+albumname+"','"+createtime+"','"+albumdescription+"','"+userid+"')";
		try {
				flag=operate.executeUpdate(sql); //jj:ִ��sql��䣬����һ����¼
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean delAlbum(String userid,int albumid) {
		List<Album> list=this.getAlbum(albumid);
		if(list!=null&&"Temp"==list.get(0).getName())
		return false;
		else
		{	
			PhotoOpe photoope=PhotoOpe.getInstance();
			int num=photoope.getTotalPhoto(userid, albumid);
			boolean flag0=photoope.delPhotoByAid(userid, albumid);
			String sql = "delete from albums where id="+albumid+" and userid='"+userid+"'";
			boolean flag=false;
			try {
				if(flag0||num==0)
					flag=operate.executeUpdate(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return flag;
		}
	}
	public boolean delAnyAlbum(String userid,int albumid) {
	
			PhotoOpe photoope=PhotoOpe.getInstance();
			int num=photoope.getTotalPhoto(userid, albumid);
			boolean flag=false;
			String sql = "delete from albums where id="+albumid+" and userid='"+userid+"'";
			if(num>0)
			{
				boolean flag0=photoope.delPhotoByAid(userid, albumid);
				try {
					if(flag0)
						flag=operate.executeUpdate(sql);
				} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	//ֻ��ɾ���û���ʱ����Ե���,userope�Ǳ��Ѿ�ȷ�������������Ϊ0
	public boolean delAlbumByUid(String userid) {
		List<Album> list=this.findAlbumByUserid(userid);
		boolean flag=true;
		for(int i=0;i<list.size();i++)
		{
			int albumid=list.get(i).getId();
			flag=this.delAnyAlbum(userid, albumid);
			if(flag==false)
				break;
		}
		return flag;
	}
}
