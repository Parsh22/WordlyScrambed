/**
 * Name: Parsh Goel
 * Class: AP CSA
 * Period: 1
 * DESIGN PLAN
 * Main Method:
 * Make a WordFinder object
 * Make a MyTimer object
 * Ask if the user wants to play the game
 * Continue if the user enters "y"
 * Ask the user's Name and ID (for bonus)
 * Set the timer limit to 1 min using myTimer.set() method and start it
 * Call a random Word from the dictionary using nextWord() method
 * while the timer is running, display the word to the user and ask to guess a word
 * use the goodWord() method to find if the word is valid and display it to the user accordingly
 * Keep track of the number of valid words by making a numWord variable and increasing it by 1 everytime a word is vald\
 * Keep track of the total points by adding the length of word to the current total points
 * Make a storageString variable that will store all the valid words
 * After the user runs out of time, display the round's stats to the user
 * Ask the user if they want to play the game again
 * Continue playing the game as long as the user enters "y"
 * if user enters "n", print out all rounds' stats
 * BONUS:
 * if the user enters "n",
 * Create a new File and name it according to the user's name and ID
 * Create a scanner object
 * create a counter variable that will keep track of the particular line the scanner obj is on
 * if the file exists and as long as the scanner obj has something in the next line,
 * check what line the scanner is on using the counter variable
 * if the counter is 0 (1st line), move the reader to next line
 * if the counter is 1, store the value into a numRounds variable
 * if counter is 2, store the value into a numValidWords variable
 * if counter is 3, store the value into a totalPoints variable
 * Make a BufferedWriter and a FileWriter object
 * Use the bufferedWriter obj to write the user's cumulative stats
 * Close the bufferedWriter
 * Finally, print out both the cumulative stats in this particular game and all the games combined
 */


import java.util.Scanner;
import java.io.*;

public class WordyScramble {
    public static void main(String[] args) {

        WordFinder wordFinder = new WordFinder("dictionary.txt");
        MyTimer myTimer = new MyTimer();
        int numValidWords = 0;
        int totalPoints = 0;
        String storageString = "";
        int numValidWordsAllRounds = 0;
        int totalPointsAllRounds = 0;
        int numRounds = 0;
        StringBuilder strB = new StringBuilder();
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to the Word Finder Program");
        System.out.println("Would you like to play our game (y/n)");
        String yesNo = reader.nextLine();
        String userResponse = "";

        System.out.println("Enter you name (First Name): ");
        String name = reader.nextLine();
        System.out.println("Enter your ID: ");
        String idNumber = reader.nextLine();
        while (yesNo.equals("y")) {
            numValidWords = 0;
            totalPoints = 0;
            storageString = "";
            myTimer.set(60000);
            System.out.println("You have 1 min to enter as many words as you can");
            myTimer.start();
            wordFinder.nextWord(1);
            while (myTimer.check()) {
                System.out.print("Word: " + wordFinder.showWord());
                System.out.println("\tGuess?");

                if (myTimer.check()) {
                    userResponse = reader.nextLine();
                    if (wordFinder.goodWord(userResponse)) {
                        System.out.println(userResponse + " is a valid word");
                        numValidWords++;
                        totalPoints = totalPoints + userResponse.length();
                        strB.append(userResponse + "\n");
                    } else if (userResponse.equals(wordFinder.showWord())) {
                        System.out.println(userResponse + " is a duplicate word");
                    } else {
                        System.out.println(userResponse + " is an invalid word");
                    }
                }
            }
            System.out.println("Sorry, time is up!");
            numRounds++;
            System.out.println("You guessed " + numValidWords + " words in this round.");
            System.out.println("You earned " + totalPoints + " points in this round");
            System.out.println("Here is the list of all of the valid words you entered:");
            System.out.println(strB);
            System.out.println();
            numValidWordsAllRounds = numValidWordsAllRounds + numValidWords;
            totalPointsAllRounds = totalPointsAllRounds + totalPoints;
            System.out.println("Would you like to play our game (y/n)");
            yesNo = reader.nextLine();
        }
        if (yesNo!= "y") {

        int existingNumRounds = 0;
        int existingNumWords = 0;
        int existingTotalPoints = 0;
        String fileName = name + idNumber;
        try {
            File file = new File(fileName + ".txt"); // creates a new File objectt with the name of FileName.txt
            file.createNewFile(); // actually creates a new File by calling the createNewFile() method
            Scanner nReader = new Scanner(file); // creates a Scanner object that will read through the given file
            double number;
            if (file.exists()) { // checks if the file exists
                int counter = 0;
                while (nReader.hasNextLine()) { // as long as there is something in the next line in the file
                    switch (counter) {
                        case 0:
                            nReader.nextLine(); // ignores the first line and goes to the next line
                        case 1:
                            number = nReader.nextDouble(); // stores the value of the double value in the next line into a number
                            existingNumRounds = (int) number;
                            break;
                        case 2:
                            number = nReader.nextDouble();
                            existingNumWords = (int) number;
                            break;
                        case 3:
                            number = nReader.nextDouble();
                            existingTotalPoints = (int) number;
                            break;
                    }
                    counter++; // increases the counter by 1
                }
            }

            FileWriter fileWriter = new FileWriter(new File(fileName + ".txt")); // creates a new FileWriter object
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // creates a new bufferedWriter object
            //writes the header of the file to the file
            bufferedWriter.write("The numbers in this file shows the following thins in order: " +
                    "Total Rounds, Total Valid Words, Total Points, Avg Words per round, Avg Points per round\n");
            // adds the cumulative statistics to the file line by line to the file
            bufferedWriter.append((existingNumRounds + numRounds) + "\n" + (numValidWordsAllRounds + existingNumWords) +
                    "\n" + (totalPointsAllRounds + existingTotalPoints) +
                    "\n" + (double) (numValidWordsAllRounds + existingNumWords) / (numRounds + existingNumRounds) +
                    "\n" + (double) (totalPointsAllRounds + existingTotalPoints) / (numRounds + existingNumRounds));

            bufferedWriter.close(); // closes the bufferedWriter

        }
        catch (Exception e)
        {
            e.printStackTrace(); // catches any exception and prints it out
        }
        System.out.println("You played for a total of " + numRounds + " rounds in this game");
        System.out.println("You guessed a total of " + (numValidWordsAllRounds) + " words in this game");
        System.out.println("You earned a total of " + (totalPointsAllRounds) + " points in this game");
        System.out.println("You found an average of " + (double) numValidWordsAllRounds / numRounds + " words per round in this game");
        System.out.println("Your average points were " + (double) totalPointsAllRounds / numRounds + " points per round in this games");
        System.out.println();
        System.out.println("You played for a total of " + (numRounds + existingNumRounds) + " rounds in all of the games combined");
        System.out.println("You guessed a total of " + (numValidWordsAllRounds + existingNumWords) + " words in all of the games combined");
        System.out.println("You earned a total of " + (totalPointsAllRounds + existingTotalPoints) + " points in all of the games combined");
        System.out.println("You found an average of " + (double) (numValidWordsAllRounds + existingNumWords) / (numRounds + existingNumRounds) + " words per round in all of the games combined");
        System.out.println("Your average points were " + (double) (totalPointsAllRounds + existingTotalPoints) / (numRounds + existingNumRounds) + " points per round in all of the games combined");


        }
    }
}
