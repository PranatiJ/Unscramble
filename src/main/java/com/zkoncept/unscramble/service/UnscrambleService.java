package com.zkoncept.unscramble.service;

import java.util.List;

public interface UnscrambleService {
 
	 List<String> getUnscrammbleWords(String word);
	
	  boolean saveUnscrambleWord(String word);
	
}
