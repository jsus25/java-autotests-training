package ru.rtf;

import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    String[] langs = {"Java", "C#", "Python", "PHP"}; //массив
    for (String l : langs) {
      System.out.println("I want to learn " + l);  //итерация по элементам массива
    }

    List<String> countries = Arrays.asList("Thailand", "Spain", "Egipet", "Venesuela"); //список
    for (String c : countries ) {
      System.out.println("I want to go to " + c);  // итерация по элементам списка
    }
  }
}
