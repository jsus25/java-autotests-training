package ru.rtf;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

  @Test
  public void testPrime() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNotPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE-2));
  }

  @Test
  public void testSqrtPrime() {
    Assert.assertFalse(Primes.isPrime(25));
  }

  @Test
  public void testWPrime() {
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
  }

  @Test
  public void testWNotPrime() {
    Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE-2));
  }

  @Test
  public void testWSqrtPrime() {
    Assert.assertFalse(Primes.isPrimeWhile(25));
  }


}
