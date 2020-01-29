package com.restcontrollerdemo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {
	
	@GetMapping
	public String home(HttpSession session) {
		Integer counter=1;
		if(session.getAttribute("counter")!=null) {
			counter = Integer.valueOf(session.getAttribute("counter").toString())+1;
		}
		
		session.setAttribute("counter", counter);
		return "Requested Count "+counter;
	}
	
	@GetMapping(value = "/test1", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Map<String,String>> getJSONOrXML(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("User", "Komal");
		map.put("Company", "Synergy");
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.valueOf(MediaType.APPLICATION_XML_VALUE));
		return new ResponseEntity<Map<String,String>>(map,header,HttpStatus.OK);
	}
}
