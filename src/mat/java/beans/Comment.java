package mat.java.beans;
import java.util.Date;
public class Comment {
	private int cid;
	private String content;
	private Date commenttime;
	private String cuserid;
	private String puserid;
	private int albumid;
	private int pid;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Date getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getAlbumid() {
		return albumid;
	}
	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}
	
	public String getCuserid() {
		return cuserid;
	}
	public void setCuserid(String cuserid) {
		this.cuserid = cuserid;
	}
	
	public String getPuserid() {
		return puserid;
	}
	public void setPuserid(String puserid) {
		this.puserid =puserid ;
	}
}
