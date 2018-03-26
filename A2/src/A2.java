/* Program that simulates a slot machine game.
 * By: Dustin Fehlman
 */

import java.util.*;
import java.lang.Math;
import java.util.Scanner;

public class A2 {
    
    public static void main(String[] args) {
        int betAmount;
        //Did not see a place to compare max pulls in specs. Used to avoid going beyond max pulls.
        int pulls = 0;
        //Didnt see a place that calcs total winnings. Used to sum total winnings. 
        int totalWins = 0;
        //Loops to get bet and play game. Exits once user bets 0, or exceeds max pulls.
        do {
            betAmount = getBet();
            if (betAmount != 0) {
                TripleString pullString = pull();
                int multiplyer = getPayMultiplier(pullString);
                int winnings = betAmount * multiplyer;
                if(TripleString.saveWinnings(winnings)) {
                    pulls++;
                    totalWins += winnings;
                    display(pullString, winnings);
                }
            }
        } while (betAmount != 0 && pulls < TripleString.MAX_PULLS);
        //Prints game info
        System.out.println("Thanks for playing!");
        System.out.println("Your individual winnings were:");
        System.out.println(TripleString.displayWinnings());
    }
    
    /* 
     * Method: getBet()
     * Parameters: none
     * Returns: int
     * Method to get the betting amount for the user. Checks to make sure bet input
     * is valid. 
     */
    public static int getBet() {
        int betAmount;
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("How much would you like to bet (1 - 100) or 0 to quit?");
            betAmount = Integer.parseInt(keyboard.next());
        } while (betAmount < 0 || betAmount > 100);

        return betAmount;
    }
    
    /* 
     * Method: pull()
     * Parameters: none
     * Returns: TripleString
     * Method to simulate slot machine pull. Assigns random strings to TripleString object.
     */
    public static TripleString pull() {
        TripleString pullResults = new TripleString();
        if (pullResults.setStringOne(randString())) {
            if (pullResults.setStringTwo(randString())) {
                if (pullResults.setStringThree(randString())) {
                    return pullResults;
                }
            }
        }
        return pullResults;
    }
    
    /* 
     * Method: randString()
     * Parameters: none
     * Returns: String
     * Creates random strings based on slot machine word probability.
     */
    private static String randString() {
        double randomDouble = Math.random();
        String randomString = "null";
        double barMax = .49;
        double cherriesMax = .74;
        double spaceMax = .865;
        double sevenMax = 1;
        if (randomDouble >= 0 && randomDouble <= barMax) {
            randomString = "BAR";
        }
        if (randomDouble > barMax && randomDouble <= cherriesMax) {
            randomString = "cherries";
        }
        if (randomDouble > cherriesMax && randomDouble <= spaceMax) {
            randomString = "space";
        }
        if (randomDouble > spaceMax && randomDouble < sevenMax) {
            randomString = "7";
        }
        return randomString;
    }
    
    /* 
     * Method: getPayMultiplier()
     * Parameters: TripleString
     * Returns: int
     * Get multiplier of bet based on random strings in TripleString object.
     */
    public static int getPayMultiplier(TripleString thePull) {
        String bar = "BAR";
        String seven = "7";
        String cherries = "cherries";
        int hunMulti = 100;
        int fiftyMulti = 50;
        int thrityMulti = 30;
        int fifteenMulti = 15;
        int fivMulti = 5;

        if (thePull.getStringOne() == seven && thePull.getStringTwo() == seven && thePull.getStringThree() == seven) {
            return hunMulti;
        } else if (thePull.getStringOne() == bar && thePull.getStringTwo() == bar && thePull.getStringThree() == bar) {
            return fiftyMulti;
        } else if (thePull.getStringOne() == cherries) {
            if (thePull.getStringTwo() == cherries && thePull.getStringThree() == cherries) {
                return thrityMulti;
            } else if (thePull.getStringTwo() == cherries) {
                return fifteenMulti;
            } else {
                return fivMulti;
            }
        } else {
            return 0;
        }
    }
    
    /* 
     * Method: display()
     * Parameters: TripleString, int
     * Returns: none
     * Displays slot machine results, and if applicable, user winnings.
     */
    public static void display(TripleString thePull, int winnings) {
        System.out.println(thePull.toString());
        if (winnings > 0) {
            System.out.println("Congrats, you won $" + winnings + "\n");

        } else {
            System.out.println("Sorry, you lose. \n");
        }
    }
}

//TripleString class used as main class of slot machine game. 
class TripleString {
    public static final int MAX_LEN = 20;
    public static final int MAX_PULLS = 40;
    private static int numPulls = 0;
    private String stringOne;
    private String stringTwo;
    private String stringThree;
    private static int[] pullWinnings = new int[MAX_PULLS];
    
    /* 
     * Method: TripleString()
     * Parameters: none
     * Returns: none
     * Object constructor
     */
    TripleString() {
        this.stringOne = "";
        this.stringTwo = "";
        this.stringThree = "";
    }
    
    /* 
     * Method: validString()
     * Parameters: String
     * Returns: boolean
     * Checks if passed string is valid.
     */
    private boolean validString(String str) {
        if (str != null && str.length() <= TripleString.MAX_LEN) {
            return true;
        }
        return false;
    }
    
    //Object accessors
    public String getStringOne() {
        return this.stringOne;
    }

    public String getStringTwo() {
        return this.stringTwo;
    }

    public String getStringThree() {
        return this.stringThree;
    }
    
    //Object mutators
    public boolean setStringOne(String value) {
        if (this.validString(value)) {
            this.stringOne = value;
            return true;
        }
        return false;
    }

    public boolean setStringTwo(String value) {
        if (this.validString(value)) {
            this.stringTwo = value;
            return true;
        }
        return false;
    }

    public boolean setStringThree(String value) {
        if (this.validString(value)) {
            this.stringThree = value;
            return true;
        }
        return false;
    }
    
    /* 
     * Method: toString()
     * Parameters: none
     * Returns: String
     * Combines slot machine results into one string.
     */
    public String toString() {
        return getStringOne() + " " + getStringTwo() + " " + getStringThree();
    }
    
    /* 
     * Method: saveWinnings()
     * Parameters: int
     * Returns: boolean
     * Saves winning amounts into array 
     */
    public static boolean saveWinnings(int winnings) {
        TripleString.pullWinnings[TripleString.numPulls] = winnings;
        TripleString.numPulls++;
        return true;
    }
    
    /* 
     * Method: displayWinnings()
     * Parameters: none
     * Returns: String
     * Displays winnings to user
     */
    public static String displayWinnings() {
        String winnings = "";
        int totalWinnings = 0;
        //Displays indexs with valid winnings, else shows whole array.
        if (TripleString.numPulls < TripleString.pullWinnings.length) {
            for (int x = 0; x < TripleString.numPulls; x++) {
                winnings += String.valueOf(TripleString.pullWinnings[x]) + " ";
                totalWinnings += TripleString.pullWinnings[x];
            }
        } else {
            for (int x = 0; x < TripleString.pullWinnings.length; x++) {
                winnings += String.valueOf(TripleString.pullWinnings[x]) + " ";
                totalWinnings += TripleString.pullWinnings[x];
            }
        }
        winnings += "\nYour total winnings were: $" + totalWinnings;
        return winnings;
    }

}

/*
 * -------------------- Sample Run ---------------------------*

How much would you like to bet (1 - 100) or 0 to quit?
9
cherries BAR BAR
Congrats, you won $45

How much would you like to bet (1 - 100) or 0 to quit?
10
cherries BAR BAR
Congrats, you won $50

How much would you like to bet (1 - 100) or 0 to quit?
101
How much would you like to bet (1 - 100) or 0 to quit?
50
BAR cherries space
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
12
cherries cherries cherries
Congrats, you won $360

How much would you like to bet (1 - 100) or 0 to quit?
13
cherries BAR BAR
Congrats, you won $65

How much would you like to bet (1 - 100) or 0 to quit?
4
BAR cherries BAR
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
9
BAR BAR space
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
15
BAR cherries BAR
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
89
BAR 7 BAR
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
45
cherries cherries cherries
Congrats, you won $1350

How much would you like to bet (1 - 100) or 0 to quit?
56
cherries 7 cherries
Congrats, you won $280

How much would you like to bet (1 - 100) or 0 to quit?
34
BAR cherries cherries
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
12
BAR BAR cherries
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
56
BAR cherries cherries
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
78
BAR BAR BAR
Congrats, you won $3900

How much would you like to bet (1 - 100) or 0 to quit?
4
7 cherries cherries
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
78
space BAR BAR
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
9
BAR cherries cherries
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
10
BAR BAR space
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
56
BAR space 7
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
89
space cherries space
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
-1
How much would you like to bet (1 - 100) or 0 to quit?
101
How much would you like to bet (1 - 100) or 0 to quit?
45
7 BAR BAR
Sorry, you lose. 

How much would you like to bet (1 - 100) or 0 to quit?
0
Thanks for playing!
Your individual winnings were:
45 50 0 360 65 0 0 0 0 1350 280 0 0 0 3900 0 0 0 0 0 0 0 
Your total winnings were: $6050

 * --------------------------------------------------------
 */
