// Exercise: 7.17 TwoHandPoker.java
// Card shuffling and dealing
// Modify application from Exercise. 7.16 (ModifiedDeckOfCardsTest) to deal
// two five card poker hands. Application should evaluate both hands to
// determine which is better

public class TwoHandPoker
{
  //execute application
  public static void main(String[] args)
  {
    ModifiedDeckOfCards2 myDeckOfCards = new ModifiedDeckOfCards2();
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

    int faceRanking = myDeckOfCards.reviewFaces(pokerHand);
    int suitRanking = myDeckOfCards.reviewSuits(pokerHand);

    ModifiedCard[] pokerHand2 = new ModifiedCard[5];
    System.out.println("Player 2 =\n");

    for(int i = 0; i < 5; i++)
    {
      // deal and display a Card
      pokerHand2[i] = myDeckOfCards.dealCard();
      System.out.printf("%-19s", pokerHand2[i]);
    }
    System.out.println();

    int faceRanking2 = myDeckOfCards.reviewFaces(pokerHand2);
    int suitRanking2 = myDeckOfCards.reviewSuits(pokerHand2);

    int handRanking = myDeckOfCards.rankHand(faceRanking, suitRanking);
    int handRanking2 = myDeckOfCards.rankHand(faceRanking2, suitRanking2);

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
