package mat.java.util;
import java.io.File;

public class DeleteFile {
	public static boolean deleteFolder(File dirFile)
	{
		if(!dirFile.exists())
		{
			return true;
		}
		else
		{
			if(dirFile.isFile())
			{
				return dirFile.delete();
			}
			else
			{
				for(File file:dirFile.listFiles())
				{
					deleteFolder(file);
				}
			}
			return dirFile.delete();
		}
	}
	/*public static boolean deleteFile(String sPath)
	{
		File file=new File(sPath);
		if(!file.exists())
			return true;
		else if(!file.isFile())
			return false;
		else
		{
			return file.delete();
		}
	}
	public static boolean deleteDirectory(String sPath)
	{
		if(!sPath.endsWith(File.separator))
		{
			sPath=sPath+File.separator;
		} 
		File dirFile=new File(sPath);
		if(!dirFile.exists())
		{
			return true;
		}
		if(!dirFile.isDirectory())
		{
			return false;
		}
		boolean flag=true;
		File[] files=dirFile.listFiles();
		for(int i=0;i<files.length;i++)
		{
			if(files[i].isFile())
			{
				flag=deleteFile(files[i].getAbsolutePath());
				if(!flag)
					break;
			}
			else {
				flag=deleteDirectory(files[i].getAbsolutePath());
				if(!flag)
					break;
			}
		}
		if(!flag)
			return false;
		else return dirFile.delete();
	}*/
}
