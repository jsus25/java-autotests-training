package ru.rtf;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

  @Test
  public void testEquation0() {
    Equation eq = new Equation(1,1,1);
    Assert.assertEquals(eq.rootNumber(), 0);
  }
  @Test
  public void testEquation1() {
    Equation eq = new Equation(1,2,1);
    Assert.assertEquals(eq.rootNumber(), 1);
  }
  @Test
  public void testEquation2() {
    Equation eq = new Equation(1,4,2);
    Assert.assertEquals(eq.rootNumber(), 2);
  }
}
