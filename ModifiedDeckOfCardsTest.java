// Exercise: 7.16 ModifiedDeckofCardsTest.java
// Card shuffling and dealing
// Modify application of Fig. 7.13 (DeckOfCardsTest) to deal a five card
// poker hand. Must also modify Fig. 7.12 (DeckOfCards) to include methods to
// determine whether a hand contains certain results

public class ModifiedDeckOfCardsTest
{
  //execute application
  public static void main(String[] args)
  {
    ModifiedDeckOfCards myDeckOfCards = new ModifiedDeckOfCards();
    myDeckOfCards.shuffle();  // place cards in random order

    ModifiedCard[] pokerHand = new ModifiedCard[5];
    // deal a hand of five cards
    for(int i = 0; i < 5; i++)
    {
      // deal and display a Card
      pokerHand[i] = myDeckOfCards.dealCard();
      System.out.printf("%-19s", pokerHand[i]);
    }
  System.out.println();

  }
}
