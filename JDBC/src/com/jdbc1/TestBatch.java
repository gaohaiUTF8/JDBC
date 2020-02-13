package com.jdbc1;
/**
 * 此类用于演示批处理的使用
 * 案例：向admin表中插入50000行数据
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class TestBatch {
	//没有使用批处理
	@Test
	public void testNobatch() throws Exception {
		
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		
		//执行插入
		PreparedStatement statement = connection.prepareStatement("insert into admin values(null,?,?)");
		for (int i = 1; i < 50000; i++) {
			statement.setString(1, "john"+i);
			statement.setString(2, Integer.toString(i));
		
			statement.executeUpdate();//执行
		}
		
		//释放资源
		JDBCUtils.close(null, statement, connection);
	}
	
	
	
	@Test
	public void testbatch() throws Exception {
	//使用批处理	
		
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		
		//执行插入
		PreparedStatement statement = connection.prepareStatement("insert into admin values(null,?,?)");
		for (int i = 1; i <= 50000; i++) {
			statement.setString(1, "john"+i);
			statement.setString(2, Integer.toString(i));
		
			statement.addBatch();//添加sql语句到批处理包中
			if (i%1000==0) {
				statement.executeBatch();//执行批处理包中的sql语句
				statement.clearBatch();//清空批处理包中的sql语句
			}
		}
		
		//释放资源
		JDBCUtils.close(null, statement, connection);
	}
	
}
