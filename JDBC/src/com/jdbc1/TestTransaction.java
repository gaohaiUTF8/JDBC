package com.jdbc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;

/**
 * ����������ʾ����
 * 1������������
 * ȡ�������Զ��ύ�Ĺ���
 * setAutoCommit(false);
 * 2����д��������һ��sql���
 * 
 * 3����������
 * commit()�ύ
 * rollback()�ع�
 * @author Administrator
 *
 *
 *ϸ�ڣ�Ҫ������������Ӷ���ͻ�ȡ��������Ӷ��������ͬһ��������������Ч
 *
 */
public class TestTransaction {
	
	/**
	 *	�����ڲ��Բ�������
	 *
	 */
	@Test
	public void testNoTransaction() throws Exception {
		//��ȡ����
		Connection connection = JDBCUtils.getConnection();
		
		//ִ��sql���
		String sql = "update employees set salary=salary-? where employee_id =?"; 
		PreparedStatement statement = connection.prepareStatement(sql);
		
		//����1  Ա�����100�Ĺ���-5000
		statement.setDouble(1, 5000);
		statement.setInt(2, 100);
		statement.executeUpdate();
		
		int i= 1/0;//ģ���쳣
		
		//����2  Ա�����101�Ĺ���-5000
				statement.setDouble(1, 5000);
				statement.setInt(2, 101);
				statement.executeUpdate();
		
				
		//�ͷ���Դ
				JDBCUtils.close(null, statement, connection);
	}
	
	
	
	
	//-----------------ʹ������-----------------------
	@Test
	public void testTransaction(){
		Connection connection =null;
		PreparedStatement statement = null;
		try {
			//��ȡ����
			connection = JDBCUtils.getConnection();
			
			//�����ʹ�ò���1:��������
			connection.setAutoCommit(false);
			
			
			//�����ʹ�ò���2��ִ��sql���
			
			String sql = "update employees set salary=salary-? where employee_id =?"; 
			statement = connection.prepareStatement(sql);
					
			//����1  Ա�����100�Ĺ���-5000
			statement.setDouble(1, 5000);
			statement.setInt(2, 100);
			statement.executeUpdate();
				
			int i= 1/0;//ģ���쳣
				
			//����2  Ա�����101�Ĺ���-5000
			statement.setDouble(1, 5000);
			statement.setInt(2, 101);
			statement.executeUpdate();
			
			
			//�����ʹ�ò���3����������
			
			connection.commit();
		} catch (SQLException e) {
			try {
				//�ع�����
				connection.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
		}finally {
			JDBCUtils.close(null, statement, connection);
		}
		
	}
	

}
