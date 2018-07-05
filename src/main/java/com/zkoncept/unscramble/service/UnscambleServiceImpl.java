package com.zkoncept.unscramble.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.zkoncept.unscramble.util.WordManager;

@Service
public class UnscambleServiceImpl implements UnscrambleService {

	WordManager wordmanager = WordManager.getInstance();

	@Override
	public List<String> getUnscrammbleWords(String word) {

		return wordmanager.getWords(word);
	}

	@Override
	public boolean saveUnscrambleWord(String word) {

		return wordmanager.saveWord(word);
	}

}
