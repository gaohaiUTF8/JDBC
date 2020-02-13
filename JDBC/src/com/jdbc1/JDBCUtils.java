package com.jdbc1;
/**
 * �������ڷ�װJDBC���ӵĹ�����
 * 
 * @author Administrator
 *����
 *1.��ȡ����
 *2.�ͷ���Դ
 *
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.management.RuntimeErrorException;

import java.sql.Statement;

public class JDBCUtils {
	
	static String user;
	static String password;;
	static String url;
	static String driver;
	
	static {
		try {
			Properties info = new Properties();
			info.load(new FileInputStream("src\\jdbc.properties"));
			
			user = info.getProperty("user");
			password = info.getProperty("password");
			url = info.getProperty("url");
			driver = info.getProperty("driver");
			
			Class.forName(driver);
	}
	catch (Exception e) {
			throw new RuntimeException(e);
	}
	}
	
	/**
	 * ���ܣ���ȡ���õ����Ӷ���
	 * @return ����
	 * @throws Exception
	 */
	
	public static Connection getConnection(){
		
			try {
				return DriverManager.getConnection(url,user,password);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		
		
		
		
	}
	/**
	 * ���ܣ��ͷ���Դ
	 * 
	 * @param set
	 * @param statement
	 * @param connection
	 * @throws SQLException
	 */
	public static void close(ResultSet set,Statement statement,Connection connection){
		try {
			
			if (set!=null) {
				set.close();		
			}
			if (statement!=null) {
				statement.close();	
			}
			if (connection !=null) {
				connection.close();	
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
	

}
