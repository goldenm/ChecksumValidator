package checksum;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.votanstudios.checksum.HashUtility;
import com.votanstudios.checksum.app.RequestData;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ValidatorTest extends TestCase {

	private String filename;
	
	@Override
	protected void setUp() {
		filename = "src/test/resources/checksum_test.txt";
	}
	
	public static Test suite() {
		return new TestSuite(ValidatorTest.class);
	}
	
	public void testMD5Hash() throws NoSuchAlgorithmException, IOException {
		String md5Checksum = "6df48c159d58c059eca01b2e81f6a8af";
		
		RequestData data = new RequestData();
		data.setFilename(filename);
		data.setGeneratedChecksum(md5Checksum);
		data.setMessageDigest("MD5");
		
		HashUtility hashUtility = new HashUtility(data);
		String checksumValidation = hashUtility.getChecksumHex();
		
		assertEquals(md5Checksum, checksumValidation.toLowerCase());
	}
	
	public void testSHA1Hash() throws NoSuchAlgorithmException, IOException {
		String sha1Checksum = "c0a27b84d3e0ed0c16be30bc4c553cc5d0d63f1b";
		
		RequestData data = new RequestData();
		data.setFilename(filename);
		data.setGeneratedChecksum(sha1Checksum);
		data.setMessageDigest("SHA1");
		
		HashUtility hashUtility = new HashUtility(data);
		String checksumValidation = hashUtility.getChecksumHex();
		
		assertEquals(sha1Checksum, checksumValidation.toLowerCase());
	}
	
	public void testSHA256Hash() throws NoSuchAlgorithmException, IOException {
		String sha256Checksum = "52d2317a275580e6f17968f51bac234def151aee09c4c8a15f80097d48d89cdc";
		
		RequestData data = new RequestData();
		data.setFilename(filename);
		data.setGeneratedChecksum(sha256Checksum);
		data.setMessageDigest("SHA-256");
		
		HashUtility hashUtility = new HashUtility(data);
		String checksumValidation = hashUtility.getChecksumHex();
		
		assertEquals(sha256Checksum, checksumValidation.toLowerCase());
	}
	
}
