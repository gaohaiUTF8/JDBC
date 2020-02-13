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
		
		System.out.println("��������޸ĵ�Ա�����");
		int employee_id = input.nextInt();
		
		System.out.println("�������µ�Ա������");
		String name = input.next();
		
		//----------------�������ݿ�Ĳ���----------------------------
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = JDBCUtils.getConnection();
			
			//ִ���޸�
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
