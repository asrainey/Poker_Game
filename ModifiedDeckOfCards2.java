// Exercise 7.17: ModifiedDeckOfCards2.java
// ModifiedDeckOfCards2 class represents a deck of playing cards
// This application modifies Exercise 7.16: ModifiedDeckOfCards.java to update
// methods to give each hand type a ranking so multiple hands can be compared

import java.security.SecureRandom;

public class ModifiedDeckOfCards2
{
  private ModifiedCard[] deck;    // array of Card objects
  private int currentCard;  // index of next Card to be dealt (0-51)
  private static final int NUMBER_OF_CARDS = 52; // constant number of cardSuit
  // random number generator
  private static final SecureRandom randomNumbers = new SecureRandom();

  //constructor fills deck of cardSuit
  public ModifiedDeckOfCards2()
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
        System.out.println("Your hand contains a flush");
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
    // test array
    //for(int k = 0; k < faceFrequency.length; k++)
    //{
    //  System.out.printf("faceFrequency[%d] = %d\n", k, faceFrequency[k]);
    //}

    for(int k = 0; k < faceFrequency.length; k++)
    {
      if(faceFrequency[k] == 2)
      {
        for(int i = k + 1; i < faceFrequency.length; i++)
        {
          if(faceFrequency[i] == 3)
          {
            System.out.println("Your hand contains a full house");
            ranking = 7;
          }
          else if(faceFrequency[i] == 2)
          {
            System.out.println("Your hand contains two pairs");
            ranking = 2;
          }
        }
        System.out.println("Your hand contains a pair");
        ranking = 1;
        break;
      }

      if(faceFrequency[k] == 3)
      {
        for(int i = k + 1; i < faceFrequency.length; i++)
        {
          if(faceFrequency[i] == 2)
          {
            System.out.println("Your hand contains a full house");
            ranking = 7;
          }
        }
        System.out.println("Your hand contains a three of a kind");
        ranking = 3;
      }

      if(faceFrequency[k] == 4)
      {
        System.out.println("Your hand contains a four of a kind");
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
              System.out.println("Your hand contains a straight");
              ranking = 6;
            }
          }
        }
      }
    }
    return ranking;
  }

  public int rankHand(int faceRanking, int suitRanking)
  {
    int handRanking = 0;

    if(faceRanking > suitRanking)
    {
      handRanking = faceRanking;
    }
    else
    {
      handRanking = suitRanking;
    }
    return handRanking;
  }
}
