package com.ParallelProjectTest.TestClass;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ParallelProjectTest.bean.CustomerBean;
import com.ParallelProjectTest.dao.Dao;

public class TestClass {

	Dao dao = new Dao();

	@Before
	public void setUp() {
		dao = new Dao();
	}

	@After
	public void tearDown() {
		dao = null;
	}

	@Test
	public void depositTest() throws NumberFormatException, SQLException {
		dao.deposit("826", "1000");
	
	}

}
