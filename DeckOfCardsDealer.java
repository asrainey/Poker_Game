// Exercise 7.18: DeckOfCardsDealer.java
// ModifiedDeckOfCards2 class represents a deck of playing cards
// This application modifies Exercise 7.17: ModifiedDeckOfCards2.java to update
// methods to include a dealer hand and allow for a second draw to replace
// unneeded cards

import java.security.SecureRandom;
import java.util.Arrays;

public class DeckOfCardsDealer
{
  private ModifiedCard[] deck;    // array of Card objects
  private int currentCard;  // index of next Card to be dealt (0-51)
  private static final int NUMBER_OF_CARDS = 52; // constant number of cardSuit
  // random number generator
  private static final SecureRandom randomNumbers = new SecureRandom();

  //constructor fills deck of cardSuit
  public DeckOfCardsDealer()
  {
    String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six",
      "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

    deck = new ModifiedCard[NUMBER_OF_CARDS];   // create an array of card objects
    currentCard = 0;   // first card dealt will be deck[0]

    // populate deck with Card objects
    for(int count = 0; count < deck.length; count++)
      deck[count] =
        new ModifiedCard(faces[count % 13], suits[count / 13]);
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
      ModifiedCard temp = deck[first];
      deck[first] = deck[second];
      deck[second] = temp;

    }
  }

  // deal one Card
  public ModifiedCard dealCard()
  {
    // determine whether Cards remain to be dealt
    if(currentCard < deck.length)
      return deck[currentCard++];  // return current Card in array
    else
      return null;  // return null to indicate all cards were dealt
  }

  public ModifiedCard[] dealHand()
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();
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

    return pokerHand;
  }

  public ModifiedCard[] dealDealerHand()
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();
    myDeckOfCards.shuffle();  // place cards in random order

    ModifiedCard[] pokerHand = new ModifiedCard[5];
    // deal a hand of five cards
    for(int i = 0; i < 5; i++)
    {
      // deal and display a Card
      pokerHand[i] = myDeckOfCards.dealCard();
    }
    System.out.println();

    return pokerHand;
  }

  public int[] determineSuitFrequency(ModifiedCard[] hand)
  {
    int[] suitFrequency = new int[4];

    for(int k = 0; k < hand.length; k++)
    {
      String face = hand[k].getSuit();

      if(face.equals("Hearts"))
      {
        ++suitFrequency[0];
      }
      else if(face.equals("Diamonds"))
      {
        ++suitFrequency[1];
      }
      else if(face.equals("Clubs"))
      {
        ++suitFrequency[2];
      }
      else
      {
        ++suitFrequency[3];
      }
    }
    return suitFrequency;
  }

  public int reviewSuits(ModifiedCard[] hand)
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();

    int[] suitFrequency = myDeckOfCards.determineSuitFrequency(hand);
    int ranking = 0;

    for(int k = 0; k < suitFrequency.length; k++)
    {
      if(suitFrequency[k] == 5)
      {
        ranking = 5;
      }
      else
      {
        ranking = 0;
      }
    }
    return ranking;
  }

  public int[] determineFaceFrequency(ModifiedCard[] hand)
  {
    int[] faceFrequency = new int[13];

    for(int k = 0; k < hand.length; k++)
    {
      String face = hand[k].getFace();

      switch(face)
      {
        case "Ace":
          ++faceFrequency[0];
          break;
        case "Deuce":
          ++faceFrequency[1];
          break;
        case "Three":
          ++faceFrequency[2];
          break;
        case "Four":
          ++faceFrequency[3];
          break;
        case "Five":
          ++faceFrequency[4];
          break;
        case "Six":
          ++faceFrequency[5];
          break;
        case "Seven":
          ++faceFrequency[6];
          break;
        case "Eight":
          ++faceFrequency[7];
          break;
        case "Nine":
          ++faceFrequency[8];
          break;
        case "Ten":
          ++faceFrequency[9];
          break;
        case "Jack":
          ++faceFrequency[10];
            break;
        case "Queen":
          ++faceFrequency[11];
          break;
        case "King":
          ++faceFrequency[12];
          break;
      }
    }
    return faceFrequency;
  }

  public int reviewFaces(ModifiedCard[] hand)
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();

    int[] faceFrequency = myDeckOfCards.determineFaceFrequency(hand);

    int ranking = 0;

    for(int n = 0; n < (faceFrequency.length - 5); ++n)
    if(faceFrequency[n] == 1)
    {
      if(faceFrequency[n + 1] == 1)
      {
        if(faceFrequency[n + 2] == 1)
        {
          if(faceFrequency[n + 3] == 1)
          {
            if(faceFrequency[n + 4] == 1)
            {
              ranking = 6;
            }
          }
        }
      }
    }

    Arrays.sort(faceFrequency);

    for(int k = 0; k < (faceFrequency.length - 1); ++k)
    {
      if(faceFrequency[k] == 2)
      {
        if(faceFrequency[k + 1] == 3)
        {
          ranking = 7;
        }
        else if(faceFrequency[k + 1] == 2)
        {
          ranking = 2;
        }
      }

      if (faceFrequency[faceFrequency.length - 1] == 2 &&
          faceFrequency[faceFrequency.length - 2] != 2)
      {
        ranking = 1;
      }

      if(faceFrequency[k] == 3 || faceFrequency[k + 1] == 3)
      {
        ranking = 3;
      }

      if(faceFrequency[k] == 4 || faceFrequency[k + 1] == 4)
      {
        ranking = 4;
      }
    }

    //for(int k = 0; k < faceFrequency.length; k++)
    //{
    //  System.out.printf("%d: %d\n", k, faceFrequency[k]);
    //}
    return ranking;
  }

  public int rankHand(ModifiedCard[] pokerHand)
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();

    int faceRanking = myDeckOfCards.reviewFaces(pokerHand);
    int suitRanking = myDeckOfCards.reviewSuits(pokerHand);

    int handRanking = 0;

    if(faceRanking > suitRanking)
    {
      handRanking = faceRanking;
    }
    else
    {
      handRanking = suitRanking;
    }

    switch(handRanking)
    {
      case 1:
        System.out.println("Your hand contains a pair");
        break;
      case 2:
        System.out.println("Your hand contains two pairs");
        break;
      case 3:
        System.out.println("Your hand contains a three of a kind");
        break;
      case 4:
        System.out.println("Your hand contains a four of a kind");
        break;
      case 5:
        System.out.println("Your hand contains a flush");
        break;
      case 6:
        System.out.println("Your hand contains a straight");
        break;
      case 7:
        System.out.println("Your hand contains a full house");
        break;
      default:
        System.out.println();
        break;
    }
    System.out.println();
    return handRanking;
  }

  public ModifiedCard[] updateCardsinHand(ModifiedCard[] pokerHand)
  {
    DeckOfCardsDealer myDeckOfCards = new DeckOfCardsDealer();
    int handRanking = myDeckOfCards.rankHand(pokerHand);

    if(handRanking == 0) // replace any three cards
    {
      pokerHand[2] = myDeckOfCards.dealCard();
      pokerHand[3] = myDeckOfCards.dealCard();
      pokerHand[4] = myDeckOfCards.dealCard();
    }

    ModifiedCard[] updatedPokerHand = myDeckOfCards.sorting(pokerHand);

    String face_1 = updatedPokerHand[0].getFace();
    String face_2 = updatedPokerHand[1].getFace();
    String face_3 = updatedPokerHand[2].getFace();
    String face_4 = updatedPokerHand[3].getFace();
    String face_5 = updatedPokerHand[4].getFace();

    if(handRanking == 1)  // replace three cards
    {
      if(face_1 == face_2)
      {
        updatedPokerHand[2] = myDeckOfCards.dealCard();
        updatedPokerHand[3] = myDeckOfCards.dealCard();
        updatedPokerHand[4] = myDeckOfCards.dealCard();
      }
      else if (face_2 == face_3)
      {
        updatedPokerHand[0] = myDeckOfCards.dealCard();
        updatedPokerHand[3] = myDeckOfCards.dealCard();
        updatedPokerHand[4] = myDeckOfCards.dealCard();
      }
      else if (face_3 == face_4)
      {
        updatedPokerHand[0] = myDeckOfCards.dealCard();
        updatedPokerHand[1] = myDeckOfCards.dealCard();
        updatedPokerHand[4] = myDeckOfCards.dealCard();
      }
      else
      {
        updatedPokerHand[0] = myDeckOfCards.dealCard();
        updatedPokerHand[1] = myDeckOfCards.dealCard();
        updatedPokerHand[2] = myDeckOfCards.dealCard();
      }
    }
    else if (handRanking == 3) // replace 2 cards
    {
      if(face_1 == face_3)
      {
        updatedPokerHand[3] = myDeckOfCards.dealCard();
        updatedPokerHand[4] = myDeckOfCards.dealCard();
      }
      else if(face_2 == face_4)
      {
        updatedPokerHand[0] = myDeckOfCards.dealCard();
        updatedPokerHand[4] = myDeckOfCards.dealCard();
      }
      else
      {
      updatedPokerHand[0] = myDeckOfCards.dealCard();
      updatedPokerHand[1] = myDeckOfCards.dealCard();
      }
    }

    else if (handRanking == 2 || handRanking == 4)  // replace 1 card
    {
      if(face_1 == face_4)
      {
        updatedPokerHand[4] = myDeckOfCards.dealCard();
      }
      else
      {
        updatedPokerHand[0] = myDeckOfCards.dealCard();
      }
    }
    // don't need to replace any cards if handRanking == 5, 6, 7
    return pokerHand;
  }

  public ModifiedCard[] sorting(ModifiedCard[] pokerHand)
  {
    ModifiedCard[] updatedPokerHand = new ModifiedCard[5];
    updatedPokerHand = pokerHand;

    int[] tempPokerSort = new int[5];

    for(int k = 0; k < pokerHand.length; k++)
    {
      String face = pokerHand[k].getFace();

      switch(face)
      {
        case "Ace":
          tempPokerSort[k] = 1;
          break;
        case "Deuce":
          tempPokerSort[k] = 2;
          break;
        case "Three":
          tempPokerSort[k] = 3;
          break;
        case "Four":
          tempPokerSort[k] = 4;
          break;
        case "Five":
          tempPokerSort[k] = 5;
          break;
        case "Six":
          tempPokerSort[k] = 6;
          break;
        case "Seven":
          tempPokerSort[k] = 7;
          break;
        case "Eight":
          tempPokerSort[k] = 8;
          break;
        case "Nine":
          tempPokerSort[k] = 9;
          break;
        case "Ten":
          tempPokerSort[k] = 10;
          break;
        case "Jack":
          tempPokerSort[k] = 11;
          break;
        case "Queen":
          tempPokerSort[k] = 12;
          break;
        case "King":
          tempPokerSort[k] = 13;
          break;
      }
    }

    updatedPokerHand[0] = pokerHand[0];

    for(int i = 0; i < tempPokerSort.length-1; i++)
    {
      int smallest = i;

      for(int index = i + 1; index < tempPokerSort.length; index++)
      {
        if(tempPokerSort[index] < tempPokerSort[smallest])
        {
          ModifiedCard tempCard = updatedPokerHand[smallest];
          updatedPokerHand[smallest] = updatedPokerHand[index];
          updatedPokerHand[index] = tempCard;
          int tempVar = tempPokerSort[smallest];
          tempPokerSort[smallest] = tempPokerSort[index];
          tempPokerSort[index] = tempVar;

        }
      }
    }
    return updatedPokerHand;
  }
}
