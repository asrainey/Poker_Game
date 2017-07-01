// Exercise: 7.18 PokerWithDealerHand.java
// Card shuffling and dealing
// Modify application from Exercise. 7.17 (TwoHandPoker) to simulate a dealer
// Dealer's hand is dealt "face down" so the player can't see it.
// Application should evaluate the dealer's hand, and based on the quality of
// the hand, draw one, two or three more cards to replace the unneeded cards
// in the original hand. Application should then reevaluate the dealer's hand

public class PokerWithDealerHand
{
  //execute application
  public static void main(String[] args)
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();
    myDeckOfCards.shuffle();  // place cards in random order

    ModifiedCard[] pokerHand = new ModifiedCard[5];
    System.out.println("Player 1 =\n");
    // deal a hand of five cards
    for(int i = 0; i < 5; i++)
    {
      // deal and display a Card
      pokerHand[i] = myDeckOfCards.dealCard();
      System.out.printf("%-19s", pokerHand[i]);
    }
    System.out.println();

    int handRanking = myDeckOfCards.rankHand(pokerHand);

    ModifiedCard[] pokerHand2 = new ModifiedCard[5];
    System.out.println("Player 2 =\n");

    for(int i = 0; i < 5; i++)
    {
      // deal and display a Card
      pokerHand2[i] = myDeckOfCards.dealCard();
      System.out.printf("%-19s", pokerHand2[i]);
    }
    System.out.println();

    int handRanking2 = myDeckOfCards.rankHand(pokerHand2);

    if(handRanking > handRanking2)
    {
      System.out.println("Player 1 has the better hand.\n");
    }
    else if (handRanking < handRanking2)
    {
      System.out.println("Player 2 has the better hand.\n");
    }
    else
    {
      System.out.println("Both players have the same hand.");
    }
  }
}
