package com.zkoncept.unscramble.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CopyOfWordManager {

	private static CopyOfWordManager INSTANCE;
//	HashMap<String, List<String>> wordMap;
	HashMap<String, List<String>> wordMap;

	private CopyOfWordManager() {
	}

	public static CopyOfWordManager getInstance() {
		if (INSTANCE == null) {
			System.out.println("Instance not available creating new instance");
			INSTANCE = new CopyOfWordManager();
			INSTANCE.loadWords();
		}
		return INSTANCE;
	}

	private void loadWords() {
		String fileName = "E:\\Git\\Unscramble\\android\\assets\\words\\";
		wordMap = new HashMap<String, List<String>>();
		try (Scanner scanner = new Scanner(new File(fileName))) {
			while (scanner.hasNext()) {
				String[] words = scanner.nextLine().split(",");
				for (String word : words) {
					String rearrangedWord = rearrange(word);
					if (wordMap.containsKey(rearrangedWord))
						wordMap.get(rearrangedWord).add(word);
					else {
						List wordList = new ArrayList<String>();
						wordList.add(word);
						wordMap.put(rearrangedWord, wordList);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> getWords(String word) {
		System.out.println("checking for word:" + word);
		String newWord = rearrange(word);
		List<String> list = wordMap.get(newWord);
		System.out.println("Returning the wordList:" + list);
		return list;

	}

	private String rearrange(String word) {
		char[] characters = word.toCharArray();
		Arrays.sort(characters);
		return String.valueOf(characters);
	}
}
