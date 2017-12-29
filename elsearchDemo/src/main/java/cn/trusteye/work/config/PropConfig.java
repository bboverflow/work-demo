package cn.trusteye.work.config;

import cn.trusteye.work.pojo.MongoConfig;

import java.io.*;
import java.util.Properties;

/**
 * 配置文件类 应用单例模式 用来动态加载和写入配置文件
 */

public class PropConfig {
	private Properties prop;

	private static final String PROP_FILENAME = "config/config.properties";
	private String outputDir;

	private MongoConfig mongoConfig;

	private String elsearchAddr;
	private int elsearchPort;
	private int handleTimes;
	private int handleNumEverytime;


	private PropConfig() {
		prop = new Properties();
		mongoConfig = new MongoConfig();
		try {
			String configFilename = getConfigFilename();
			System.out.println("加载程序配置文件:"+configFilename);
			InputStreamReader reader = new InputStreamReader(new FileInputStream(configFilename),"UTF-8");
			prop.load(reader);

			outputDir = prop.getProperty("Output_Directory").trim();

			mongoConfig.setMongoAddr(prop.getProperty("mongo.address").trim());
			mongoConfig.setMongoPort(Integer.parseInt(prop.getProperty("mongo.port").trim()));

			mongoConfig.setMongoDbName(prop.getProperty("mongo.dbname").trim());
			mongoConfig.setMongoUserName(prop.getProperty("mongo.username").trim());
			mongoConfig.setMongoPassword(prop.getProperty("mongo.password").trim());
			mongoConfig.setMongoCollection(prop.getProperty("mongo.collection").trim());

			elsearchAddr = prop.getProperty("elasticsearch.address").trim();
			elsearchPort = Integer.parseInt(prop.getProperty("elasticsearch.port").trim());

			handleTimes = Integer.parseInt(prop.getProperty("handle.times").trim());
			handleNumEverytime = Integer.parseInt(prop.getProperty("handle.number.everyTime").trim());

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 静态内部类实现单例
	 */
	private static class InnerClass {
			private static final PropConfig INSTANCE = new PropConfig();
	}

	public static PropConfig getInstance() {
		return InnerClass.INSTANCE;
	}



	public static String getConfigFilename() {
		return WorkPathUtil.getConfigPath()
				+ File.separator + PROP_FILENAME;
	}

	public String getOutputDir() {
		return outputDir;
	}

	public MongoConfig getMongoConfig() {
		return mongoConfig;
	}

	public String getElsearchAddr() {
		return elsearchAddr;
	}

	public int getElsearchPort() {
		return elsearchPort;
	}

	public int getHandleTimes() {
		return handleTimes;
	}

	public int getHandleNumEverytime() {
		return handleNumEverytime;
	}
}
