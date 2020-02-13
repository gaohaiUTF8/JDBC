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
 * 此类通过德鲁伊数据库连接池获取连接对象
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
			
			//创建一个指定参数的数据库连接池
			dataSource =  DruidDataSourceFactory.createDataSource(properties);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getconnection() throws Exception {
		
		
		//从数据库连接池中获取可用度连接对象
		return dataSource.getConnection();
		
	}
	
	/**
	 * 功能：释放资源
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
