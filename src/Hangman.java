public class Hangman {

    // properties
    private StringBuffer secretWord;
    private StringBuffer allLetters;
    private StringBuffer usedLetters;
    private int numberOfIncorrectTries;
    private int maxAllowedIncorrectTries;
    private StringBuffer knownSoFar;

    // constructor
    public Hangman() 
    {

        // this method will initialize the variable secretWord
        chooseSecretWord();

        // initialize properties
        allLetters = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
        usedLetters = new StringBuffer("");
        numberOfIncorrectTries= 0;
        maxAllowedIncorrectTries = 6;
        knownSoFar = new StringBuffer("");
        
        // replaces the choosen secretWord with the "underscore" character
        for( int i = 0; i < secretWord.length(); i++){
            knownSoFar.append("_");
        }

    }

    /**
     * Checks if a letter is in the secretWord and adds it to "usedLetters".
     * If it is in secretWord, update knownSoFar with the discovered letter.
     * @param letter the guessed letter
     * @return number of occurrences of letter in secretWord
     */
    public int tryThis(char letter) {

        int count;
        
        // A character that is not in allLetters is invalid.
        if (allLetters.indexOf(String.valueOf(letter)) == -1) {
            return 0;
        }
        
        // It is assumed that the main method will prevent the user from trying
        // a used letter. However, if a letter was already used, the method
        // will not stop but it will not change any of the properties.
        if (usedLetters.indexOf(String.valueOf(letter)) == -1) {
            usedLetters.append(letter);
        }
        
        count = 0;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                // Replace the blank letters with the correct letter.
                // knownSoFar is assumed to have the same length as secretWord.
                knownSoFar.replace(i, i + 1, String.valueOf(letter));
                count++;
            }
        }
        
        if (count == 0) {
            numberOfIncorrectTries++;
        }

        return count;
    }

    /**
     * Chooses a random word from the list
     * @return the chosen word
     */
    public String chooseSecretWord()
    {
        String[] words = { "bird", "cat", "computer", "java", "Davenport" }; // Some random words
        int index;
        String chosenWord;
        
        // Assigning a random index in the range of the array
        index = (int) ( Math.random() * words.length ) ;
        
        // Getting the word from the array at that index
        chosenWord = words[index];
        
        return chosenWord;
    }

    /**
     * Get all letters of the secret word.
     * @return is all letters of the secret word.
     */
    public String getAllLetters ()
    {
        return allLetters.toString();
    }
    
    /**
     * Get used letter which is used by user.
     * @return is used letter which is used by user.
     */
    public String getUsedLetters ()
    {
        return usedLetters.toString();
    }
    
    /**
     * Get numbers of incorrect tries.
     * @return is numbers of incorrect tries.
     */
    public int getNumOfIncorrectTries ()
    {
        return numberOfIncorrectTries;
    }
    
    /**
     * Get max allowed incorrect rights.
     * @return is max allowed incorrect rights.
     */
    public int getMaxAllowedIncorrectTries ()
    {
        return maxAllowedIncorrectTries;
    }
    
    /**
     * Get known letters so far.
     * @return is known letters so far.
     */
    public String getKnownSoFar ()
    {
        return knownSoFar.toString();
    }
    
    /**
     * Show whether the game is won or not.
     * @return indicates whether the game is won or not.
     */
    public boolean hasLost ()
    {
        if (numberOfIncorrectTries == maxAllowedIncorrectTries)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Show if the game is over or not.
     * @return shows if the game is over or not.
     */
    public boolean isGameOver ()
    {
        return hasLost() || (allLetters == usedLetters);
    }

}
