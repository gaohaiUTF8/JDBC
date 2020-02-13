package com.jdbc1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import java.sql.PreparedStatement;



public class TestPreparedStatement {
	
	@Test
	public void testPreparedStatement() throws Exception{
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("请输入待修改的员工编号");
		int employee_id = input.nextInt();
		
		System.out.println("请输入新的员工姓名");
		String name = input.next();
		
		Properties info = new Properties();
		info.load(new FileInputStream("src\\jdbc.properties"));
		
		String user = info.getProperty("user");
		String password = info.getProperty("password");
		String url = info.getProperty("url");
		String driver = info.getProperty("driver");
		
		Class.forName(driver);
		
		Connection connection = DriverManager.getConnection(url,user,password);
		
		String sql = "update employees set last_name =? where employee_id =? ";
		PreparedStatement statement =  connection.prepareStatement(sql);
		statement.setString(1, name);
		statement.setInt(2, employee_id);
		
		int update = statement.executeUpdate();
		
		System.out.println(update>0?"success":"failure");
		
		statement.close();
		connection.close();
		
		
		
		
		
	}

}
