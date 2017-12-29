package cn.trusteye.work.config;


import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 得到应用程序路径工具类
 * @author rayman
 *
 */
public class WorkPathUtil {
	private static final String LOG_DIR = "logs";
	private static final String TEMP_DIR = "temp";
	private static final String CONFIG_DIR = "config";
	public static final long FREE_DPACE_LIMIT = 200*1024*1024;
	
	static{
		File file =new File(TEMP_DIR);
		if  (!file.exists()  && !file .isDirectory())
		{       
		    file.mkdir();    
		}
		file = new File(LOG_DIR);
		if  (!file.exists()  && !file .isDirectory())      
		{       
		    file.mkdir();
		}				
	}

	/**
	 * 得到类路径
	 * @return
	 */
	public static String getClassPath(){
		String path= System.getProperty("java.class.path");

		int lastIndex = path.indexOf(System.getProperty("path.separator"));
		if(lastIndex < 0){
			return path;
		}
		return path.substring(0, lastIndex);
	}
	
	/**
	 * 得到工作区路径
	 * @return
	 */
	public static String getWorkspacePath()  {
		URL urlPath = WorkPathUtil.class.getClassLoader().getResource("");
		File file = null;
		try {
			file = new File(urlPath.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String classPath = file.getAbsolutePath();
		return classPath;
	}
	
	/**
	 * 获得配置文件目录
	 */
	public static String getConfigPath(){
		return getWorkspacePath() + File.separator + CONFIG_DIR;
	}
	
	/**
	 * 得到临时工作目录
	 * @return
	 */
	public static String getTempPath(){
		return getWorkspacePath()+ File.separator+TEMP_DIR;
	}

	/**
	 * 获得日志文件目录
	 * @return
	 */
	public static String getLogPath() {
		return getWorkspacePath()+ File.separator+LOG_DIR;
	}

	/**
	 * 获得输出目录
	 * @return
	 */
	public static String getOutputPath(){
		String dir = PropConfig.getInstance().getOutputDir()+ File.separator;
		return dir;
	}
	

}
