package com.jdbc1;
/**
 * 此类用于演示德鲁伊数据库连接池的使用
 * @author Administrator
 *开源框架的使用步骤
 *1、导jar包
 *2、看帮助
 *3、调用方法使用
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement.DeferredSegmentCreation;

public class TestDataSource {
	@Test
	public void testDataSource() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("src\\druid.properties"));
		
		//创建一个指定参数的数据库连接池
		DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
		
		//从数据库连接池中获取可用度连接对象
		Connection connection = dataSource.getConnection();
		
		System.out.println("连接成功");
		
		//关闭连接
		connection.close();
		
	}

}
