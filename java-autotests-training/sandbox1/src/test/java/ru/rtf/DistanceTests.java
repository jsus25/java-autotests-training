package ru.rtf;

import org.testng.Assert;
import org.testng.annotations.Test;


public class DistanceTests {

  @Test
  public void testDistance1(){
    Point p1 = new Point(1, 1);
    Point p2 = new Point(4, 5);
    Assert.assertEquals(Point.distance(p1, p2), 5.0);
  }

  @Test
  public void testDistance2() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(1, 1);
    Assert.assertEquals(Point.distance(p1, p2), 0.0);
  }

  @Test
  public void testDistance3() {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(-2, -3);
    Assert.assertEquals(Point.distance(p1, p2), -5.0);
  }

}

