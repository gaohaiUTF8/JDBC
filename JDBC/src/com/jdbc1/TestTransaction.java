package com.jdbc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

/**
 * 此类用于演示事务
 * 1、开启新事物
 * 取消事务自动提交的功能
 * setAutoCommit(false);
 * 2、编写组成事务的一组sql语句
 * 
 * 3、结束事务
 * commit()提交
 * rollback()回滚
 * @author Administrator
 *
 *
 *细节：要求开启事务的连接对象和获取命令的连接对象必须是同一个，否则事务无效
 *
 */
public class TestTransaction {
	
	/**
	 *	此用于测试不用事务
	 *
	 */
	@Test
	public void testNoTransaction() throws Exception {
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		
		//执行sql语句
		String sql = "update employees set salary=salary-? where employee_id =?"; 
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//操作1  员工编号100的工资-5000
		statement.setDouble(1, 5000);
		statement.setInt(2, 100);
		statement.executeUpdate();
		
		int i= 1/0;//模拟异常
		
		//操作2  员工编号101的工资-5000
				statement.setDouble(1, 5000);
				statement.setInt(2, 101);
				statement.executeUpdate();
		
				
		//释放资源
				JDBCUtils.close(null, statement, connection);
	}
	
	
	
	
	//-----------------使用事务-----------------------
	@Test
	public void testTransaction(){
		Connection connection =null;
		PreparedStatement statement = null;
		try {
			//获取连接
			connection = JDBCUtils.getConnection();
			
			//事务的使用步骤1:开启事务
			connection.setAutoCommit(false);
			
			
			//事务的使用步骤2：执行sql语句
			
			String sql = "update employees set salary=salary-? where employee_id =?"; 
			statement = connection.prepareStatement(sql);
					
			//操作1  员工编号100的工资-5000
			statement.setDouble(1, 5000);
			statement.setInt(2, 100);
			statement.executeUpdate();
				
			int i= 1/0;//模拟异常
				
			//操作2  员工编号101的工资-5000
			statement.setDouble(1, 5000);
			statement.setInt(2, 101);
			statement.executeUpdate();
			
			
			//事务的使用步骤3：结束事务
			
			connection.commit();
		} catch (SQLException e) {
			try {
				//回滚事务
				connection.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
		}finally {
			JDBCUtils.close(null, statement, connection);
		}
		
	}
	

}
