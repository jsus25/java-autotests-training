package ru.rtf;

public class MyFirstProgram{

  public static void main(String[] args) {
    hello("Julia");
    hello("everybody");

    Point p1 = new Point(1, 1);
    Point p2 = new Point(4, 5);
    System.out.println("Расстояние между точками (" + p1.x + "," + p1.y + ") и (" + p2.x + "," + p2.y + ") " +
            "равно " + dimention(p1, p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello " + somebody + "!");
  }

  public static double dimention(Point p1, Point p2) {
    return (Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x)));
  }

}