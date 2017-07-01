// Exercise 7.18: DeckOfCardsDealer.java
// ModifiedDeckOfCards2 class represents a deck of playing cards
// This application modifies Exercise 7.17: ModifiedDeckOfCards2.java to update
// methods to include a dealer hand and allow for a second draw to replace
// unneeded cards

import java.security.SecureRandom;

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

  public int reviewSuits(ModifiedCard[] hand)
  {
    int[] suitFrequency = new int[4];
    int ranking = 0;

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

  public int reviewFaces(ModifiedCard[] hand)
  {
    int[] faceFrequency = new int[13];
    int ranking = 0;

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

    for(int k = 0; k < faceFrequency.length; k++)
    {
      if(faceFrequency[k] == 2)
      {
        for(int i = k + 1; i < faceFrequency.length; i++)
        {
          if(faceFrequency[i] == 3)
          {
            ranking = 7;
          }
          else if(faceFrequency[i] == 2)
          {
            ranking = 2;
          }
        }
        ranking = 1;
        break;
      }

      if(faceFrequency[k] == 3)
      {
        for(int i = k + 1; i < faceFrequency.length; i++)
        {
          if(faceFrequency[i] == 2)
          {
            ranking = 7;
          }
        }
        ranking = 3;
      }

      if(faceFrequency[k] == 4)
      {
        ranking = 4;
      }
    }

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
}
