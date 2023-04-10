package ru.rtf;

import java.util.ArrayList;
import java.util.Scanner;

public class ColodaDeal {

  private static ArrayList<String> coloda = new ArrayList<>();

  public static void main(String[] args) {
    createColoda36();
    System.out.println("Введите количество игроков (не более 6)");
    Scanner scan = new Scanner(System.in);
    int playersCount = Integer.parseInt(scan.next());
    giveCards(playersCount);
  }

  private static void createColoda36() {
    String[] masti = {"ch", "p", "t", "b" };
    String[] values = {"6", "7", "8", "9", "10", "V", "D", "K", "T"};
    for (String m : masti) {
      for (String v : values) {
        ColodaDeal.coloda.add(v + m);
      }
    }
  }

  private static void giveCards(int players) {
    String[][] deal = new String[players][5];
    for (int i = 0; i < players; i++) {
      int cardCount = 0;
      while (cardCount < 5) {
        int index = (int) (Math.random() * 36);
        if (coloda.get(index) == null) {
          continue;
        }
        String nextCard = coloda.get(index);
        deal[i][cardCount] = nextCard;
        System.out.print(nextCard + " ");
        coloda.set(index, null);
        cardCount++;
      }
      System.out.println();
    }
  }

}

