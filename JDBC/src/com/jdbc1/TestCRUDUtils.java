package com.jdbc1;

import java.util.List;

import org.junit.Test;

public class TestCRUDUtils {
	@Test
	public void testCRUDUtils() {
		
		int update = CRUDUtils.update("UPDATE employees SET salary = ? WHERE employee_id =?",50,100);
		System.out.println(update);
	
	
	}
	
	@Test
	public void testQuerySingle() {
		Students stu = CRUDUtils.querySingle("select * from stuinfo where id =?",2);
		System.out.print(stu);
	}
	@Test
	public void testQueryMulti() {
		List<Students> list = CRUDUtils.queryMulti("select * from stuinfo");
		
		for(Students stu : list) {
				System.out.println(stu);
	}
	}

}
