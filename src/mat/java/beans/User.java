package mat.java.beans;

public class User {

	private String id = "";
	/** �û����ʺ� */
	private String name = "";
	/**�û����� */
	private String pass = "";
	private int uflag;

	public User() {  //�����޲ι��캯��
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
