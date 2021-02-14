public class Hangman {

    // properties
    public StringBuffer secretWord;
    public StringBuffer allLetters;
    public StringBuffer usedLetters;
    public int numberOfIncorrectTries;
    public int maxAllowedIncorrectTries;
    public StringBuffer knownSoFar;

    // constructor
    public Hangman() {

        // this method will initialize the variable secretWord
        chooseSecretWord();

        // initialize properties
        allLetters = new StringBuffer("ABCDEFGHIJKLMNOQRSTUVWXYZ");
        usedLetters = new StringBuffer("");
        numberOfIncorrectTries = 0;
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
     * @return number of occurrences of letter in secretWord or the responseCode
     * -1 Invalid
     * -2 Used
     * -3 Game over / lost
     */
    public int tryThis(char letter) {

        int count;
        int responseCode = 0;
        
        // -1 INVALID CHARACTER
        // A character that is not in allLetters is invalid.
        if (allLetters.indexOf(String.valueOf(letter)) == -1) {
            responseCode = -1;
        }

        // -2 USED CHARACTER
        if (usedLetters.indexOf(String.valueOf(letter)) == -1) {
            usedLetters.append(letter);
            responseCode = -2;
        }

        // -3 GAME OVER
        if (isGameOver()) {
            responseCode = -3;
        }

        count = 0;

        if (responseCode != -3) {

            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.toString().toUpperCase().charAt(i) == letter) {
                    // Replace the blank letters with the correct letter.
                    // knownSoFar is assumed to have the same length as secretWord.
                    knownSoFar.replace(i, i + 1, String.valueOf(letter));
                    count++;
                }
            }

            if (count == 0) {
                numberOfIncorrectTries++;
            }
        
        }

        return count == 0 ? responseCode : count;

    }

    /**
     * Chooses a random word from the list
     * @return the chosen word
     */
    private String chooseSecretWord() {

        // Some random words
        String[] words = { "bird", "cat", "computer", "java", "david" };
        int index;
        String chosenWord;
        
        // Assigning a random index in the range of the array
        index = (int) ( Math.random() * words.length ) ;
        
        // Getting the word from the array at that index
        chosenWord = words[index];

        // Assign the word to the global property
        this.secretWord = new StringBuffer(chosenWord);

        return chosenWord;

    }

    /**
     * Get all letters of the secret word.
     * @return is all letters of the secret word.
     */
    public String getAllLetters () {
        return allLetters.toString();
    }
    
    /**
     * Get used letter which is used by user.
     * @return is used letter which is used by user.
     */
    public String getUsedLetters () {
        return usedLetters.toString();
    }
    
    /**
     * Get numbers of incorrect tries.
     * @return is numbers of incorrect tries.
     */
    public int getNumOfIncorrectTries () {
        return numberOfIncorrectTries;
    }
    
    /**
     * Get max allowed incorrect rights.
     * @return is max allowed incorrect rights.
     */
    public int getMaxAllowedIncorrectTries () {
        return maxAllowedIncorrectTries;
    }
    
    /**
     * Get known letters so far.
     * @return is known letters so far.
     */
    public String getKnownSoFar () {
        return knownSoFar.toString();
    }
    
    /**
     * Show whether the game is won or not.
     * @return indicates whether the game is won or not.
     */
    public boolean hasLost () {
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
    public boolean isGameOver () {
        
        int count = 0;

        for (int i = 0; i < knownSoFar.length(); i++) {
            if (Character.isLetter(knownSoFar.charAt(i)))
            count++;
        }

        return hasLost() || (count == secretWord.length());
    
    }

}
