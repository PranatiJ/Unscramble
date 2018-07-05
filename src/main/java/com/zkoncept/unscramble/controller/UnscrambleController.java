package com.zkoncept.unscramble.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkoncept.unscramble.service.UnscrambleService;

@RestController
public class UnscrambleController {

	/**
	 * Get Test.
	 */
 @Autowired
 UnscrambleService unscrambleService;
	@RequestMapping("/")
	public String index() {

		return "Greetings from Spring Boot!";
	}

	@GetMapping(path = "/unscramble/{word}")
	public ResponseEntity<List<String>> getWord(@PathVariable(value = "word") String word) {
       
		
		
		return ResponseEntity.ok( unscrambleService.getUnscrammbleWords(word));
	}
	
	@GetMapping(path = "/unscramble/newWord/{word}")
	public ResponseEntity<String> saveWord(@PathVariable(value = "word") String word) {
       
		if(unscrambleService.saveUnscrambleWord(word)){
		
		return ResponseEntity.ok("word Added sucessfully " );
	}
		else{
			return ResponseEntity.ok("word already Exist " );
		}
			
		}
}
