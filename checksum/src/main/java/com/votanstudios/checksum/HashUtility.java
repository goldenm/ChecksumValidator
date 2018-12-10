package com.votanstudios.checksum;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.votanstudios.checksum.app.RequestData;

public class HashUtility {
	
	private RequestData data;
	
	public HashUtility(RequestData data) {
		this.data = data;
	}
	
	public String getChecksumHex() throws NoSuchAlgorithmException, IOException {
		String checksumValidation = "";
		
		MessageDigest digest = MessageDigest.getInstance(data.getMessageDigest());
		digest.update(Files.readAllBytes(Paths.get(data.getFilename())));
		byte[] hash = digest.digest();
		checksumValidation = DatatypeConverter.printHexBinary(hash);
		
		return checksumValidation;
	}

}
