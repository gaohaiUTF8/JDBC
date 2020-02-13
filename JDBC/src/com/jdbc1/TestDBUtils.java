package com.jdbc1;
/**
 * ����������ʾDBUtils��ʹ��
 * 
 * @author Administrator
 * ���ܣ���װ�˺����ݿ��ȡ��ص�һЩ����
 * ͨ�õ���ɾ�Ĳ�ȵ�
 * QueryRunner�ࣺ
 * 		update(connection,sql,params): ִ���κ���ɾ�����
 * 		query(connection, sql , ResultSetHandler , params):ִ���κβ�ѯ���
 * ResultSetHandler��
 * 		BeanHandler:������ĵ�һ�У���װ�ɶ��󲢷���
 * 		BeanListHandler����������е������У���װ�ɶ���ļ��ϣ�������
 * 		ScalarHandler����������еĵ�һ�е�һ�У���Object��ʽ����
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
		
		//1.��ȡ����
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		//2��ִ����ɾ��
		QueryRunner queryRunner = new QueryRunner();
		int update = queryRunner.update(connection, "update stuinfo set name =? where id =2", "baga");
		System.out.println(update>0?"success":"falure");
		
		//3���ر�����
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	
	
	@Test
	public void testQuerySingle() throws Exception {
		
		//1.��ȡ����
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		//2��ִ����ɾ��
		QueryRunner queryRunner = new QueryRunner();
		Students students = queryRunner.query(connection, "select * from stuinfo where id =?",new BeanHandler<>(Students.class),2);
		System.out.println(students);
		//3���ر�����
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	
	@Test
	public void testQueryMulti() throws Exception {
		
		//1.��ȡ����
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		//2��ִ����ɾ��
		QueryRunner queryRunner = new QueryRunner();
		List<Students> list = queryRunner.query(connection, "select * from stuinfo",new BeanListHandler<>(Students.class));
		for(Students students : list) {
			System.out.println(students);
		}
		//3���ر�����
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	
	
		@Test
		public void testScalar() throws Exception {
		//�鵥��ֵ
			//1.��ȡ����
			Connection connection = JDBCUtilsByDruid.getconnection();
			
			//2��ִ����ɾ��
			QueryRunner queryRunner = new QueryRunner();
			Object query = queryRunner.query(connection, "select count(*) from stuinfo",new ScalarHandler());
			
			System.out.println(query);
			
			//3���ر�����
			JDBCUtilsByDruid.close(null, null, connection);
	}


}
