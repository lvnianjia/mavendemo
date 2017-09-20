package com.lnj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUitl {
	private static final String PATH = "D:/Work/workspace/hongmaoyun/config/src/main/resources/db.properties";
	private static final String DRIVER = "driver";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String PASS = "pass";
	private static Properties properties;
	public static void main(String[] args) {
		//通过类加载的资源地址和类在同一个文件夹
		java.net.URL resource = DbUitl.class.getResource("");
		//通过类加载器加载的资源,在bin目录下
		resource=DbUitl.class.getClassLoader().getResource(".");
		System.out.println(resource);
	}
	
	static{
		//获取配置对象
		properties=new Properties();
		
		//加载配置文件
		File file = new File(PATH);
		try {
			InputStream inStream = new FileInputStream(file);
			properties.load(inStream);
		} catch (IOException e) {
			ErrorUtil.doException(e);
		}
	}

	/**
	 * 关闭资源
	 * @param set
	 * @param conn
	 * @param statement
	 */
	public static void close(
			ResultSet set, Connection conn, PreparedStatement statement) {
		try {
			closeResouce(set);
			closeResouce(statement);
			closeResouce(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 因为所有可以关闭的资源都实现了AutoCloseable接口
	 * 因此，此方法可以关闭所有的需要关闭的资源
	 * @param ac
	 * @throws Exception
	 */
	private static void closeResouce(AutoCloseable ac) throws Exception{
		if(ac!=null)ac.close();
	}

	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConn() {
		try {
			Class.forName(properties.getProperty(DRIVER));
			return DriverManager.
					getConnection(properties.getProperty(URL), properties.getProperty(USER), properties.getProperty(PASS));
		} catch (SQLException | ClassNotFoundException e) {
			ErrorUtil.doException(e);
		}
		return null;
	}

}
