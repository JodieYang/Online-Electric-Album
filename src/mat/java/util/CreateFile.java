package mat.java.util;
import java.io.File;

public class CreateFile {
	public CreateFile()
	{}
	public static boolean createfile(String path)
	{
		//System.out.println(path);
		File f=new File(path);
		if(!f.exists())
		{
			return f.mkdirs();
		}
		return true;
	}
}
