package org.bsm.test.book;



import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;

public class firstTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(firstTest.class.getName());

	@Test
	public void test() {
		logger.info("begin test the firsttest..");
		logger.info("end test the firsttest..");
	}
}
