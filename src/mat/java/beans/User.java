package mat.java.beans;

public class User {

	private String id = "";
	/** 用户的帐号 */
	private String name = "";
	/**用户密码 */
	private String pass = "";
	private int uflag;

	public User() {  //公共无参构造函数
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getUflag()
	{
		return uflag;
	}
	public void setUflag(int uflag)
	{
		this.uflag=uflag;
	}

}
