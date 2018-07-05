package com.zkoncept.unscramble.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordManager implements Serializable {

	private static WordManager INSTANCE;
	// HashMap<String, List<String>> wordMap;
	HashMap<Integer, HashMap<String, List<String>>> wordMap;

	private WordManager() {
	}

	public static WordManager getInstance() {
		if (INSTANCE == null) {
			System.out.println("Instance not available creating new instance");
			INSTANCE = new WordManager();
			INSTANCE.loadWords();
		}
		return INSTANCE;
	}

	private void loadWords() {
		// String fileName1 = "E:\\Git\\Unscramble\\android\\assets\\words\\";
		// int
		String fileName = "E:\\Git\\Unscramble\\android\\assets\\words\\";
		wordMap = new HashMap<Integer, HashMap<String, List<String>>>();

		for (int i = 3; i < 20; i++) {

			HashMap<String, List<String>> loadWord = getHashMapFromFile(fileName
					+ i + "_words.txt");
			wordMap.put(i, loadWord);
		}
	}

	private HashMap<String, List<String>> getHashMapFromFile(String fileName) {

		HashMap<String, List<String>> wordMap = new HashMap<String, List<String>>();
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNext()) {
				String[] words = scanner.nextLine().split(",");
				for (String word : words) {
					addWordToMap(wordMap, word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wordMap;
	}

	private void addWordToMap(HashMap<String, List<String>> wordMap, String word) {
		String rearrangedWord = rearrange(word);
		if (wordMap.containsKey(rearrangedWord))
			wordMap.get(rearrangedWord).add(word);
		else {
			List wordList = new ArrayList<String>();
			wordList.add(word);
			wordMap.put(rearrangedWord, wordList);
		}
	}

	public List<String> getWords(String word) {
		System.out.println("checking for word:" + word);
		List<String> list = wordMap.get(word.length()).get(rearrange(word));
		System.out.println("Returning the wordList:" + list);
		return list;

	}

	public boolean saveWord(String word) {
		List<String> list = getWords(word);

		if (list != null && list.contains(word)) {
			return false;

		} else {

			addWordToMap(wordMap.get(word.length()), word);
			return true;

		}

	}

	private String rearrange(String word) {
		char[] characters = word.toCharArray();
		Arrays.sort(characters);
		return String.valueOf(characters);
	}
}
