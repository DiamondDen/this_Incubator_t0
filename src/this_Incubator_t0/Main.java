package this_Incubator_t0;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {

	public static void main(String[] args) {
		if (args.length == 0) {
			String line = new Scanner(System.in).nextLine();
			if (line != null) {
				args = line.split(" ");
			}
		}

		Map<String, Word> map = new HashMap<>();
		for (String wordStr : args) {
			map.computeIfAbsent(wordStr.toLowerCase(), (key) -> new Word(key)).incAmount();
		}
		StringJoiner joiner = new StringJoiner("\n");
		joiner.add("TOP10:");
		map.values().stream().sorted((word0, word1) -> {
			int comp = Integer.compare(word1.getAmount(), word0.getAmount());
			if (comp != 0) {
				return comp;
			}
			return word0.getWord().compareTo(word1.getWord());
		}).limit(10).forEach(word -> {
			joiner.add(word.getAmount() + " - " + word.getWord());
		});
		System.out.println(joiner.toString());
	}

	public static class Word {

		private int amount;
		private String wordStr;

		public Word(String wordStr) {
			this.wordStr = wordStr;
		}

		public void incAmount() {
			this.amount++;
		}

		public String getWord() {
			return wordStr;
		}

		public int getAmount() {
			return this.amount;
		}
	}

}
