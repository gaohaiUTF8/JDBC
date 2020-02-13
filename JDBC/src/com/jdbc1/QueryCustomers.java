package com.jdbc1;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

public class QueryCustomers {
	
	@Test
	public void testqueryCustomers() throws Exception {
		
		Connection connection = JDBCUtilsByDruid.getconnection();
		
		QueryRunner queryRunner = new QueryRunner();
		Customers customers = queryRunner.query(connection, "select id,name,email,birth  FROM customers WHERE id =?",new BeanHandler<>(Customers.class),1);
		
		System.out.println(customers);
		
		JDBCUtilsByDruid.close(null, null, connection);
	}
	
	

}
