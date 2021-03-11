package mat.java.ope;
import java.util.List;
import java.util.ArrayList;
import mat.java.db.DBOperate;
import mat.java.beans.Comment;
public class CommentOpe {

	private static CommentOpe commentope = null;
	private DBOperate operate = null;

	private CommentOpe() {
		operate = DBOperate.getInstance();
	}       //jj:照片操作类中，也有一个数据库操作类的对象，这个对象是我们写的封装数据库

	/**
	 * 该方法获得一个PhotoBiz实例
	 * 
	 * @return photobiz
	 */
	public static CommentOpe getInstance() {
		if (commentope == null) {
			commentope = new CommentOpe();
		}
		return commentope;
	}
	public List<Comment> findAllComment(String puserid,int albumid,int pid)
	{
		List<Comment> list=new ArrayList<Comment>();
		String sql="select * from comments where puserid='"+puserid+
				"' and albumid="+albumid+
				" and pid="+pid;
		try {
				list=operate.executeQuery_Comment(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return list;
	}
	public int getMaxCid(String puserid,int albumid,int pid)
	{
		int maxCid=0;
		String sql="select max(cid) from comments where puserid='"+puserid+
				"' and albumid="+albumid+
				" and pid="+pid;
		try {
			maxCid=operate.executeQuery_CommentNum(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return maxCid;
	}
	public boolean addComment(Comment comment)
	{
		boolean flag=false;
		String sql="insert into comments(cid,content,commenttime,cuserid,puserid,albumid,pid) values("+
		comment.getCid()+",'"
		+comment.getContent()+"','"
		+comment.getCommenttime()+"','"
		+comment.getCuserid()+"','"
		+comment.getPuserid()+"',"
		+comment.getAlbumid()+","
		+comment.getPid()+")";
		try {
			flag=operate.executeUpdate(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return flag;
	}
	public boolean deleteCommentByPhoto(String puserid,int albumid,int pid)
	{
		int maxCid=this.getMaxCid(puserid, albumid, pid);
		String sql="delete from comments where puserid='"+puserid+"' and albumid="+albumid+" and pid="+pid;
		boolean flag=false;
		try {
			if(maxCid!=0)
			{
				flag=operate.executeUpdate(sql);
			}
			else
				flag=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
}
