package mat.java.beans;
import java.sql.Date;
public class Album {
	/** ������id */
	private int id;
	/** ���������� */
	private String name = "";
	/** ����ᴴ����ʱ�� */
	private Date createtime;
	/** ���������� */
	private String description = "";
	/** ��������Ƭ���� */
	//private int num;
	/* ����Ƭ�������û���id*/
	private String userid="";
//jj:���Զ��һ�����ԣ����������user
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
