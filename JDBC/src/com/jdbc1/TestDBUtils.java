package com.jdbc1;
/**
 * 此类用于演示DBUtils的使用
 * 
 * @author Administrator
 * 功能：封装了和数据库存取相关的一些方法
 * 通用的增删改查等等
 * QueryRunner类：
 * 		update(connection,sql,params): 执行任何增删改语句
 * 		query(connection, sql , ResultSetHandler , params):执行任何查询语句
 * ResultSetHandler：
 * 		BeanHandler:将结果的第一行，封装成对象并返回
 * 		BeanListHandler：将结果集中的所有行，封装成对象的集合，并返回
 * 		ScalarHandler：将结果集中的第一行第一列，以Object形式返回
 *
 */

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

public class TestDBUtils {
	@Test
	public void testUpdate() throws Exception {
		
		//1.获取连接
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		//2、执行增删改
		QueryRunner queryRunner = new QueryRunner();
		int update = queryRunner.update(connection, "update stuinfo set name =? where id =2", "baga");
		System.out.println(update>0?"success":"falure");
		
		//3、关闭连接
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	
	
	@Test
	public void testQuerySingle() throws Exception {
		
		//1.获取连接
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		//2、执行增删改
		QueryRunner queryRunner = new QueryRunner();
		Students students = queryRunner.query(connection, "select * from stuinfo where id =?",new BeanHandler<>(Students.class),2);
		System.out.println(students);
		//3、关闭连接
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	
	@Test
	public void testQueryMulti() throws Exception {
		
		//1.获取连接
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		//2、执行增删改
		QueryRunner queryRunner = new QueryRunner();
		List<Students> list = queryRunner.query(connection, "select * from stuinfo",new BeanListHandler<>(Students.class));
		for(Students students : list) {
			System.out.println(students);
		}
		//3、关闭连接
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	
	
		@Test
		public void testScalar() throws Exception {
		//查单个值
			//1.获取连接
			Connection connection = JDBCUtilsByDruid.getconnection();
			
			//2、执行增删改
			QueryRunner queryRunner = new QueryRunner();
			Object query = queryRunner.query(connection, "select count(*) from stuinfo",new ScalarHandler());
			
			System.out.println(query);
			
			//3、关闭连接
			JDBCUtilsByDruid.close(null, null, connection);
	}


}
