package com.restcontrollerdemo;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
	
	static Integer counter = 1;
	
	@GetMapping
	public String home(HttpSession session) {
		if(session.getAttribute("counter")!=null) {
			counter = counter+1;
		}
		
		session.setAttribute("counter", counter);
		return "Requested Count "+counter;
	}
	
	@GetMapping(value = "/test1", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<TestResponse> getJSONOrXML(@RequestParam String format){
		TestResponse obj = new TestResponse();
		obj.setCompany("STS");
		obj.setName("Komal");
		HttpHeaders header = new HttpHeaders();
		if(format.equals("xml"))
			header.setContentType(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE));
		else
			header.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));
		
		return new ResponseEntity<TestResponse>(obj,header,HttpStatus.OK);
	}
}
