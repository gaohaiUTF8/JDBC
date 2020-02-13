package com.jdbc1;
/**
 * ����������ʾ��³�����ݿ����ӳص�ʹ��
 * @author Administrator
 *��Դ��ܵ�ʹ�ò���
 *1����jar��
 *2��������
 *3�����÷���ʹ��
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement.DeferredSegmentCreation;

public class TestDataSource {
	@Test
	public void testDataSource() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileInputStream("src\\druid.properties"));
		
		//����һ��ָ�����������ݿ����ӳ�
		DataSource dataSource =  DruidDataSourceFactory.createDataSource(properties);
		
		//�����ݿ����ӳ��л�ȡ���ö����Ӷ���
		Connection connection = dataSource.getConnection();
		
		System.out.println("���ӳɹ�");
		
		//�ر�����
		connection.close();
		
	}

}
