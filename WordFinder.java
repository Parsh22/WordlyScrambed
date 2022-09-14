/**
 * DESIGN PLAN
 * goodWord Method (one parameter):
 * Make a duplicate string variable that contains the same value as the word
 * Make a stringbuilder with the same value as the word
 * Check if the test word is less than the original word
 * Check if the test word is a duplicate of original word
 * Check if the test word is in the dictionary
 * If any of these conditions fail, the word is invalid
 * If all of these pass, loop through the test word and for each letter, check if it exists in the duplicate word.
 * If the letter exists, the word is valid
 * Make sure to find the index of that letter using indexOf method and delete it from the Stringbuilder using deleteCharAt
 * Store the string value of the stringbuilder into the duplicate word
 * IF any of the letter doesn't exist, make the word invalid and exit out of the for loop
 */


// You must complete the implementation of this class.  You will need
// to use an instance of the Dictionary class within this class.  See
// Dictionary.java for details on the Dictionary class.

public class WordFinder
{
	// Think about the instance variables that you will need for this class.
	// Minimally you will need a Dictionary object and a String.


	// Initialize a WordFinder object.  String fileName is the name of a
	// Dictionary file from which the Dictionary instance variable will be
	// initialized.

	private Dictionary dictionary;
	private String word;



	// Obtain and store a random word from the Dictionary of "size" or more
	// letters.
	public void nextWord(int size)
	{
		word = dictionary.randWord(size);
	}
	public WordFinder(String fileName)
	{
		dictionary = new Dictionary(fileName);
	}
	// Return the word that was obtained. This is necessary since the word itself
	// will be stored in a private instance variable.
	public String showWord()
	{
		return word;
	}

	// This is the most challenging method in this class.  The "test" argument is
	// a String that will be checked for validity within the current word that was
	// obtained from the Dictionary.  This method should return true only if all of
	// the characters in "test" are found within the word (such that each letter in
	// the word is used at most one time) and if "test" is also a valid word in the
	// Dictionary.
	public boolean goodWord(String test) {
		String duplicateWord = new String(word);
		boolean isGoodWord = false;
		StringBuilder stringBuilder = new StringBuilder(duplicateWord);
		if (test.length() < duplicateWord.length()) {
			if (dictionary.contains(test))
			{
				if (!test.equals(duplicateWord))
				{
					int indexR = 0;
					for (int i = 0; i < test.length(); i++) {
						if (indexR == 0) {

							if (duplicateWord.contains(test.substring(i, i + 1))) {
								int index = duplicateWord.indexOf(test.substring(i, i + 1));
								stringBuilder.deleteCharAt(index);
								duplicateWord = stringBuilder.toString();
								isGoodWord = true;
							} else {
								isGoodWord = false;
								indexR = 1;
							}
						}
					}

					}

				}

			}
		return isGoodWord;
		}
	//this method
	//is strictly made for the unit tests
	//it should not be used outside of the unit test file
	//if your String instance variable is something else replace the "word" on "this.word"
	//with whatever name you come up with
	public void setWord (String word) {
		this.word = word;
	}
}
