package com.jdbc1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类用于封装通用的增删改查方法
 * @author Administrator
 *功能
 *1、执行增删改
 *2、执行查询
 */
public class CRUDUtils {
	/**
	 * 功能：增删改
	 * 针对于任何表的任何增删改语句
	 * @return
	 * @throws Exception 
	 */
	public static int update(String sql,Object...params){
		
		try {
			//1.获取连接
			Connection connection = JDBCUtilsByDruid.getconnection();
			
			//2.执行sql语句
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
	 * 只针对stuinfo表，查询单条
	 * @throws Exception 
	 */
	
	public static Students querySingle(String sql,Object...params) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1、获取连接
			connection = JDBCUtilsByDruid.getconnection();
			
			//2、执行查询
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
	 * 只针对stuinfo表，查询多条
	 * @throws Exception 
	 */
	
	public static List<Students> queryMulti(String sql,Object...params) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet set = null;
		
		try {
			//1、获取连接
			connection = JDBCUtilsByDruid.getconnection();
			
			//2、执行查询
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
