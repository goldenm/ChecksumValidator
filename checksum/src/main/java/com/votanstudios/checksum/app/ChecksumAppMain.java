package com.votanstudios.checksum.app;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.votanstudios.checksum.HashUtility;
import com.votanstudios.checksum.view.SwingApp;

public class ChecksumAppMain {
	
	// Supported digests
	public final static List<String> supportedDigests = Arrays.asList("MD5", "SHA1", "SHA-256");
	
	private static List<String> argsList;
	private static List<String> optsList;
	private static RequestData data;

	public static void main(String[] args) {
		
		if(args.length > 0) {
			argsList = new ArrayList<String>();
			optsList = new ArrayList<String>();
			
			parseArgs(args);
			
			// process data with HashUtility
			HashUtility hashUtility = new HashUtility(data);
			String generatedChecksum = "";
			
			try {
				generatedChecksum = hashUtility.getChecksumHex();
			} catch (NoSuchAlgorithmException e1) {
				System.err.println("An unexpected error has occurred");
				System.err.println(e1);
			} catch (IOException e2) {
				System.err.println("An unexpected error has occurred");
				System.err.println(e2);
			}
			
			// return results
			if(data.getInputChecksum() != null) {
				if(generatedChecksum.toLowerCase().equals(data.getInputChecksum().toLowerCase())) {
					System.out.println("The checksum " + generatedChecksum.toLowerCase() + " matches");
				}
			}
			
			else {
				System.out.println("The checksum of the specified file is " + generatedChecksum.toLowerCase());
			}
		}
		else {
			SwingApp.init();
		}
	}
	
	// ARGS METHODS
	
	private static void parseArgs(String[] args) {
		// load lists
		for(String arg : args) {
			if(arg.charAt(0) != '-') {
				argsList.add(arg);
			}
			else {
				optsList.add(arg);
			}
		}
		
		if(optsList.size() > 0) {
			parseOpts();
		}
		
		
		// validate input & error if necessary
		validateArgs();
		
		// load data object
		data = new RequestData();
		data.setFilename(argsList.get(0));
		data.setMessageDigest(argsList.get(1));
		
		if(argsList.size() > 2) {
			data.setInputChecksum(argsList.get(2));
		}
	}
	
	private static void parseOpts() {
		for(String opt : optsList) {
			if(opt.equals("-h") || opt.equals("--help")) {
				printHelp();
				System.exit(0);
			}
		}
	}
	
	private static void validateArgs() {
		if(argsList.size() < 2) {
			error("Insufficient number of inputs");
		}
		
		File file = new File(argsList.get(0));
		
		if(file.isDirectory() || !file.exists()) {
			error("Invalid file");
		}
		
		if(!supportedDigests.contains(argsList.get(1))) {
			error("Unsupported Message Digest");
		}
	}
	
	private static void printHelp() {
		System.out.println("Usage: checksum [FILEPATH] [MESSAGE DIGEST] [CHECKSUM]");
		System.out.println("Hash the file specified in FILEPATH using the desired MESSAGE DIGEST and compare it to the CHECKSUM (if provided)");
		System.out.println("Supported MESSAGE DIGESTS are SHA1, SHA256, and MD5");
		System.out.println("FILEPATH and MESSAGE DIGEST are mandatory arguments\n");
		System.out.println("Optional arguments:");
		System.out.println("\t-h, --help\t display help information");
//		System.out.println("\t-v, --verbose\t provide additional information");
		System.out.println("\nExit status:");
		System.out.println(" 0  if OK");
		System.out.println(" 1  if exiting in error");
	}
	
	private static void error(String message) {
		System.err.println(message);
		System.err.println("Try 'checksum --help' for more information");
		System.exit(1);
	}
}
