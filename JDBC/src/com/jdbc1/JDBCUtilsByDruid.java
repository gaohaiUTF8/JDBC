package com.jdbc1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * ����ͨ����³�����ݿ����ӳػ�ȡ���Ӷ���
 * 
 * @author Administrator
 *
 */
public class JDBCUtilsByDruid {
	static DataSource dataSource;
	static {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src\\druid.properties"));
			
			//����һ��ָ�����������ݿ����ӳ�
			dataSource =  DruidDataSourceFactory.createDataSource(properties);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getconnection() throws Exception {
		
		
		//�����ݿ����ӳ��л�ȡ���ö����Ӷ���
		return dataSource.getConnection();
		
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
