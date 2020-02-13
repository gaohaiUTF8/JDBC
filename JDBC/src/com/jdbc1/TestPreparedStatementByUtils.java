package com.jdbc1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;



public class TestPreparedStatementByUtils {
	
	@Test
	public void testPreparedStatement(){
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入待修改的员工编号");
		int employee_id = input.nextInt();
		
		System.out.println("请输入新的员工姓名");
		String name = input.next();
		
		//----------------连接数据库的步骤----------------------------
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = JDBCUtils.getConnection();
			
			//执行修改
			String sql = "update employees set last_name =? where employee_id =? ";
			statement =  connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, employee_id);
			
			int update = statement.executeUpdate();
			
			System.out.println(update>0?"success":"failure");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtils.close(null, statement, connection);
		}
		
		

		
		
	}

}
