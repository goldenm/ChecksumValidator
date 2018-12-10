package checksum;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite("All ChecksumValidator Tests");
		suite.addTest(ValidatorTest.suite());
		return suite;
	}
}
