// Exercise 7.19: DeckOfCardsPoker.java
// DeckOfCardsPoker class represents a deck of playing cards
// This application modifies Exercise 7.18: DeckOfCardsDealer.java to update
// methods to include a method that allows the player to choose which cards in
// their hand to discard and then draw again

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class DeckOfCardsPoker
{
  private ModifiedCard[] deck;    // array of Card objects
  private int currentCard;  // index of next Card to be dealt (0-51)
  private static final int NUMBER_OF_CARDS = 52; // constant number of cardSuit
  // random number generator
  private static final SecureRandom randomNumbers = new SecureRandom();

  //constructor fills deck of cardSuit
  public DeckOfCardsPoker()
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
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();
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
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();
    myDeckOfCards.shuffle();  // place cards in random order

    ModifiedCard[] pokerHand = new ModifiedCard[5];
    // deal a hand of five cards
    for(int i = 0; i < 5; i++)
    {
      // deal and display a Card
      pokerHand[i] = myDeckOfCards.dealCard();
    }

    return pokerHand;
  }

  public ModifiedCard dealSingleCard(ModifiedCard[] pokerHand)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();

    myDeckOfCards.shuffle();  // place cards in random order

    ModifiedCard newCard = myDeckOfCards.dealCard();

    while(newCard == pokerHand[0] || newCard == pokerHand[1] ||
          newCard == pokerHand[2] || newCard == pokerHand[3] ||
          newCard == pokerHand[4])
    {
      newCard = myDeckOfCards.dealCard();
    }

    return newCard;
  }

  public int[] determineSuitFrequency(ModifiedCard[] hand)
  {
    int[] suitFrequency = new int[4];

    for(int k = 0; k < hand.length; k++)
    {
      String suit = hand[k].getSuit();

      switch(suit)
      {
        case "Hearts":
          ++suitFrequency[0];
          break;
        case "Diamonds":
          ++suitFrequency[1];
          break;
        case "Clubs":
          ++suitFrequency[2];
          break;
        case "Spades":
          ++suitFrequency[3];
          break;
      }
    }
    return suitFrequency;
  }

  public int reviewSuits(ModifiedCard[] hand)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();

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
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();

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
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();

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
        System.out.println("This hand contains a pair");
        break;
      case 2:
        System.out.println("This hand contains two pairs");
        break;
      case 3:
        System.out.println("This hand contains a three of a kind");
        break;
      case 4:
        System.out.println("This hand contains a four of a kind");
        break;
      case 5:
        System.out.println("This hand contains a flush");
        break;
      case 6:
        System.out.println("This hand contains a straight");
        break;
      case 7:
        System.out.println("This hand contains a full house");
        break;
      default:
        System.out.println();
        break;
    }
    return handRanking;
  }

  public int rankHandLimited(ModifiedCard[] pokerHand)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();

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
    return handRanking;
  }

  public ModifiedCard[] updateCardsinHand(ModifiedCard[] pokerHand)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();
    int handRanking = myDeckOfCards.rankHandLimited(pokerHand);

    ModifiedCard[] updatedPokerHand = myDeckOfCards.sorting(pokerHand);

    String face_1 = updatedPokerHand[0].getFace();
    String face_2 = updatedPokerHand[1].getFace();
    String face_3 = updatedPokerHand[2].getFace();
    String face_4 = updatedPokerHand[3].getFace();
    String face_5 = updatedPokerHand[4].getFace();

    if(handRanking == 0) // replace any three cards
    {
      int num = randomNumbers.nextInt(3);
      updatedPokerHand[num] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      num = randomNumbers.nextInt(3);
      updatedPokerHand[num] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      num = randomNumbers.nextInt(3);
      updatedPokerHand[num] = myDeckOfCards.dealSingleCard(updatedPokerHand);
    }

    if(handRanking == 1)  // replace three cards
    {
      if(face_1 == face_2)
      {
        updatedPokerHand[2] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[3] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[4] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
      else if (face_2 == face_3)
      {
        updatedPokerHand[0] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[3] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[4] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
      else if (face_3 == face_4)
      {
        updatedPokerHand[0] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[1] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[4] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
      else
      {
        updatedPokerHand[0] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[1] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[2] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
    }
    else if (handRanking == 3) // replace 2 cards
    {
      if(face_1 == face_3)
      {
        updatedPokerHand[3] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[4] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
      else if(face_2 == face_4)
      {
        updatedPokerHand[0] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        updatedPokerHand[4] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
      else
      {
      updatedPokerHand[0] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      updatedPokerHand[1] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
    }

    else if (handRanking == 2 || handRanking == 4)  // replace 1 card
    {
      if(face_1 == face_4)
      {
        updatedPokerHand[4] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
      else
      {
        updatedPokerHand[0] = myDeckOfCards.dealSingleCard(updatedPokerHand);
      }
    }
    // don't need to replace any cards if handRanking == 5, 6, 7
    return updatedPokerHand;
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

  public ModifiedCard[] playerRedraw(ModifiedCard[] pokerHand)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();
    Scanner input = new Scanner(System.in);

    ModifiedCard[] updatedPokerHand = pokerHand;
    System.out.println("You may replace up to three cards in your hand.");

    for(int k = 0; k < 3; k++)
    {
      System.out.print("Would you like to draw a replacement card? Y/N: ");
      String answer = input.nextLine();

      if(answer.equalsIgnoreCase("Y"))
      {
        updatedPokerHand = myDeckOfCards.replaceCard(updatedPokerHand);
      }
      else if(answer.equalsIgnoreCase("N"))
      {
        System.out.println("Your final hand - ");
        for(int i = 0; i < 5; i++)
        {
          // display dealer hand
          System.out.printf("%-19s", updatedPokerHand[i]);
        }
        System.out.println();
        break;
      }
      else
      {
        System.out.println("That is not a valid answer.");
        k--;
      }
    }
    return updatedPokerHand;
  }

  public ModifiedCard[] replaceCard(ModifiedCard[] pokerHand)
  {
    DeckOfCardsPoker myDeckOfCards = new DeckOfCardsPoker();
    Scanner input = new Scanner(System.in);

    ModifiedCard[] updatedPokerHand = myDeckOfCards.sorting(pokerHand);

    System.out.print("Which card would you like to discard? ");
    String card = input.nextLine();
    String[] cardAttributes = card.split(" ");

    String cardFace = cardAttributes[0];
    String cardSuit = cardAttributes[cardAttributes.length - 1];

    String[] handFaces = new String[5];
    String[] handSuits = new String[5];

    // create handFaces array
    for(int k = 0; k < updatedPokerHand.length; k++)
    {
      handFaces[k] = updatedPokerHand[k].getFace();
    }

    // create handSuits array
    for(int k = 0; k < updatedPokerHand.length; k++)
    {
      handSuits[k] = updatedPokerHand[k].getSuit();
    }

    for (int i = 0; i < handFaces.length; i++)
    {
      if(cardFace.equalsIgnoreCase(handFaces[i]) &&
        cardSuit.equalsIgnoreCase(handSuits[i]))
      {
        updatedPokerHand[i] = myDeckOfCards.dealSingleCard(updatedPokerHand);
        break;
      }
    }

    System.out.println("Your updated hand - ");
    for(int i = 0; i < 5; i++)
    {
      System.out.printf("%-19s", updatedPokerHand[i]);
    }
    System.out.println();
    return updatedPokerHand;
  }
}
