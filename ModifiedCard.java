// Exercise 7.16: ModifiedCard.java
// Also used with Exercise 7.17, 7.18
// ModifiedCard class represents a playing card
// This application modifies Fig. 7.11: Card.java to add methods
// getFace and getSuit


public class ModifiedCard
{
  private final String face; // face of card ("Ace", "Deuce", ...)
  private final String suit;  // suit of card ("Hearts", "Diamonds", ...)

  // two argument constructor initializes card's face and suit
  public ModifiedCard(String cardFace, String cardSuit)
  {
    this.face = cardFace;  // initializes face of cardFace
    this.suit = cardSuit;  // initializes suit of card
  }

  // return String representation of Card
  public String toString()
  {
    return face + " of " + suit;
  }

  public String getFace()
  {
    return face;
  }

  public String getSuit()
  {
    return suit;
  }
}
