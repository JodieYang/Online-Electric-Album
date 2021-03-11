package mat.java.beans;
import java.sql.Date;
public class Album {
	/** 该相册的id */
	private int id;
	/** 该相册的名字 */
	private String name = "";
	/** 该相册创建的时间 */
	private Date createtime;
	/** 该相册的描述 */
	private String description = "";
	/** 该相册的照片数量 */
	//private int num;
	/* 该照片所属的用户的id*/
	private String userid="";
//jj:可以多加一个属性，相册所属的user
	public Album() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

/*	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}*/
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid=userid;
	}

}
