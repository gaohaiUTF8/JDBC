package com.jdbc1;
/**
 * ����������ʾ�������ʹ��
 * ��������admin���в���50000������
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

public class TestBatch {
	//û��ʹ��������
	@Test
	public void testNobatch() throws Exception {
		
		//��ȡ����
		Connection connection = JDBCUtils.getConnection();
		
		//ִ�в���
		PreparedStatement statement = connection.prepareStatement("insert into admin values(null,?,?)");
		for (int i = 1; i < 50000; i++) {
			statement.setString(1, "john"+i);
			statement.setString(2, Integer.toString(i));
		
			statement.executeUpdate();//ִ��
		}
		
		//�ͷ���Դ
		JDBCUtils.close(null, statement, connection);
	}
	
	
	
	@Test
	public void testbatch() throws Exception {
	//ʹ��������	
		
		//��ȡ����
		Connection connection = JDBCUtils.getConnection();
		
		//ִ�в���
		PreparedStatement statement = connection.prepareStatement("insert into admin values(null,?,?)");
		for (int i = 1; i <= 50000; i++) {
			statement.setString(1, "john"+i);
			statement.setString(2, Integer.toString(i));
		
			statement.addBatch();//���sql��䵽���������
			if (i%1000==0) {
				statement.executeBatch();//ִ����������е�sql���
				statement.clearBatch();//�����������е�sql���
			}
		}
		
		//�ͷ���Դ
		JDBCUtils.close(null, statement, connection);
	}
	
}
