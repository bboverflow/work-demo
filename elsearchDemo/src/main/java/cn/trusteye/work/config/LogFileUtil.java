package cn.trusteye.work.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;

/**
 * 日志文件类 应用log4j 应用静态内部类实现单例模式
 * 实现加载指定配置文件
 * @author Rayman
 * 
 */

public class LogFileUtil {
	private Logger logger;
	private static final String LOG_FILENAME = "log4j2.xml";

	private LogFileUtil() {
		try {
			/**
			 * 指定日志存放在哪个目录下
			 */
			String logPath = WorkPathUtil.getLogPath();
			System.setProperty("elsearchDemo.logpath", logPath);
			/**
			 * 加载指定日志配置文件
			 */
			ConfigurationSource source = new ConfigurationSource(
					new FileInputStream(WorkPathUtil.getConfigPath()+File.separator+LOG_FILENAME));
			Configurator.initialize(null, source);
			logger = LogManager.getLogger(LogFileUtil.class.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class InnerClass {
		private static final LogFileUtil INSTANCE = new LogFileUtil();
	}

	static public Logger getLogger() {
		return InnerClass.INSTANCE.logger;
	}

}
