package com.votanstudios.checksum.app;

public class RequestData {
	
	private String filename;
	private String messageDigest;
	private String inputChecksum;
	private String generatedChecksum;
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getMessageDigest() {
		return messageDigest;
	}
	
	public void setMessageDigest(String messageDigest) {
		this.messageDigest = messageDigest;
	}
	
	public String getInputChecksum() {
		return inputChecksum;
	}
	
	public void setInputChecksum(String inputChecksum) {
		this.inputChecksum = inputChecksum;
	}
	
	public String getGeneratedChecksum() {
		return generatedChecksum;
	}
	
	public void setGeneratedChecksum(String generatedChecksum) {
		this.generatedChecksum = generatedChecksum;
	}

}
