package com.jdbc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * �������ڷ�װͨ�õ���ɾ�Ĳ鷽��
 * @author Administrator
 *����
 *1��ִ����ɾ��
 *2��ִ�в�ѯ
 */
public class CRUDUtils {
	/**
	 * ���ܣ���ɾ��
	 * ������κα���κ���ɾ�����
	 * @return
	 * @throws Exception 
	 */
	public static int update(String sql,Object...params){
		
		try {
			//1.��ȡ����
			Connection connection = JDBCUtilsByDruid.getconnection();
			
			//2.ִ��sql���
			PreparedStatement statement =connection.prepareStatement(sql);
			for(int i = 0;i<params.length;i++) {
				statement.setObject(i+1, params[i]);
			}
			return statement.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 
	 * ֻ���stuinfo����ѯ����
	 * @throws Exception 
	 */
	
	public static Students querySingle(String sql,Object...params) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1����ȡ����
			connection = JDBCUtilsByDruid.getconnection();
			
			//2��ִ�в�ѯ
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			if(set.next()) {
				int id = set.getInt("id");
				String name = set.getString("name");
				Students students = new Students(id,name);
				return students;
			}
			return null;
		
		} catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JDBCUtilsByDruid.close(set, statement, connection);
		}
		
	}
	
	
	/**
	 * 
	 * ֻ���stuinfo����ѯ����
	 * @throws Exception 
	 */
	
	public static List<Students> queryMulti(String sql,Object...params) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1����ȡ����
			connection = JDBCUtilsByDruid.getconnection();
			
			//2��ִ�в�ѯ
			statement = connection.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				statement.setObject(i+1, params[i]);
			}
			set = statement.executeQuery();
			List<Students> list = new ArrayList<>();
			while(set.next()) {
				int id = set.getInt("id");
				String name = set.getString("name");
				Students students = new Students(id,name);
				list.add(students);		
			}
			return list;
		
		} catch (Exception e) {
			throw new RuntimeException();
		}finally {
			JDBCUtilsByDruid.close(set, statement, connection);
		}
		
	}

}
