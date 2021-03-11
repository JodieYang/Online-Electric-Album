package mat.java.ope;
import java.util.ArrayList;
import java.util.List;
import mat.java.db.DBOperate;
import mat.java.beans.*;
import mat.java.ope.AlbumOpe;
import mat.java.beans.Album;
public class PhotoOpe {

	private static PhotoOpe photoope = null;
	private DBOperate operate = null;

	private PhotoOpe() {
		operate = DBOperate.getInstance();
	}       //jj:照片操作类中，也有一个数据库操作类的对象，这个对象是我们写的封装数据库

	/**
	 * 该方法获得一个PhotoBiz实例
	 * 
	 * @return photobiz
	 */
	public static PhotoOpe getInstance() {
		if (photoope == null) {
			photoope = new PhotoOpe();
		}
		return photoope;
	}


	

	//仅有添加照片搞好了，其他还没弄
	public boolean addPhoto(Photo photo) {
		boolean flag = false;
		String sql = "INSERT INTO photos(pid,pname,puploadtime,pdescription,albumid,userid,path) values("
				+ photo.getPid()
				+ ",'"
				+ photo.getPname()
				+ "','"
				+ photo.getPuploadtime()
				+ "','"
				+ photo.getPdescription()  
				+ "',"
				+ photo.getAlbumid() + ",'" + photo.getUserid() + "','"+photo.getPath()+"')";
		try {
		flag = operate.executeUpdate(sql); 
		}  //这里面有照片所属的相册，所以相册记录里也应该有所属于的用户
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	public int getTotalPhoto(String userid,int albumid)
	{
		int totalPhoto=0;
		String sql="select count(*) from photos where userid='"+userid+"' and albumid='"+albumid+"'";
		try {
			totalPhoto=operate.executeQuery_PhotoNum(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return totalPhoto;
	}
	public int getMaxPid(String userid,int albumid)
	{
		int maxPid=0;
		String sql="select max(pid) from photos where userid='"+userid+"' and albumid='"+albumid+"'";
		try {
			maxPid=operate.executeQuery_PhotoNum(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return maxPid;
	}
	public List<Photo> findByPid(String userid,int albumid,int pid) {
		
		String sql = "select * from photos where pid="+pid+" and albumid="+albumid+" and userid='"+userid+"'";
		List<Photo> list=new ArrayList<Photo>();
		try {
			
			list=operate.executeQuery_Photo(sql);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public List<Photo> findByAid(String userid,int albumid,int pageSize,int currentPage) {
		int index=(currentPage-1)*pageSize;
		String sql="select top "+String.valueOf(pageSize)+
				" * from photos where userid='"+userid
				+ "' and albumid="+String.valueOf(albumid)+
				" and pid not in(select top "+String.valueOf(index)+
				" pid from photos where userid='"+userid
				+ "' and albumid="+String.valueOf(albumid)+")";
		/*String sql = "select top "+String.valueOf(pageSize)+
				" * from photos where userid='"+userid+
				"' and albumid="+String.valueOf(albumid)+
				" and pid not in(select top "+String.valueOf(index)+
				" pid from photos where userid='"+userid+
				"' and albumid="+String.valueOf(albumid)+")";*/
		List<Photo> list=new ArrayList<Photo>();
		try {
			
			list=operate.executeQuery_Photo(sql);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int findByPname(String userid,int albumid,String pname)
	{
		int num=0;
		String sql="select count(*) from photos where pname='"+pname+
				"' and albumid="+albumid+
				" and userid='"+userid+"'";
		try {
			num=operate.executeQuery_PhotoNum(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return num;
	}
	public List<Photo> findJustByAid(int albumid) {
		String sql=	"select * from photos where albumid="+albumid;
		List<Photo> list=new ArrayList<Photo>();
		try {
			
			list=operate.executeQuery_Photo(sql);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Photo> findByAname(String aname)
	{
		AlbumOpe albumope=AlbumOpe.getInstance();
		List<Album> list=albumope.findAllAlbumIdByName(aname);
		List<Photo> listp=new ArrayList<Photo>();
		try {
			for(int i=0;i<list.size();i++)
			{
				listp.addAll(this.findJustByAid(list.get(i).getId()));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listp;
	}
public List<Photo> findAll(String userid,int albumid) {
		
		String sql = "select * from photos where albumid="+albumid+" and userid='"+userid+"'";
		List<Photo> list=new ArrayList<Photo>();
		try {
			
			list=operate.executeQuery_Photo(sql);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
public boolean delPhotoByPid(String userid,int albumid,int pid) {
	
	String sql = "delete from photos where pid="+pid+" and albumid="+albumid+" and userid='"+userid+"'";
	CommentOpe commentope=CommentOpe.getInstance();
	boolean flag0=commentope.deleteCommentByPhoto(userid, albumid, pid);
	boolean flag=false;
	try {
		if(flag0)
			flag=operate.executeUpdate(sql);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return flag;
}

public boolean delPhotoByAid(String userid,int albumid) {
	List<Photo> list=this.findJustByAid(albumid);
	boolean flag=true;
	for(int i=0;i<list.size();i++)
	{
		int pid=list.get(i).getPid();
		flag=this.delPhotoByPid(userid, albumid, pid);
		if(flag==false)
			break;
	}
	return flag;
}
	
}
