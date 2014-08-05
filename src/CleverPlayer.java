/**
 * A CleverPlayer is a Player that makes their guesses by choosing their letters
 * from a filtered word list. One filter uses the masked phrase and another filter
 * uses failed guesses to eliminate words. Then it picks a letter on the 
 * filtered list in order of most probable.
 * @author tomlewis
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class CleverPlayer extends Player {
	private ArrayList<String> filteredWords = new ArrayList<String>();
	private int guideCur = 0;
	
	
	/**
	 * Constructs a CleverPlayer with a set of known words
	 * @param words the word list
	 * @param charSet the list of valid characters
	 * @throws FileNotFoundException
	 */
	public CleverPlayer(ArrayList<String> words, ArrayList<Character> charSet)
			throws FileNotFoundException {
		super(words, charSet);
		filteredWords = wordBank;
		maskPhrase = "";	
	}

	/**
	 * Initial culling of the filtered words
	 * refine filteredWords based off length of phrase
	 * @param maskPhraseCount number of letters in keyword
	 */
	public void initCull(int maskPhraseCount){
		
		//build initial masked word
		for (int k = 0; k < maskPhraseCount; k++){
			maskPhrase+="-";
		}
	
		int phraseLength = maskPhrase.length();
		int listSize = filteredWords.size();
		for (int i = 0; i < listSize; i++){
			String cur = filteredWords.get(i);
			if (cur.length() != phraseLength) { //if the lengths aren't equal
				filteredWords.remove(i);
				listSize--;
				i--;
			}
		}
	}
	
	/**
	 * Returns a Clever guess
	 * First the filteredWords list is refined to discard
	 * @return char guess
	 */
	public char getGuess() {
		
		System.out.println("getting guess, filtered words size: " + filteredWords.size());
		System.out.println("maskPhrase: "+maskPhrase);
		int wordListSize = filteredWords.size();
		
		//filter with what is known from masked phrase
		for (int i = 0; i < maskPhrase.length(); i++){
			char cur = maskPhrase.charAt(i); //the current letter of the maskPhrase being checked
			
			if (cur != '-') { //if cur is known
				//check all the words in filteredWords for a match
				//if it deoesn't match, discard the word
				
				for (int j = 0; j < wordListSize; j++){
					String filtWord = filteredWords.get(j);
					if ( cur !=  filtWord.charAt(i)){  //if it doesn't match
						filteredWords.remove(j); //remove the word
						wordListSize--;
					}
				}
			}
		}
		
		//filter with failed guesses
		int failedNum = failedGuesses.size();
		char letterOut;
		for (int i = 0; i < failedNum; i++){
			
			letterOut = failedGuesses.get(i);
			
			//remove words with that guess
			System.out.println("removing words from list...");
			for (int k = 0; k < wordListSize; k++){
				
				//if word contains the char, then remove
				String currentWord = filteredWords.get(k);
				if ( currentWord.indexOf(letterOut) > -1){  //if the character is in the word
					//System.out.println("removing "+ filteredWords.get(k));
					filteredWords.remove(k); //remove the word
					wordListSize--;
				}
			}
		}
		
		//now pick a letter found in remaining words starting with highest frequency a
		char[] guessGuide = { 'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd',
				'l', 'c', 'u', 'm', 'w', 'f', 'g', 'y', 'p', 'b', 'v', 'k',
				'j', 'x', 'q', 'z' };

		// while current char is in failedGuess or SuccessGuesses or not in filteredWords, keep incrementing
		while (failedGuesses.contains(guessGuide[guideCur])
				|| successGuesses.contains(guessGuide[guideCur]) 
				|| !(charInFilteredWords(guessGuide[guideCur]))) {
			guideCur++;
		}
		return guessGuide[guideCur];
	}
	
	private boolean charInFilteredWords(char c) {
		//go through all the filtered words and return true if c is found
		for (int i = 0; i < filteredWords.size(); i++){
			String curWord = filteredWords.get(i);
			//check to see if c is in the current word
			for (int j = 0; j < curWord.length(); j++){
				if (c == curWord.charAt(j)){
					return true;
				}
			}
		}
		return false;
	}

}
