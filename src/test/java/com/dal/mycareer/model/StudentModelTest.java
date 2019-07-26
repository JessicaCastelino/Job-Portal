package com.dal.mycareer.model;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.dal.mycareer.mocks.HttpServletRequestMock;
import com.dal.mycareer.mocks.ModelMock;

public class StudentModelTest {

	@Test
	public void testFetchJobs() {
		StudentModel sm = new StudentModel();
		HttpServletRequestMock request = new HttpServletRequestMock();
		ModelMock model=new ModelMock();
		sm.fetchJobs(model, request);
		Assert.assertTrue("jhghj", regStudent.getId() != 0);
	}

	@Test
	public void testViewJobs() {
		fail("Not yet implemented");
	}

	@Test
	public void testApplyJob() {
		fail("Not yet implemented");
	}

	@Test
	public void testWithdrawApplication() {
		fail("Not yet implemented");
	}

	@Test
	public void testFilterJobs() {
		fail("Not yet implemented");
	}

	@Test
	public void testJobApplicationExists() {
		fail("Not yet implemented");
	}

}
