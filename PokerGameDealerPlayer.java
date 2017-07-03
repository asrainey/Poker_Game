// Exercise: 7.19 PokerGameDealerPlayer.java
// Card shuffling and dealing
// Modify application from Exercise. 7.18 (PokerWithDealerHand.java) to allow
// player to discard cards and draw a second time.
// Dealer's hand is dealt "face down" so the player can't see it.
// Application should automatically evaluate and update the dealer's hand
// Application should then compare the two hands to determine winner

public class PokerGameDealerPlayer
{
  //execute application
  public static void main(String[] args)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();

    // Dealer actions
    ModifiedCard[] dealerHand = myDeckOfCards.dealDealerHand();
    ModifiedCard[] updatedDealerHand = myDeckOfCards.updateCardsinHand(dealerHand);

    // Player actions
    ModifiedCard[] player1 = myDeckOfCards.dealHand();
    System.out.println("-- Hand for Player 1 --");
    int handRanking = myDeckOfCards.rankHand(player1);

    //ModifiedCard[] player2 = myDeckOfCards.dealHand();
    //System.out.println("-- Hand for Player 2 --");
    //int handRanking2 = myDeckOfCards.rankHand(player2);

    System.out.println("Dealer's hand is: ");
    for(int i = 0; i < 5; i++)
    {
      // display original dealer hand
      System.out.printf("%-19s", updatedDealerHand[i]);
    }
    System.out.println();
    int updatedDealerHandRanking = myDeckOfCards.rankHand(updatedDealerHand);

    if(updatedDealerHandRanking > handRanking)
    {
      System.out.println("Dealer wins.\n");
    }
    else if (handRanking < updatedDealerHandRanking)
    {
      System.out.println("Congratulations, you win!\n");
    }
    else
    {
      System.out.println("Both players have the same hand.");
    }
  }
}
