/* Decks of Cards Program
 * Description: TBD
 * By: John Seals, Hanna Bonert, Dustin Fehlman, and Carlos Sanchez 
 */

public class A3 {
    
    public static void main(String[] args) {
        
    }
}

class Card {
    private char value;
    private Suit suit;
    private boolean errorFlag;
    
    private enum Suit {clubs, diamonds, hearts, spades}
    
    Card(char value, Suit suit){
        
    }
    
    private boolean isValid(char value, Suit suit) {
        return false;
    }
  
    public String toString() {
        return "";
    }
    
    public boolean equals(Card card) {
        return false;
    }
    
    //Mutators
    public boolean set(char value, Suit suit) {
        return false;
    }
    
    //Accessors
    public Suit getSuit() {
        return this.suit;
    }
    
    public char getValue() {
        return this.value;
    }
    
    public boolean getErrorFlag() {
        return this.errorFlag;
    }
}

class Hand {
    public final int MAX_CARDS = 100;
    private Card[] myCards;
    private int numCards;
    
    Hand() {
        
    }
    
    public void resetHand() {
        
    }
    
    public boolean takeCard(Card card) {
        return false;
    }
    
    public Card playCard() {
        return //card Object;
    }
    
    public String toString() {
        return "";
    }
    
    public int getNumCards() {
        return this.numCards;
    }
    
    public Card inspectCard(int k) {
        return //Card object
    }
}

class Deck {
    
}
