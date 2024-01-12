package com.booleanuk;

import java.util.HashMap;

public class Scrabble {
	String word;
	int multiplier;
	HashMap<Character, Integer> letterValue;

	public Scrabble(String word) {
		this.word = word;
		multiplier = 1;
		init();

	}

	public int score() {
		int score = 0;
		int bonus = 1;
		checkMultiplier();
		if (isValid()) {
			for (int i = 0; i < word.length(); i++) {
				if (Character.isLetter(word.charAt(i))) {
					score += letterValue.get(Character.toLowerCase(word.charAt(i))) * bonus;
					bonus = 1;
				} else if (word.charAt(i) == '{') {
					bonus = 2;
				} else if (word.charAt(i) == '[') {
					bonus = 3;
				}
			}
		}
		return score * multiplier;
	}

	private void checkMultiplier() {
		if (word.length() > 2) {
			if (word.charAt(0) == '[' && word.charAt(word.length() - 1) == ']') {
				multiplier = multiplier * 3;
				word = word.substring(1, word.length() - 1);
				checkMultiplier();
				return;
			}
			if (word.charAt(0) == '{' && word.charAt(word.length() - 1) == '}') {
				if (word.charAt(2) != '}') {
					multiplier = multiplier * 2;
					word = word.substring(1, word.length() - 1);
					checkMultiplier();
				}
			}
		}
	}

	private boolean isValid() {
		if (word.isEmpty()) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!Character.isLetter(c) && c != '[' && c != ']' && c != '{' && c != '}') {
				return false;
			}

			if (c == '[') {
				if (word.length() < i + 2) {
					return false;
				}
				if (word.charAt(i + 2) != ']') {
					return false;
				}
			}

			if (c == '{') {
				if (word.length() < i + 2) {
					return false;
				}
				if (word.charAt(i + 2) != '}') {
					return false;
				}
			}

			if (c == ']') {
				if (i < 2) {
					return false;
				}
				if (word.charAt(i - 2) != '[') {
					return false;
				}
			}
			if (c == '}') {
				if (i < 2) return false;
				if (word.charAt(i - 2) != '{') {
					return false;
				}
			}
		}

		return true;
	}

	private void init() {
		letterValue = new HashMap<>();
		letterValue.put('a', 1);
		letterValue.put('b', 3);
		letterValue.put('c', 3);
		letterValue.put('d', 2);
		letterValue.put('e', 1);
		letterValue.put('f', 4);
		letterValue.put('g', 2);
		letterValue.put('h', 4);
		letterValue.put('i', 1);
		letterValue.put('j', 8);
		letterValue.put('k', 5);
		letterValue.put('l', 1);
		letterValue.put('m', 3);
		letterValue.put('n', 1);
		letterValue.put('o', 1);
		letterValue.put('p', 3);
		letterValue.put('q', 10);
		letterValue.put('r', 1);
		letterValue.put('s', 1);
		letterValue.put('t', 1);
		letterValue.put('u', 1);
		letterValue.put('v', 4);
		letterValue.put('w', 4);
		letterValue.put('x', 8);
		letterValue.put('y', 4);
		letterValue.put('z', 10);


	}
}
