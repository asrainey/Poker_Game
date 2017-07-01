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

    ModifiedCard[] dealerHand = myDeckOfCards.dealDealerHand();

    ModifiedCard[] player1 = myDeckOfCards.dealHand();
    System.out.println("Hand for Player 1");
    int handRanking = myDeckOfCards.rankHand(player1);

    ModifiedCard[] player2 = myDeckOfCards.dealHand();
    System.out.println("Hand for Player 2");
    int handRanking2 = myDeckOfCards.rankHand(player2);

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
