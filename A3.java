/* Decks of Cards Program
 * Description: A program to simulate a deck of cards passed out to card players.
 * By: John Seals, Hanna Bonert, Dustin Fehlman, and Carlos Sanchez 
 */

import java.util.Scanner;

public class A3 {
    
    public static void main(String[] args) {
        int totalHands;
        int numPacks = 1;
        Scanner keyboard = new Scanner(System.in);
        
        //Loops until user gives a correct amount of hands.
        do {
            System.out.print("How many hands? (1 - 10, please): ");
            totalHands = Integer.parseInt(keyboard.next());
        }while(totalHands < 1 || totalHands > 10);
        
        Deck singleDeck = new Deck(numPacks);
        Hand[] handList = new Hand[totalHands];
        
        //Creates hands objects and stores them in a array.
        for(int x = 0; x < handList.length; x++) {
            handList[x] = new Hand();
        }
     
        //Loops to put all cards in the deck into player hands.
        do {
            for(int playerIndex = 0; playerIndex < handList.length; playerIndex++) {
                if(singleDeck.getTopCard() > 0) {
                    handList[playerIndex].takeCard(singleDeck.dealCard());
                }
                else {
                    break;
                }
            }     
        }while(singleDeck.getTopCard() > 0);
         
        System.out.println("Here are our hands, from an unshuffled deck:");
        //Loops to print hands
        for(Hand hand : handList) {
            boolean badCard;
            int cardIndex = 0;
            String handString = "Hand = (";
            String formattedString = "";
            do {
                Card card = new Card();
                card = hand.inspectCard(cardIndex);
                badCard = card.getFlag();
                if(!badCard) {
                    handString += card.toString() + ", ";
                    cardIndex++;
                }else {
                    formattedString = handString.substring(0, handString.length() - 2) + ")";
                }     
            }while(!badCard);
            System.out.println(formattedString);
        }
        
        System.out.println();
        System.out.println("Please press ENTER to continue");
        keyboard.nextLine();
        keyboard.nextLine();
        
        //Resetting all objects and shuffling
        singleDeck.init(numPacks);
        singleDeck.shuffle();
        for(Hand hand : handList) {
            hand.resetHand();
        }
      
        //Loops to put all cards in the deck into player hands.
        do {
            for(int playerIndex = 0; playerIndex < handList.length; playerIndex++) {
                if(singleDeck.getTopCard() > 0) {
                    handList[playerIndex].takeCard(singleDeck.dealCard());
                }
                else {
                    break;
                }
            }     
        }while(singleDeck.getTopCard() > 0);
        
        System.out.println("Here are our hands, from a shuffled deck:");
        //Loops to print hands
        for(Hand hand : handList) {
            boolean badCard;
            int cardIndex = 0;
            String handString = "Hand = (";
            String formattedString = "";
            do {
                Card card = new Card();
                card = hand.inspectCard(cardIndex);
                badCard = card.getFlag();
                if(!badCard) {
                    handString += card.toString() + ", ";
                    cardIndex++;
                }else {
                    formattedString = handString.substring(0, handString.length() - 2) + ")";
                }     
            }while(!badCard);
            System.out.println(formattedString);
        }     
    }
}

//The Card class, simulates an ordinary playing card.
class Card
{
 public enum Suit
 {
    clubs, diamonds, hearts, spades
 }
 
 private char value;
 private Suit suit;
 private boolean errorFlag = false;
 
 //Default No Arguments Constructor
 public Card()
 {
    this.set( 'A', Suit.spades );
 }
 
 //With Arguments Constructor 
 public Card( char Value, Suit suit )
 {
    if ( this.set(Value, suit) )//If the boolean function 'set' evaluates to true, we have good data.
       this.errorFlag = false;
    else                        //If the boolean function 'set' evaluates to false, we have bad data.
       this.errorFlag = true;
 }
 
 /*
  * Set method: this method sets the card object's value and suit.   
  * @param: Value will be in the range A,2-9,T,J,Q,K while suit will be one of the 4 standard playing card suits.
  * @returns: True if the set operation was successful, false otherwise.
  */
 public boolean set( char value, Suit suit )
 {
    if ( isValid(value, suit) )
    {
       this.value = value;
       this.suit = suit;
       this.errorFlag = false;
       return true;
    }
    else
    {
       errorFlag = true;
       return false;
    }   
 }
 
 /*
  * isValid method: this method determines whether the card's ordinal number is correct.
  * @param: The card's value number and the card's suit
  * @returns: True if the data is valid, false otherwise   
  */
 private static boolean isValid(char value, Suit suit)//Can use '==' because char is a primitive type
 {
    if ( value == 'A' || value == '2' || value == '3' || value == '4' || value == '5' || value == '6' || value == '7'
          || value == '8' || value == '9' || value == 'T' || value == 'J' || value == 'Q' || value == 'K' )
          return true;
    else
       return false;
 }
 
 /*
  * toString method: Allows us to see/display a string representation of a card. 
  * @returns: Returns either the card as a string or an error message if the card has bad data.
  */
 public String toString()
 {
    if ( errorFlag == false )
    {
       return Character.toString(this.value) + " of " + this.suit.name();
    }
    
    else
    {
       return "Card is invalid. ";
    }
 }

 /*
  * getSuit method: Provides read only access to the suit variable.
  * @returns: Returns the card's suit.
  */
 public Suit getSuit()
 {
    return this.suit;
 }
 
 /*
  * getValue method: Provides read only access to the value variable.
  * @returns: Returns the card's ordinal number value.
  */
 public char getValue()
 {
    return this.value;
 }
 
 /*
  * getFlag method: Provides read only access to the errorFag which tells us if the card has bad data.
  * @returns: A false value indicates the card has good data, true indicates the card had bad data.
  */
 public boolean getFlag()
 {
    return errorFlag;
 }
 
 /*
  * equals method: This method determines whether two cards are equivalent, also returns true if two cards refer to 
  * the same card.
  * @returns: True if the cards have the same values in their storage variables or if both references refer to the 
  * same object.  
  */
 public boolean eqauls( Card card )
 {
    if ( card.suit == this.suit && card.value == this.value && card.errorFlag == this.errorFlag )
       return true;
    
    else
       return false;
 }
 
 //Test for the card class
 public static void main( String[] args )
 {
    Card card1 = new Card();
    Card card2 = new Card( 'T',Suit.hearts );
    Card card3 = new Card( '1',Suit.clubs );
    
    System.out.println( "Card1 = " + card1.toString() );
    System.out.println( "Card2 = " + card2.toString() );
    System.out.println( "Card3 = " + card3.toString() );
    
    card2.set( '0', Suit.diamonds );
    card3.set( 'K', Suit.diamonds );
    
    System.out.println( "New Card2 = " + card2.toString() );
    System.out.println( "New Card3 = " + card3.toString() );
 }
 
}//end of class bracket


class Hand
{
   public static final int MAX_CARDS = 50;
   private Card[] myCards;
   private int numCards;
   
   //Default constructor for Hand class, array is initialized
   public Hand()
   {
      myCards = new Card[MAX_CARDS];
      numCards = 0;
   }
   
   //removes all the cards by simply setting numCards = 0
   public void resetHand()
   {
      numCards = 0;
   }
   
   //Simulates taking a new card for the hand. If the hand is full, return false.
   //Otherwise, add an object copy of the card to the hand
   public boolean takeCard(Card card)
   {
      if(numCards == MAX_CARDS)
         return false;
      
      myCards[numCards] = new Card(card.getValue(), card.getSuit());
      numCards++;
      return true;      
   }
   
   //Play a card. Decrease numCards
   public Card playCard()
   {
      numCards--;
      return myCards[numCards]; //Returns the card at the old numCards - 1, which is the new numCards
   }
   
   //Puts all the values in the hand into a String
   public String toString()
   {
      String retStr = "";
      
      for(int i = 0; i < numCards; i++)
         retStr += myCards[i] + ", ";
      
      return retStr;
   }
   
   //Accessor for numCards
   public int getNumCards()
   {
      return numCards;
   }
   
   //returns the card in the hand at k if k is legal. Otherwise a card with
   //errorCard set to true is returned.
   Card inspectCard(int k)
   {
      //creates a card with errorFlag = true
      Card errorCard = new Card('B', Card.Suit.spades);
      
      if(k >= numCards)
         return errorCard;
      
      return myCards[k];
   }
}

//deck of 52 cards
class Deck {

  public static final int MAX_CARDS = 6 * 52;

  private static Card[] masterPack;
  private Card[] cards;
  private int topCard;
  private int numPacks;

  private static boolean once;

  // constructor with parameter for number of packs
  public Deck(int numPacks) {
      allocateMasterPack();
      cards = new Card[MAX_CARDS];
      init(numPacks);
  }

  // initiates deck of cards from masterPack for number of packs
  public void init(int numPacks) {
      if (topCard <= MAX_CARDS) {
          for (int i = 0; i < numPacks; i++)
              for (int k = 0; k < 52; k++)
                  cards[i * 52 + k] = masterPack[k];
          this.numPacks = numPacks;
          topCard = numPacks * 52;
      } else {
          return;
      }
  }

  // shuffles the deck of cards by switching each cards in the deck
  // with another random card
  public void shuffle() {
      Card temp;
      int random;

      for (int i = 0; i < topCard; i++) {
          random = (int) (topCard * Math.random());
          temp = cards[i];
          cards[i] = cards[random];
          cards[random] = temp;
      }
  }

  // deals a card from the top of the deck
  public Card dealCard() {
      if (topCard > 0) {
          Card dealt = cards[topCard - 1];
          cards[topCard - 1] = null;
          topCard--;
          return dealt;
      } else {
          return null;
      }
  }

  // pulls top card in the array
  public int getTopCard() {
      return topCard;
  }

  // Accessor for an individual card. Returns a card with errorFlag = true if
  // k is bad
  public Card inspectCard(int k) {
      Card card;
      if (k > topCard) {
          card = new Card('X', Card.Suit.diamonds);
      } else {
          card = cards[k];
      }
      return card;
  }

  // allocates the first 52 cards into the deck
  private static void allocateMasterPack() {
      if (!once) {
          masterPack = new Card[52];
          Card.Suit suit;
          int k, j;
          char val;

          for (k = 0; k < masterPack.length; k++) {
              masterPack[k] = new Card();
          }

          for (k = 0; k < 4; k++) {
              switch (k) {
              case 0:
                  suit = Card.Suit.clubs;
                  break;
              case 1:
                  suit = Card.Suit.diamonds;
                  break;
              case 2:
                  suit = Card.Suit.hearts;
                  break;
              case 3:
                  suit = Card.Suit.spades;
                  break;
              default:
                  suit = Card.Suit.spades;
                  break;
              }

              masterPack[13 * k].set('A', suit);
              for (val = '2', j = 1; val <= '9'; val++, j++)
                  masterPack[13 * k + j].set(val, suit);
              masterPack[13 * k + 9].set('T', suit);
              masterPack[13 * k + 10].set('J', suit);
              masterPack[13 * k + 11].set('Q', suit);
              masterPack[13 * k + 12].set('K', suit);
          }
          once = true;
      } else
          return;
  }

}

/*--------------- run #1 ----------------------------------
How many hands? (1 - 10, please): 7
Here are our hands, from an unshuffled deck:
Hand = (K of spades, 6 of spades, Q of hearts, 5 of hearts, J of diamonds, 4 of diamonds, T of clubs, 3 of clubs)
Hand = (Q of spades, 5 of spades, J of hearts, 4 of hearts, T of diamonds, 3 of diamonds, 9 of clubs, 2 of clubs)
Hand = (J of spades, 4 of spades, T of hearts, 3 of hearts, 9 of diamonds, 2 of diamonds, 8 of clubs, A of clubs)
Hand = (T of spades, 3 of spades, 9 of hearts, 2 of hearts, 8 of diamonds, A of diamonds, 7 of clubs)
Hand = (9 of spades, 2 of spades, 8 of hearts, A of hearts, 7 of diamonds, K of clubs, 6 of clubs)
Hand = (8 of spades, A of spades, 7 of hearts, K of diamonds, 6 of diamonds, Q of clubs, 5 of clubs)
Hand = (7 of spades, K of hearts, 6 of hearts, Q of diamonds, 5 of diamonds, J of clubs, 4 of clubs)

Please press ENTER to continue

Here are our hands, from a shuffled deck:
Hand = (Q of diamonds, J of hearts, 5 of hearts, 9 of spades, K of spades, 4 of diamonds, J of diamonds, T of hearts)
Hand = (2 of hearts, 9 of diamonds, K of clubs, 3 of clubs, Q of spades, 2 of clubs, T of clubs, 3 of hearts)
Hand = (K of diamonds, J of spades, 7 of hearts, Q of clubs, 6 of spades, 6 of hearts, 8 of hearts, 2 of spades)
Hand = (K of hearts, 8 of diamonds, A of clubs, 2 of diamonds, 5 of diamonds, 5 of spades, A of diamonds)
Hand = (5 of clubs, A of hearts, 6 of clubs, Q of hearts, 7 of clubs, 9 of hearts, J of clubs)
Hand = (8 of spades, A of spades, 6 of diamonds, T of diamonds, 7 of spades, 9 of clubs, 4 of hearts)
Hand = (4 of clubs, 4 of spades, 8 of clubs, T of spades, 7 of diamonds, 3 of diamonds, 3 of spades)
*/

/*--------------- run #2 ----------------------------------
How many hands? (1 - 10, please): -1
How many hands? (1 - 10, please): 20
How many hands? (1 - 10, please): 3
Here are our hands, from an unshuffled deck:
Hand = (K of spades, T of spades, 7 of spades, 4 of spades, A of spades, J of hearts, 8 of hearts, 5 of hearts, 2 of hearts, Q of diamonds, 9 of diamonds, 6 of diamonds, 3 of diamonds, K of clubs, T of clubs, 7 of clubs, 4 of clubs, A of clubs)
Hand = (Q of spades, 9 of spades, 6 of spades, 3 of spades, K of hearts, T of hearts, 7 of hearts, 4 of hearts, A of hearts, J of diamonds, 8 of diamonds, 5 of diamonds, 2 of diamonds, Q of clubs, 9 of clubs, 6 of clubs, 3 of clubs)
Hand = (J of spades, 8 of spades, 5 of spades, 2 of spades, Q of hearts, 9 of hearts, 6 of hearts, 3 of hearts, K of diamonds, T of diamonds, 7 of diamonds, 4 of diamonds, A of diamonds, J of clubs, 8 of clubs, 5 of clubs, 2 of clubs)

Please press ENTER to continue

Here are our hands, from a shuffled deck:
Hand = (8 of diamonds, T of clubs, 4 of hearts, T of diamonds, A of spades, J of spades, 3 of diamonds, 6 of clubs, 5 of spades, 2 of spades, 6 of hearts, Q of spades, Q of diamonds, 3 of spades, 2 of diamonds, J of clubs, K of clubs, 7 of clubs)
Hand = (A of clubs, 5 of diamonds, Q of hearts, 4 of spades, 2 of hearts, 4 of clubs, J of hearts, 8 of spades, 8 of hearts, Q of clubs, 9 of diamonds, 8 of clubs, 7 of hearts, 6 of spades, A of diamonds, K of diamonds, 9 of hearts)
Hand = (6 of diamonds, 3 of hearts, 7 of spades, T of hearts, 7 of diamonds, 5 of hearts, 4 of diamonds, T of spades, J of diamonds, 9 of spades, K of spades, K of hearts, 2 of clubs, A of hearts, 9 of clubs, 3 of clubs, 5 of clubs)
*/

/*--------------- run #3 ----------------------------------
How many hands? (1 - 10, please): 5
Here are our hands, from an unshuffled deck:
Hand = (K of spades, 8 of spades, 3 of spades, J of hearts, 6 of hearts, A of hearts, 9 of diamonds, 4 of diamonds, Q of clubs, 7 of clubs, 2 of clubs)
Hand = (Q of spades, 7 of spades, 2 of spades, T of hearts, 5 of hearts, K of diamonds, 8 of diamonds, 3 of diamonds, J of clubs, 6 of clubs, A of clubs)
Hand = (J of spades, 6 of spades, A of spades, 9 of hearts, 4 of hearts, Q of diamonds, 7 of diamonds, 2 of diamonds, T of clubs, 5 of clubs)
Hand = (T of spades, 5 of spades, K of hearts, 8 of hearts, 3 of hearts, J of diamonds, 6 of diamonds, A of diamonds, 9 of clubs, 4 of clubs)
Hand = (9 of spades, 4 of spades, Q of hearts, 7 of hearts, 2 of hearts, T of diamonds, 5 of diamonds, K of clubs, 8 of clubs, 3 of clubs)

Please press ENTER to continue

Here are our hands, from a shuffled deck:
Hand = (Q of diamonds, K of hearts, T of diamonds, 8 of spades, Q of hearts, 6 of diamonds, T of hearts, 8 of hearts, 2 of spades, A of diamonds, 7 of hearts)
Hand = (9 of spades, K of clubs, 2 of clubs, J of diamonds, J of hearts, J of clubs, A of clubs, 4 of hearts, 6 of hearts, 4 of diamonds, 6 of spades)
Hand = (K of spades, 5 of spades, 4 of clubs, 7 of diamonds, 3 of diamonds, 5 of hearts, 2 of hearts, 8 of clubs, 5 of diamonds, Q of clubs)
Hand = (2 of diamonds, 5 of clubs, 3 of hearts, 3 of clubs, 4 of spades, 6 of clubs, 7 of clubs, T of spades, J of spades, 9 of hearts)
Hand = (T of clubs, Q of spades, 9 of clubs, 9 of diamonds, 3 of spades, K of diamonds, A of spades, A of hearts, 7 of spades, 8 of diamonds)
*/
