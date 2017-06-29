// Exercise 7.16: ModifiedDeckOfCarts.java
// ModifiedDeckOfCardsTestDeckOfCards class represents a deck of playing cards
// This application modifies Fig 7.12: DeckOfCards.java to include methods to
// determine whether a hand contains:
// A pair, two pairs, three of a kind, four of a kind, a flush (five cards of
// the same suit), a straight (five cards of consecutive face values),
// a full house (two cards of one face value and three cards of another face value)


import java.security.SecureRandom;

public class ModifiedDeckOfCards
{
  private Card[] deck;    // array of Card objects
  private int currentCard;  // index of next Card to be dealt (0-51)
  private static final int NUMBER_OF_CARDS = 52; // constant number of cardSuit
  // random number generator
  private static final SecureRandom randomNumbers = new SecureRandom();

  //constructor fills deck of cardSuit
  public DeckOfCards()
  {
    String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
      "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    deck = new Card[NUMBER_OF_CARDS];   // create an array of card objects
    currentCard = 0;   // first card dealt will be deck[0]

    // populate deck with Card objects
    for(int count = 0; count < deck.length; count++)
      deck[count] =
        new Card(faces[count % 13], suits[count / 13]);
  }

  // shuffle deck of Cards with one-pass algorithm
  public void shuffle()
  {
    // next call to method dealCard should start at deck[0] again
    currentCard = 0;

    // for each Card, pick another random Card (0-51) and swap them
    for(int first = 0; first < deck.length; first++)
    {
      // select a random number between 0 and 51
      int second = randomNumbers.nextInt(NUMBER_OF_CARDS);

      // swap current Card with randomly selected Card
      Card temp = deck[first];
      deck[first] = deck[second];
      deck[second] = temp;

    }
  }

  // deal one Card
  public Card dealCard()
  {
    // determine whether Cards remain to be dealt
    if(currentCard < deck.length)
      return deck[currentCard++];  // return current Card in array
    else
      return null;  // return null to indicate all cards were dealt
  }
}
