package com.jdbc1;
/**
 * 此类用于演示Blob类型的存取
 * 
 * @author Administrator
 *
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class TestBlob {
	
	//存图片
	@Test
	public void testSave() throws Exception {
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		
		//执行修改语句
		PreparedStatement statement = connection.prepareStatement("update customers set photo =? where id=?");
		statement.setBlob(1, new FileInputStream("src\\1.jpg"));
		statement.setInt(2, 1);
		statement.executeUpdate();
	
		//关闭连接
		JDBCUtils.close(null,statement,connection);
	}
	
	//读图片
	@Test
	public void testRead() throws Exception {
		//获取连接
		Connection connection = JDBCUtils.getConnection();
		
		//执行修改语句
		PreparedStatement statement = connection.prepareStatement("select photo from customers where id=1");
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			InputStream inputStream = set.getBinaryStream("photo");
			
			FileOutputStream fos = new FileOutputStream("src\\1.jpg");
			
			int len;
			byte[] buffer =new byte[1024];
			while ((len=inputStream.read(buffer))!= -1) {
				fos.write(buffer,0,len);	
			}
			fos.close();
			inputStream.close();

		}
	
		//关闭连接
		JDBCUtils.close(set,statement,connection);
	}
}
