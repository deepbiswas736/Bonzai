package com.deep.assignment;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.deep.assignment.service.ParserService;
import com.deep.assignment.service.TailService;

@SpringBootApplication
public class AssignmentApplication implements CommandLineRunner {

	@Autowired
	private TailService tailService;
	
	@Autowired
	private ParserService parserService;
	
	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String logs = "185.12.15.89 - - [23/Nov/2014:03:33:03 -0500] \"GET /ip HTTP/1.1\" 503 323 \"-\" " + 
				"\"curl/7.30.0\"\n185.12.15.93 - - [23/Nov/2014:03:33:07 -0500] \"GET /ip HTTP/1.1\" 200 " + 
				"323 \"-\" \"curl/7.30.0\"\n54.243.188.61 - - [23/Nov/2014:03:33:08 -0500] \"GET /ip HTTP/1.1\"" + 
				" 503 323 \"-\" \"check_http/v1.4.16 (nagios-plugins 1.4.16)\"\n87.255.53.44 - - " + 
				"[23/Nov/2014:03:33:52 -0500] \"GET /ip HTTP/1.1\" 200 323 \"-\" \"curl/7.19.7" + 
				"(universal-apple-darwin10.0) libcurl/7.19.7 OpenSSL/0.9.8r zlib/1.2.3\"\n87.255.53.45 - -" + 
				" [23/Nov/2014:03:33:54 -0500] \"GET /ip HTTP/1.1\" 503 323 \"-\" \"curl/7.19.7" + 
				"(universal-apple-darwin10.0) libcurl/7.19.7 OpenSSL/0.9.8r zlib/1.2.3\"";
		
		parserService.parse(logs);
		
		Path currentDir = Paths.get(".");
		
		System.out.println("Please write something in application.log to print\n Last line will not be printed !!!");
		
		tailService.tailLogFile(currentDir.toAbsolutePath()+"\\application.log");
	}

}
